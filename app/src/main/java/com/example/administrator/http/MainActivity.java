package com.example.administrator.http;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.http.bean.gsonAPP;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button queryButton;
    private EditText editText;
    private TextView TimeText,jsonText,VersionText,StatusText,CityText,PhoneText,ProvinceText,OperatorText;
    private String NumString;
    private String address = null;
    public static String phoneurl="https://mobsec-dianhua.baidu.com/dianhua_api/open/location?tel=电话号码";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queryButton = (Button)findViewById(R.id.query_button);
        editText = (EditText)findViewById(R.id.edit_text);
        jsonText = (TextView)findViewById(R.id.tv_json);
        TimeText = (TextView)findViewById(R.id.time_tv);
        VersionText = (TextView)findViewById(R.id.version_tv);
        StatusText = (TextView)findViewById(R.id.status_tv);
        CityText = (TextView)findViewById(R.id.city_tv);
        PhoneText = (TextView)findViewById(R.id.phone_tv);
        ProvinceText =(TextView)findViewById(R.id.province_tv);
        OperatorText = (TextView)findViewById(R.id.operator_tv);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              sendRequestWithOkHttpURLConnection();
            }
        });
    }
    private void sendRequestWithOkHttpURLConnection(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response1;
                NumString = editText.getText().toString();
                address = phoneurl+NumString;
                HttpURLConnection connection = null;
                BufferedReader reader =null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    //connection.setConnectTimeout(8000);
                    //connection.setReadTimeout(8000);
                    InputStream  in = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    response1 = response.substring(0, 14) +"phoneNumber"+ response.substring(25);
                    showResponse(response1);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if(reader != null){
                        try{
                              reader.close();
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                    if(connection != null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    private  void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson = new Gson();
                gsonAPP gsonapp = gson.fromJson(response,gsonAPP.class);
                String T=String.valueOf(gsonapp.getResponseHeader().getTime());
                String V=String.valueOf(gsonapp.getResponseHeader().getVersion());
                String S=String.valueOf(gsonapp.getResponseHeader().getStatus());
                PhoneText.setText("电话号码是"+NumString);
                CityText.setText("城市是"+gsonapp.getResponse().getPhoneNumber().getDetail().getArea().get(0).getCity());
                ProvinceText.setText("省份是"+gsonapp.getResponse().getPhoneNumber().getDetail().getProvince());
                OperatorText.setText("运营商是"+gsonapp.getResponse().getPhoneNumber().getDetail().getOperator());
                TimeText.setText("Time is "+ T);
                VersionText.setText("Version is "+ V);
                StatusText.setText("Status is "+ S);
                jsonText.setText("接口的json数据格式为"+response);
            }
        });
    }
   /*private void sendRequestWithOkhttp(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://mobsec-dianhua.baidu.com/dianhua_api/open/location?tel=电话号码")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithGSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
   }*/
   /*private void parseJSONWithGSON(String jsonData){
       Gson gson = new Gson();
       //String jsonData = "{\"response\":{},\"responseHeader\":{\"status\":200,\"time\":1562915497402,\"version\":\"1.1.0\"}}";
       //List<gsonAPP> gsonAPPList = gson.fromJson(jsonData,new TypeToken<List<gsonAPP>>(){}.getType());
       //resultText.setText(gsonAPPList.toString());
       gsonAPP gsonapp= gson.fromJson(jsonData,gsonAPP.class);
       resultText.setText(gsonapp.toString());
               Log.v("Tag",gsonapp.toString());
               Log.d("MainActivity","version is"+gsonapp.getResponseHeader());
               }*/
               }
