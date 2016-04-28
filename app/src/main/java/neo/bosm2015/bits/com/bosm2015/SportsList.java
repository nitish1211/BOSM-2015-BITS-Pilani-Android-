package neo.bosm2015.bits.com.bosm2015;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nitish on 9/9/2015.
 */
public class SportsList extends ArrayAdapter<String>{

    private static LayoutInflater mInflater;
    private final Context context;

    int[] picId;

    public SportsList(Context context ,String[] homeName,int [] picture)
    {
        super(context,R.layout.custom_sports, homeName);
        picId=picture;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Animation anim1 =AnimationUtils.loadAnimation(getContext(),R.anim.sports_fade);
        Animation anim2 =AnimationUtils.loadAnimation(getContext(),R.anim.up_from_bottom);
       // Animation anim3 =AnimationUtils.loadAnimation(getContext(),R.anim.down_from_top);

        String str= getItem(position);
        final Holder holder;

        if(convertView==null)
        {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.custom_sports,parent,false);
            holder=new Holder();
            holder.txt=(TextView) convertView.findViewById(R.id.txt);

            holder.pic=(ImageView) convertView.findViewById(R.id.pic);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }

        holder.txt.setText(str);
        holder.pic.setImageResource(picId[position]);

            convertView.startAnimation(anim1);

            holder.pic.startAnimation(anim2);

        return convertView;
    }

    public class Holder
    {
        public TextView txt;
        public ImageView pic;
    }

}
