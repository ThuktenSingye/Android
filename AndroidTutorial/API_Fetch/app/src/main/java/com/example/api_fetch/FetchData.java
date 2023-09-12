package com.example.api_fetch;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchData extends AsyncTask<Void, Void, String> {
    private final Context context;
    private TextView textView;

    public FetchData(Context context, TextView textView) {

        this.context = context;
        this.textView = textView;
    }

    @Override
    protected String doInBackground(Void... params) {
        // Perform the API data retrieval here
        String apiData = fetchDataFromApi();
        return apiData;
    }
    @Override
    protected void onPostExecute(String result) {
        // Update the UI with the fetched data
        Toast.makeText(context, "Data Fetch " , Toast.LENGTH_LONG).show();
        textView.setVisibility(View.VISIBLE);
        textView.setText(result.toString());
    }

    private String fetchDataFromApi() {
//           implement the data retrieval logic here
        try{
//                create url for api endpoint
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=search+terms&key=AIzaSyC9FRCpWsHIEmwpbrCcA8y8VCq9HK_2GsQ");
            // open the connection to url
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                set up connection properties
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(10000);
            
//                connect to url
            connection.connect();
            // check for respone

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                // read response data
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder stringBuilder =  new StringBuilder(); // dynamic string concatenation
                String line;
                while ((line=reader.readLine())!= null){
                    stringBuilder.append(line);
                }
                reader.close();
                String jsonData = stringBuilder.toString();
                JSONObject jsonObject = new JSONObject(jsonData);
                JSONArray resultsArray = jsonObject.getJSONArray("items");
                if (resultsArray.length() > 0) {
                    JSONObject userObject = resultsArray.getJSONObject(0);
                    JSONObject nameObject = userObject.getJSONObject("volumeInfo");
                    JSONArray authors_list = nameObject.getJSONArray("authors");
                    ArrayList<String> authorsList = new ArrayList<>();

                    // Iterate through the authors array
                    for (int i = 0; i < authors_list.length(); i++) {
                        String author = authors_list.getString(i);
                        authorsList.add(author);
                    }

                    String title = nameObject.getString("title");
                    String description = nameObject.getString("description");


                    String authors = String.join(", ",authorsList);

//                    return new BookInfo(title, authors, description);
//
                    return "Title:"+ title+"\nAuthor:"+ String.join(", ", authorsList)+"\nDescription:"+ description;
                } else {
                    return "No user data found.";
                }

//                return stringBuilder.toString(); // converting json to string
            }else{
                return "HTTP Error:"+ connection.getResponseCode();
            }

        }catch (Exception e){
            e.printStackTrace();
            return "Error:"+ e.getMessage();
        }

    }
}