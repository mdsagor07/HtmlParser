package com.example.testhtmlparser;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import static java.lang.StrictMath.abs;


//import org.w3c.dom.Document;


public class MainActivity extends AppCompatActivity {

    EditText wordtext;
    EditText meaningtext;
    Button button;

    String title;
    String xx;
    String finaltitle;

    ProgressDialog progressDialog;

    String url= "https://dictionary.cambridge.org/dictionary/english/";
    String urlname="white";
    String urlend="?s=t";
    String urlendw;
    String optimiseText="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordtext=(EditText)findViewById(R.id.word);
        meaningtext=(EditText)findViewById(R.id.meaning);
        button=(Button)findViewById(R.id.buttonid);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                urlendw= String.valueOf(wordtext.getText());

                getMeaning getSentence =new getMeaning();
                getSentence.execute();


            }
        });
    }

    private class getMeaning extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Please wait");
            progressDialog.setMessage("Find for meaning ");
            progressDialog.show();


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            meaningtext.setText(finaltitle);
            progressDialog.dismiss();




        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Void doInBackground(Void... voids) {

            Document document;

            try {
                document= (Document) Jsoup.connect(url + urlendw).get();
                //title = document.title();

                Element lll=document.getElementsByClass("def ddef_d db").first();

                title=String.valueOf(lll.text()).trim();
                int a=title.length();
                finaltitle=String.valueOf(lll.text()).trim().substring(0,a-1);



               /* Element llll=document.getElementsByClass("luna-example italic").first();

                title=String.valueOf(lll.text()).trim();
                xx=String.valueOf(llll.text()).trim();

                int a=title.length();
                int b=xx.length();

                int c=abs(a-b);
                if(a>b)
                {
                    finaltitle=String.valueOf(lll.text()).substring(0,c);
                }
                else {
                    finaltitle=String.valueOf(lll.text()).trim();
                }*/







            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

}
