package temnogrudova.com.jsontest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    //RecyclerView twitterMessages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
   //     twitterMessages = (RecyclerView)findViewById(R.id.myList);
    //    twitterMessages.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ParseTask().execute();

            }
        });


    }

    private class ParseTask extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader    reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            // �������� ������ � �������� �������
            try {
                URL url = new URL("http://jsonplaceholder.typicode.com/posts");

                //Open Http connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //Возвращает поток для чтения данных
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();

                //буфферизовать чтение с любого потока.
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            // ������� ������� ���������� json-������

            JSONObject dataJsonObj = null;

            ArrayList<Post> items = new ArrayList<Post>();
            try {
                JSONArray data = new JSONArray(strJson);
                for (int i = 0; i < data.length(); i++) {
                    JSONObject itemData = data.getJSONObject(i);
                    String userId = itemData.getString("userId");
                    String id = itemData.getString("id");
                    String title = itemData.getString("title");
                    String body = itemData.getString("body");
                    Log.d("myLog", "userId " + items.get(i).userId + "\n"
                            + "Id " + items.get(i).id + "\n"
                            + "title " + items.get(i).title + "\n"
                            + "body " + items.get(i).body + "\n");
                    items.add(new Post(userId, id, title, body));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                items.add(new Post("", "", "",""));
            }

            for(int i = 0; i < items.size(); i++){
                Log.d("myLog", "userId " + items.get(i).userId + "\n"
                + "Id " + items.get(i).id + "\n"
                + "title " + items.get(i).title + "\n"
                + "body " + items.get(i).body + "\n");

            }
          //  RecyclerAdapter recyclerAdapter = new RecyclerAdapter(items);
            //twitterMessages.setAdapter(recyclerAdapter);

            // MessagesAdapter boxAdapter = new MessagesAdapter(context, R.layout.pattern,items);
            // twitterMessages.setAdapter(boxAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
