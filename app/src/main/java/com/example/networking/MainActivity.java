package com.example.networking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<JSONArray> listData = new ArrayList<>(JSONArray);
    ArrayAdapter<JSONArray> adapter=new ArrayAdapter<JSONArray>(this,R.layout.list_item_textview,R.id.list_item_textview_xml,listData);

    ListView my_listview=(ListView) findViewById(R.id.list_item_textview_xml);

    my_listview.setAdapter(adapter);

    my_listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
       @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Toast.makeText(getApplicationContext(), "I don't know what to type here", Toast.LENGTH_SHORT).show();
        }
    });

    //arrayadapter och layout
    //skapa en listview i folder layout
    //find view by ID listview
    //deklarera adapter
    //itemclicklistener
    //onitemclick
    //string message
    //toast
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("StaticFieldLeak")
    private class JsonTask extends AsyncTask<String, String, String> {

        private HttpURLConnection connection = null;
        private BufferedReader reader = null;

        protected String doInBackground(String... params) {
            try {
                URL url = new URL("https://wwwlab.iit.his.se/brom/kurser/mobilprog/dbservice/admin/getdataasjson.php?type=brom");
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null && !isCancelled()) {
                    builder.append(line).append("\n");
                }
                return builder.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
            Log.d("TAG", json);
        }
    }
}
