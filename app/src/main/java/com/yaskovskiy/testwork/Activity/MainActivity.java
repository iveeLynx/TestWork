package com.yaskovskiy.testwork.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.yaskovskiy.testwork.Adapter.Adapter;
import com.yaskovskiy.testwork.Model.Item;
import com.yaskovskiy.testwork.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String URL = "com.yaskovskiy.testwork.MESSAGE";

    List<Item> items = new ArrayList<>();

    String sUrl;

    Adapter adapter;
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
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_adme:
                        break;
                    case R.id.nav_fav:
                        Intent intent = new Intent(MainActivity.this, FavoritesActivity.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });

        new NewThread().execute();
        adapter = new Adapter(getApplicationContext(),recycler, this, items);

    }

    public void onUrlClick(final View view) {
        Intent intent = new Intent(this, PostShowActivity.class);
        TextView textView = (TextView) view;
        sUrl = String.valueOf(textView.getHint());
        intent.putExtra(URL, sUrl);
        startActivity(intent);
//        webView.loadUrl(sUrl);
    }

    public class NewThread extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... args) {
            Elements content1, content2;
            Document doc;
            try {
                doc = Jsoup.connect("http://www.adme.ua/news/").get();
                content1 = doc.select("div > h3 > a");
                content2 = doc.select("div > div > div > div > div > div > a > img");
                items.clear();
                for (int i = 0; i < content1.size(); i++) {
                    Item item = new Item(content1.get(i).text(), "http://www.adme.ua" + content1.get(i).attr("href"), content2.get(i).attr("src"));
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
