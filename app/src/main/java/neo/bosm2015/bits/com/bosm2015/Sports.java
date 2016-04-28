package neo.bosm2015.bits.com.bosm2015;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;

public class Sports extends Activity implements SHARED_CONSTANTS {
    Database db = new Database(this);
    String HTML="<html><body><br><br><br><br><text><b>Please Refresh no data has been downloaded \nfrom the server</b></text></body></html>";
    boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        Bundle sp=getIntent().getExtras();
        check=sp.getBoolean("check");

        GridView sportsGrid=(GridView) findViewById(R.id.sportsGrid);
        sportsGrid.setAdapter(new SportsList(this, SPORTS_LIST,SPORT_IMAGES));


        sportsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               sethtml(position);
           }
       });

        //Creating a thread to refresh the data in background ONCE AGAIN
       sync();
        }


    public void sethtml(int position)
    {
        HTML=db.getSportColumn(DB_SPORTS[position], check);
        if(HTML==null||HTML.isEmpty())
        {
            HTML="<html><body><br><br><br><br><text><b>Please Refresh no data has been downloaded\n from the server</b></text></body></html>";
        }
        Intent disp=new Intent(this,Display.class);
        disp.putExtra("html",HTML);
        disp.putExtra("position", position);
        disp.putExtra("check", check);
        startActivity(disp);
    }

    public void sync() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "done";

                if (isNetworkAvailable()) {
                    SyncDB.refreshData(getApplicationContext());
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String s) {
            }

            @Override
            protected void onPreExecute() {

            }
        }.execute(null, null, null);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
