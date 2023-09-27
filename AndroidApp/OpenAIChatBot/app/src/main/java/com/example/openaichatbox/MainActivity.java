package com.example.openaichatbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import android.content.IntentFilter;
import android.icu.text.RelativeDateTimeFormatter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String apiKey = "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";
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();
    private SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    TextView welcomeText;
    EditText queryText;
    ImageButton sendBtn;
    List<ChatMessage> messageList;
    ChatAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        welcomeText = findViewById(R.id.welcomeText);
        queryText = findViewById(R.id.queryText);
        sendBtn = findViewById(R.id.send_btn);
        messageList = new ArrayList<>();

//        set up recycle view
        messageAdapter = new ChatAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // existing item will be push upwards
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        sendBtn.setOnClickListener((v)->{
            String query = queryText.getText().toString().trim();
            addMessage(query, ChatMessage.SENT_BY_ME);
            queryText.setText("");
            callAPI(query);
            welcomeText.setVisibility(View.GONE);
        });
    }
    public void addMessage(String content,String role){
        // below code must run in UI thread

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new ChatMessage(content, role));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount()); // scroll to the last item
            }
        });
    }
    public void addResponse(String response){
        addMessage(response,ChatMessage.SENT_BY_BOT);
    }
    public void callAPI(String query){
//        okhttp
        JSONObject jsonBody = new JSONObject();
        // create jsonBody which will be used by request body
        try {
            jsonBody.put("model", "gpt-3.5-turbo-instruct");
            jsonBody.put("prompt", query);
            jsonBody.put("max_tokens", 4000);
            jsonBody.put("temperature", 0);
        }catch (Exception e){
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
        }
        // convert jsonBody to requestBody that will be send to HTTP post request.
        // requestBody is an format that server can understand hence jsonBody need to convert into requestBody
        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        // creating HTTP request
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization", "Bearer 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")
                .post(body)
                .build();
//        below code make an asynchronouse call
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                this method is called if request fail
                addResponse("Failed to load resposne due to "+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                this method is called if request is successful
                if (response.isSuccessful()) {
                    try {
                        String responseBodyString = response.body().string(); // Save the response body to a variable
                        JSONObject jsonObject = new JSONObject(responseBodyString);
                        JSONArray jsonArray = jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        // Handle parsing error
                        e.printStackTrace();
                    }
                } else {
                    addResponse("Failed to load response due to " + response.body().string());
                }
            }
        });
    }
}


//    private BroadcastReceiver connectivityReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            if (isConnected()){
//                query.setOnTouchListener(new View.OnTouchListener() {
//                    @Override
//                    public boolean onTouch(View view, MotionEvent event) {
//                        final int DRAWABLE_RIGHT = 2;
//                        if(event.getAction() == MotionEvent.ACTION_UP) {
//                            if(event.getRawX() >= (query.getRight() - query.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
//                                // your action here
//                                String query_text = query.getText().toString();
//                                Toast.makeText(MainActivity.this, query_text, Toast.LENGTH_LONG).show();
//                                new FetchData(query_text).execute();
//
//                                return true;
//                            }
//                        }
//                        return false;
//                    }
//                });
//            }else{
//                Toast.makeText(MainActivity.this, "Internet is not connected", Toast.LENGTH_LONG).show();
//            }
//        }
//    };


//    @Override
//    protected void onResume() { // called when activity is brought into foreground
//        super.onResume();
//        // Register the BroadcastReceiver to listen for connectivity changes
//        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        // act as filter that define which broadcast event the broadcast receiver should listen to
//        registerReceiver(connectivityReceiver, intentFilter);
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // Unregister the BroadcastReceiver when the activity is paused
//        unregisterReceiver(connectivityReceiver);
//    }
//    public boolean isConnected(){
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
//        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
//    }
//    public class FetchData extends AsyncTask<String,Void, String>{
//        String queryText;
//        public FetchData(String queryText){
//            this.queryText = queryText;
//        }
//        @Override
//        protected String doInBackground(String... Strings) {
//            return Chatbot.generateResponse(queryText);
//        }
//        @Override
//        protected void onPostExecute(String s) {
//            Toast.makeText(MainActivity.this, String.valueOf(s), Toast.LENGTH_LONG).show();
//        }
//
//    }