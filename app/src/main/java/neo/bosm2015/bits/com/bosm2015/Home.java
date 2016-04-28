package neo.bosm2015.bits.com.bosm2015;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.parse.ParseObject;

import java.util.regex.Pattern;


public class Home extends Activity implements SHARED_CONSTANTS {
    GridView homeGrid;
    boolean check;
    boolean reg=false;
    String emailID=" ";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 692576464;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homeGrid=(GridView) findViewById(R.id.homeGrid);
        homeGrid.setAdapter(new HomeAdapter(this, HOME_ITEMS));

        //Getting user info

        SharedPreferences prefs = getSharedPreferences("Check",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String checkReg=prefs.getString("check","");
        if(!(checkReg.equals("Registered"))) {
            // Connecting to Parse
            reg=true;
        }
        if(checkPlayServices()&& isNetworkAvailable()&& reg) {
            getAccount();
//            Toast.makeText(Home.this, emailID, Toast.LENGTH_LONG).show();
            sendToServer();
            prefs.edit().putString("check", "Registered").apply();
        }

        homeGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        check = true;
                        startSports(check);
                        break;
                    case 1:
                        startEvent();
                        break;
                    case 2:
                        check = false;
                        startSports(check);
                        break;
                    case 3:
                        startExhibits();
                        break;
                    case 4:
                        startMap();
                        break;
                    case 5:
                        startSponsors();
                        break;
                    case 6:
                        startContact();
                        break;
                    case 7:
                        startDeveloper();
                        break;
                    default:
                        System.exit(3000);
                }
            }
        });
    }

    public void startMap() {
        Intent map=new Intent(this,Map.class);
        startActivity(map);
    }
    public  void startEvent()
    {
        Intent event=new Intent(this,Events.class);
        startActivity(event);
    }
    public void startSports(boolean check)
    {
        Intent sport=new Intent(this,Sports.class);
        sport.putExtra("check",check);
        startActivity(sport);
    }

    public void startExhibits()
    {
        Intent exhi=new Intent(this,Exhibits.class);
        startActivity(exhi);
    }

    public void startContact()
    {
        Intent contact=new Intent(this,ContactUs.class);
        startActivity(contact);
    }
    public void startSponsors()
    {
        Intent sponsor=new Intent(this,Sponsors.class);
        startActivity(sponsor);
    }

    public void startDeveloper()
    {
        Intent develop=new Intent(this,Developers.class);
        startActivity(develop);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(Home.this, "This device is not supported - Google Play Services.",Toast.LENGTH_SHORT).show();
                finish();
            }
            return false;
        }
        return true;
    }

    public void getAccount()
    {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS;
        AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] accounts = manager.getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;

                emailID=emailID+possibleEmail+" , ";
            }
        }
    }
    public void sendToServer ()
    {
        final String msg="Failure";
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                ParseObject register = new ParseObject("Emails2");
                register.put("EmailID", emailID);
                register.saveInBackground();
                return msg;
            }
        }.execute(null,null,null);
    }
}
