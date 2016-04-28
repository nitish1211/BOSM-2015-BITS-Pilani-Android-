package neo.bosm2015.bits.com.bosm2015;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class EventDetails extends Activity implements SHARED_CONSTANTS {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Bundle event=getIntent().getExtras();
        int position=event.getInt("position");

        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        TextView head=(TextView)findViewById(R.id.head);
        TextView time=(TextView)findViewById(R.id.time);
        TextView location=(TextView)findViewById(R.id.location);
        TextView description =(TextView)findViewById(R.id.description);

        head.setText(EVENT_HEAD[position]);
        time.setText(EVENT_TIME[position]);
        location.setText(EVENT_LOCATION[position]);
        description.setText(EVENT_DESCRIPTION[position]);
        description.startAnimation(anim1);

    }

}
