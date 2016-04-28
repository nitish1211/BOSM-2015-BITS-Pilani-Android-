package neo.bosm2015.bits.com.bosm2015;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ExhibitDetails extends Activity implements SHARED_CONSTANTS {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibit_details);

        Bundle exhibit=getIntent().getExtras();

        int exhibitNum= exhibit.getInt("ExhibitNum");

        ImageView img=(ImageView) findViewById(R.id.img);
        TextView head=(TextView) findViewById(R.id.head);
        TextView details=(TextView) findViewById(R.id.details);
        TextView location=(TextView) findViewById(R.id.location);

        img.setImageResource(EXHIBIT_PICS[exhibitNum]);
        head.setText(EXHIBIT_LIST[exhibitNum]+"\n"+EXHIBIT_DEPT[exhibitNum]);
        details.setText(EXHIBIT_DETAILS[exhibitNum]);
        location.setText(EXHIBIT_LOCATION[exhibitNum]);

    }
}
