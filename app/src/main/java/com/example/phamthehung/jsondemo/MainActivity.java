package com.example.phamthehung.jsondemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ReadJsonObject().execute("https://api.github.com/users/google/repos");
        mTextView = findViewById(R.id.text_name);
    }
    public class ReadJsonObject extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                //Đọc nội dung
                InputStreamReader inputStreamReader
                        = new InputStreamReader(url.openConnection().getInputStream());
                //Đọc dữ liệu
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                //Chứa dòng dữ liệu
                String line ="";
                while ((line = bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            mTextView.setText(s);
        }
    }
}
