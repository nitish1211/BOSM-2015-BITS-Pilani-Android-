package neo.bosm2015.bits.com.bosm2015;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Patterns;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.regex.Pattern;

/**
 * Created by Nitish on 9/11/2015.
 */
public class BOSM2015 extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this, "rw8ge9Pd6twdpXCZToI3xo5M7DrwmGP2Z2ITBhhs", "H3XDoV5cpL2I7wZym1ESj3BgcVsOrKJraCLpRCIL");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }

}
