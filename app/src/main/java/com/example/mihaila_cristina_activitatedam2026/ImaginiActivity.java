package com.example.mihaila_cristina_activitatedam2026;

import android.content.Intent;
import android.content.pm.webapp.WebAppInstallRequest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImaginiActivity extends AppCompatActivity {

    private List<ImagineDomeniu> lista = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_imagini);

        ListView lv = findViewById(R.id.imagini);

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://cdn.pixabay.com/photo/2016/04/04/14/12/monitor-1307227_640.jpg");
        linkuriImagini.add("https://cdn.pixabay.com/photo/2020/10/21/18/07/laptop-5673901_1280.jpg");
        linkuriImagini.add("https://cdn.pixabay.com/photo/2020/01/26/20/14/computer-4795762_640.jpg");
        linkuriImagini.add("https://static.vecteezy.com/system/resources/thumbnails/047/824/500/small/high-quality-realistic-beautiful-computer-monitor-image-free-png.png");
        linkuriImagini.add("https://cdn.pixabay.com/photo/2016/05/27/05/39/computer-1419134_1280.png");

        String[] texte = {"Imagine 1", "Imagine 2", "Imagine 3", "Imagine 4", "Imagine 5"};

        String[] paginiWeb = {"https://cdn.pixabay.com/photo/2016/04/04/14/12/monitor-1307227_640.jpg", "https://cdn.pixabay.com/photo/2020/10/21/18/07/laptop-5673901_1280.jpg",
                "https://cdn.pixabay.com/photo/2020/01/26/20/14/computer-4795762_640.jpg", "https://static.vecteezy.com/system/resources/thumbnails/047/824/500/small/high-quality-realistic-beautiful-computer-monitor-image-free-png.png",
                "https://cdn.pixabay.com/photo/2016/05/27/05/39/computer-1419134_1280.png"};

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper()); // Se recomandă getMainLooper()

        executor.execute(new Runnable() {
            @Override
            public void run() {
                List<Bitmap> imagini = new ArrayList<>();
                for (String link : linkuriImagini) {
                    HttpURLConnection con = null;
                    try {
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                        is.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                        imagini.add(null);
                    } finally {
                        if (con != null) {
                            con.disconnect();
                        }
                    }
                }

                // MUTĂM handler.post AICI! Se va executa pe UI Thread DOAR DUPĂ ce s-au descărcat imaginile
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        lista = new ArrayList<>();
                        for (int i = 0; i < texte.length; i++) {
                            lista.add(new ImagineDomeniu(texte[i], imagini.get(i), paginiWeb[i]));
                        }

                        // Folosim ImaginiActivity.this în loc de getApplicationContext()
                        ImagineAdapter adapter = new ImagineAdapter(ImaginiActivity.this, R.layout.imagine_layout, lista);
                        lv.setAdapter(adapter);
                    }
                });
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                it.putExtra("link", lista.get(position).getLink());
                startActivity(it);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}