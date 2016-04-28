package neo.bosm2015.bits.com.bosm2015;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContactUs extends Activity implements SHARED_CONSTANTS {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        ListView contactList=(ListView) findViewById(R.id.contactList);
        ListAdapter custom= new ContactAdapter(this ,CONTACT_HEAD);
        contactList.setAdapter(custom);

        Toast.makeText(ContactUs.this,"Click on a person to call or send an email to him",Toast.LENGTH_SHORT).show();

        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle("Contact");


        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                dialog.setMessage("How would you like to contact " + CONTACT_NAME[position]);

                    dialog.setButton2("Call", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent call = new Intent(Intent.ACTION_DIAL);
                            call.setData(Uri.parse("tel:" + CONTACT_NUMBER[position]));
                            startActivity(call);
                        }
                    });

                dialog.setButton("Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent mail = new Intent(Intent.ACTION_VIEW);
                        mail.setType("plain/text");
                        mail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                        mail.putExtra(Intent.EXTRA_EMAIL, new String[]{CONTACT_EMAIL[position]});
                        mail.putExtra(Intent.EXTRA_SUBJECT, "");
                        mail.putExtra(Intent.EXTRA_TEXT, "\n\n\n\n\n\n\n\nSent From The BOSM App");

                        try {
                            startActivity(Intent.createChooser(mail, "Send mail...(Preferably GMail) ;)"));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(ContactUs.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.show();
            }
        });
    }
}
