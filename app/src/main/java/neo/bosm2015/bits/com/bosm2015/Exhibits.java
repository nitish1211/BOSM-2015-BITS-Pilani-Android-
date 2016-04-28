package neo.bosm2015.bits.com.bosm2015;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Exhibits extends Activity implements SHARED_CONSTANTS {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exhibits);

        ListView exhibitList=(ListView) findViewById(R.id.exhibitList);
        ListAdapter custom= new ExhibitAdapter(this ,EXHIBIT_LIST);
        exhibitList.setAdapter(custom);


        exhibitList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        ToDetails(position);
                        break;
                    case 1:
                        ToDetails(position);
                        break;
                    case 2:
                        ToDetails(position);
                        break;
                    case 3:
                        ToDetails(position);
                        break;
                    case 4:
                        ToDetails(position);
                        break;
                    case 5:
                        ToDetails(position);
                        break;
                    case 6:
                        ToDetails(position);
                        break;
                    case 7:
                        ToDetails(position);
                        break;
                    case 8:
                        ToDetails(position);
                        break;
                    case 9:
                        ToDetails(position);
                        break;
                    case 10:
                        ToDetails(position);
                        break;
                    case 11:
                        ToDetails(position);
                        break;
                    case 12:
                        ToDetails(position);
                        break;
                    case 13:
                        ToDetails(position);
                        break;
                    case 14:
                        ToDetails(position);
                        break;
                    case 15:
                        ToDetails(position);
                        break;
                    case 16:
                        ToDetails(position);
                        break;
                    default:
                        Toast.makeText(Exhibits.this, "Something Went Wrong", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void ToDetails(int exhibitNum)
    {
        Intent i=new Intent(this,ExhibitDetails.class);
        i.putExtra("ExhibitNum", exhibitNum);
        startActivity(i);
    }

}
