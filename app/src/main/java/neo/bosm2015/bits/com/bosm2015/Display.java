package neo.bosm2015.bits.com.bosm2015;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Display extends Activity implements SHARED_CONSTANTS {

    ProgressDialog progress;
    String HTML;
    final Database db = new Database(this);
    int position;
    boolean check;
    WebView web;
    boolean status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Bundle disp = getIntent().getExtras();
        HTML = disp.getString("html");
        position = disp.getInt("position");
        check = disp.getBoolean("check");

        progress = new ProgressDialog(Display.this);
        progress.setMax(6);

        web = (WebView) findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);
        //  web.getSettings().setPluginsEnabled(true);
        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setSupportZoom(true);
        WebSettings ws = web.getSettings();
        ws.setBuiltInZoomControls(true);
        ws.setUseWideViewPort(true);

        web.setWebViewClient(new mwebViewClient());

        web.loadData(HTML, "text/html", "utf-8");


    }

    private class mwebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progress.setTitle("Loading");
            progress.setMessage("Please wait getting data for you...");
            if (!isFinishing())
                progress.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progress.dismiss();
        }
    }

    public void refresh(View view) {
        progress.setTitle("Loading");
        progress.setMessage("Please wait getting the latest data...");
        progress.setCancelable(false);
        progress.show();

        if (sync()) {
            Toast.makeText(Display.this, "Data has been refreshed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Display.this, "Please make sure you are connected to the internet", Toast.LENGTH_LONG).show();
        }
    }

    public boolean sync() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                String msg = "done";

                if (isNetworkAvailable()) {
                    SyncDB.refreshData(getApplicationContext());
                    status = true;
                }
                else {
                    status=false;
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String s) {
                HTML = db.getSportColumn(DB_SPORTS[position], check);
                if (HTML == null||HTML.isEmpty()) {
                    HTML = "<html><body><br><br><br><br><text><b>Please Connect to the Internet & Refresh</b></text></body></html>";
                }
                web.loadData(HTML, "text/html", "utf-8");

                progress.dismiss();
            }

            @Override
            protected void onPreExecute() {

            }

        }.execute(null, null, null);

        return status;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
