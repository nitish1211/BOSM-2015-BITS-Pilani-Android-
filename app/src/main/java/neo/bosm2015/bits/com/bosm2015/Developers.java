package neo.bosm2015.bits.com.bosm2015;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class Developers extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);

        ImageView NitishPic=(ImageView) findViewById(R.id.NitishPic);
        ImageView JaiPic=(ImageView) findViewById(R.id.JaiPic);
        ImageView AshitPic=(ImageView) findViewById(R.id.AshitPic);

        Bitmap img= BitmapFactory.decodeResource(getResources(),R.drawable.profile);
      Bitmap roundNitish= getRoundedShape(img);

        NitishPic.setImageBitmap(roundNitish);
        JaiPic.setImageBitmap(roundNitish);
        AshitPic.setImageBitmap(roundNitish);
    }

    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = scaleBitmapImage.getWidth();
        int targetHeight = scaleBitmapImage.getHeight();
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }
}
