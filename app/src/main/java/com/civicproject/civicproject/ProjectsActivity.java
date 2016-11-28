package com.civicproject.civicproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProjectsActivity extends AppCompatActivity implements View.OnClickListener {
    Button buttonAddProject, buttonSettings;
    String url = "http://188.128.220.60/projects.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        buttonAddProject = (Button)findViewById(R.id.buttonAddProject);
        buttonSettings = (Button)findViewById(R.id.buttonSettings);
        final ListView listView = (ListView) findViewById(R.id.listView);


        SharedPreferences myprefs = getSharedPreferences("user", MODE_WORLD_READABLE);
        String username = myprefs.getString("username", null);
        String type = "getUser";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        JSONArray ja= null;
        try {
            ja = new JSONArray(backgroundWorker.tempJSON);
            JSONObject jo1 = null;
            for(int i = 0; i < ja.length(); i++)
            {
                SharedPreferences sharedPreferences = this.getSharedPreferences("user", MODE_WORLD_READABLE);

                jo1 = ja.getJSONObject(i);
                String name = jo1.getString("name");
                String surname = jo1.getString("surname");
                String age = jo1.getString("age");
                String password = jo1.getString("password");
                String author_key = jo1.getString("id");

                sharedPreferences.edit().putString("name", name).apply();
                sharedPreferences.edit().putString("surname", surname).apply();
                sharedPreferences.edit().putString("age", age).apply();
                sharedPreferences.edit().putString("password", password).apply();
                sharedPreferences.edit().putString("author_key", author_key).apply();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Downloader downloader = new Downloader(this,url,listView);
        downloader.execute();
        buttonAddProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddProjectActivity.class));
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserAreaActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
