package neo.bosm2015.bits.com.bosm2015;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Nitish on 9/9/2015.
 */
public class EventAdapter extends ArrayAdapter<String> implements SHARED_CONSTANTS {

    int t=0;
    private LayoutInflater mInflater;
    private final Context context;


    public EventAdapter(Context context, String[] item) {
        super(context,R.layout.custom_events, item);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.push_left_in);
        Animation anim2 = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        String str= getItem(position);
        final Holder holder;

        if(convertView==null)
        {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.custom_events,parent,false);
            holder = new Holder();
            holder.header=(TextView) convertView.findViewById(R.id.header);
            holder.time=(TextView) convertView.findViewById(R.id.time);
            holder.details=(TextView) convertView.findViewById(R.id.details);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }

        holder.header.setText(str);
        holder.time.setText(EVENT_TIME_DATE[position]);
        holder.details.setText(EVENT_SHORT_DETAIL[position]);


        if(t<position) {
            convertView.startAnimation(anim);

            holder.details.startAnimation(anim2);
        }

        t=position;
        return convertView;
    }

    public class Holder
    {
        public TextView header;
        public TextView time;
        public TextView details;
    }
}
