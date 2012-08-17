package co.martinbrown.example.httpurlconnection;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class HttpUrlConnectionExampleActivity extends Activity {

    String data = "";
    WebView wv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        wv = (WebView) findViewById(R.id.webView1);

        new Thread(new Runnable() {

            @Override
            public void run() {

                try
                {
                    URL url = new URL("http://mobiles.cs.fsu.edu/");

                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                    data = new java.util.Scanner(in).useDelimiter("\\A").next();

                    urlConnection.disconnect();
                }
                catch(Exception e) {
                    e.printStackTrace();
                }

                wv.loadData(data, "text/html", "UTF-8");

            }
        }).start();
    }
}