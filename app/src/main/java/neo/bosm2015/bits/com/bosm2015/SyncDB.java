package neo.bosm2015.bits.com.bosm2015;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Nitish on 9/11/2015.
 */
public class SyncDB {
    public static boolean isNetworkAvailable(Context c) {

        ConnectivityManager cm = (ConnectivityManager)c.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static void refreshData(Context context) {

        final Database db = new Database(context);


        ParseQuery<ParseObject> query = ParseQuery.getQuery("bosm");

        query.findInBackground(new FindCallback<ParseObject>() {

            @SuppressWarnings("unchecked")
            @Override
            public void done(List<ParseObject> sportList, ParseException e) {

                if (e == null) {
                    for (ParseObject parseSport : sportList) {

                        Sport sport = new Sport(parseSport.getObjectId(), parseSport.getString("name"),
                                parseSport.getString("schedule"), parseSport.getString("result"));

                        db.addSport(sport);
                    }

                }
            }
        });
    }
}
