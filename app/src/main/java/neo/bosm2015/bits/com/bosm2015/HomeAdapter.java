package neo.bosm2015.bits.com.bosm2015;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nitish on 9/9/2015.
 */

public class HomeAdapter  extends BaseAdapter implements SHARED_CONSTANTS{

    private LayoutInflater mInflater;
    private final Context context;
    String[] name;

    public HomeAdapter(Context context ,String[] homeName)
    {
        this.context=context;
        name=homeName;

    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;

        Animation anim1 = AnimationUtils.loadAnimation(context, R.anim.home_fade);

        if(convertView==null)
        {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.custom_home,parent,false);
            holder = new Holder();
            holder.txt=(TextView) convertView.findViewById(R.id.txt);
            holder.pic=(ImageView) convertView.findViewById(R.id.pic);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }

        holder.txt.setText(name[position]);
        holder.pic.setImageResource(HOME_PICS[position
                ]);


       convertView.startAnimation(anim1);

        return convertView;
    }


    public class Holder
    {
        TextView txt;
        ImageView pic;
    }
}
