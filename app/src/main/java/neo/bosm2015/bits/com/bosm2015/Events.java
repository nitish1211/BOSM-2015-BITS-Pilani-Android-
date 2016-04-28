package neo.bosm2015.bits.com.bosm2015;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Events extends Activity implements SHARED_CONSTANTS {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        ListView eventList=(ListView) findViewById(R.id.eventList);
        ListAdapter custom= new EventAdapter(this,EVENT_HEAD);
        eventList.setAdapter(custom);

        eventList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                openDescription(position);

            }
        });
    }

    public void openDescription(int position)
    {
        Intent event=new Intent(this,EventDetails.class);
        event.putExtra("position",position);
        startActivity(event);
    }
}
