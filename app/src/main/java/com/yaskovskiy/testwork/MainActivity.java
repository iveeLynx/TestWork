package com.yaskovskiy.testwork;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import Interface.ILoadMore;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String EXTRA_MESSAGE = "com.yaskovskiy.testwork.MESSAGE";

    private WebViewClient client;
    private WebView webView;

    List<Item> items = new ArrayList<>();
    ArrayList<String> test = new ArrayList<>();

    String sUrl;

    String title, name, text;

    Adapter adapter;
    CardView cardView;
    RecyclerView rv;
    RecyclerView recycler;
    BottomNavigationView bottomNavigationView;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_adme:
//                        Intent intent = new Intent(MainActivity.this, PostShowActivity)
                        break;
                    case R.id.nav_fav:
//                        Intent intent = new Intent(MainActivity.this, PostShowActivity)
                        break;
                }
                return false;
            }
        });

        new NewThread().execute();
        adapter = new Adapter(recycler, this, items);

    }

    public void onUrlClick(final View view) {
        Intent intent = new Intent(this, PostShowActivity.class);
        TextView textView = (TextView) view;
        sUrl = String.valueOf(textView.getText());
        intent.putExtra(EXTRA_MESSAGE, sUrl);
        startActivity(intent);
//        webView.loadUrl(sUrl);
    }

    public class NewThread extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... args) {
            Elements content1, content2;
            Element tel;
            Document doc;
            try {
                doc = Jsoup.connect("http://www.adme.ua/news/").get();
                content1 = doc.select("div > h3 > a");
                content2 = doc.select("div > div > div > div > div > div > a > img");
                items.clear();
                for (int i = 0; i < content1.size(); i++) {
                    Item item = new Item(content1.get(i).text(), "http://www.adme.ua" + content1.get(i).attr("href"));
                    items.add(item);

                }
            } catch (Exception ex) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            recycler.setAdapter(adapter);
        }


    }
}
