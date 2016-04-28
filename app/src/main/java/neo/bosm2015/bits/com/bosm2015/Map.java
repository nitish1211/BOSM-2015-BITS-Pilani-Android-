package neo.bosm2015.bits.com.bosm2015;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Map extends Activity {

    ProgressDialog progress ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        //Progress bar initializing

        progress= new ProgressDialog(Map.this);
        progress.setMax(6);

        //Webview properties
        String HTML="<html><body><img src=\"images/bosmmap.jpg\" /></body></html>";

        WebView web = (WebView) findViewById(R.id.web);
        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setSupportZoom(true);
        WebSettings ws = web.getSettings();
        ws.setSaveFormData(true);
        ws.setJavaScriptEnabled(true);
        ws.setSavePassword(true);
        ws.setBuiltInZoomControls(true);
        ws.setSupportZoom(true);
        ws.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        ws.setLoadWithOverviewMode(true);
        ws.setUseWideViewPort(true);
        ws.setAppCacheMaxSize(2048 * 2048);
        ws.setAppCachePath(getApplicationContext().getCacheDir().getAbsolutePath());
        ws.setAppCacheEnabled(true);

        web.setWebViewClient(new mwebViewClient());

        //web.loadUrl("https://www.google.co.in/maps/place/Birla+Institute+of+Technology+and+Science,+Pilani+Campus,+Vidya+Vihar,+Pilani,+Rajasthan+333031/@28.3540543,75.5881798,18z/data=!4m2!3m1!1s0x39131964f43e4575:0x1fbad30854cf884d?authuser=0&hl=en");
        web.loadDataWithBaseURL("file:///android_asset/", HTML, "text/html", "utf-8", null);

        Toast.makeText(Map.this, "Fetching the Map .....", Toast.LENGTH_SHORT).show();

    }

    private class mwebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            progress.setTitle("Loading");
            progress.setMessage("Please wait fetching map...");
            progress.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progress.dismiss();
        }
    }
    }

