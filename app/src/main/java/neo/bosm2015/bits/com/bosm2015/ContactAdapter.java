package neo.bosm2015.bits.com.bosm2015;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Nitish on 9/3/2015.
 */
public class ContactAdapter extends ArrayAdapter<String> implements SHARED_CONSTANTS {
    private LayoutInflater mInflater;
    private final Context context;
    int t=0;

    public ContactAdapter(Context context, String[] item)
    {
        super(context,R.layout.custom_contact, item);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder;
        String str=getItem(position);
        if(convertView==null)
        {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.custom_contact,parent,false);
            holder = new Holder();
            holder.head=(TextView) convertView.findViewById(R.id.head);
            holder.name=(TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }

        holder.head.setText(str);
        holder.name.setText(CONTACT_DETAILS[position]);

//        if(t<position) {
//            convertView.startAnimation(anim1);
//            holder.img.startAnimation(anim3);
//        }
//    else {
//            convertView.startAnimation(anim2);
//            holder.img.startAnimation(anim3);
//        }
//
//        t=position;
        return convertView;
    }

    public class Holder
    {
        public TextView head;
        public TextView name;
    }
}
