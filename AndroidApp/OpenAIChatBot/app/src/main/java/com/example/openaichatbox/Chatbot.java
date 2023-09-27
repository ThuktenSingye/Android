package com.example.openaichatbox;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class Chatbot {
    private static final String apiKey = "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"; // Replace with your API key
    public static String generateResponse(String messages) {

       return null;
    }
}
// try {
//         URL url = new URL("https://api.openai.com/v1/chat/completions");
//         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//         // Set the request method to POST
//         connection.setRequestMethod("POST");
//         // Set request headers
//         connection.setRequestProperty("Authorization", "Bearer " + apiKey);
//         connection.setRequestProperty("Content-Type", "application/json");
//         // Enable input/output streams
//         connection.setDoOutput(true);
//
//         // Create JSON request body
//         JSONArray messagesArray = new JSONArray();
//
//         // Add a system message (optional, but recommended)
//         JSONObject systemMessage = new JSONObject();
//         systemMessage.put("role", "system");
//         systemMessage.put("content", "You are a helpful assistant.");
//         messagesArray.put(systemMessage);
//
//         // Add user message
//         JSONObject userMessage = new JSONObject();
//         userMessage.put("role", "user");
//         userMessage.put("content", "Tell me a joke.");
//         messagesArray.put(userMessage);
//
//         // You can add more user or assistant messages as needed
//
//         JSONObject requestBody = new JSONObject();
//         requestBody.put("messages", messagesArray);
//         connection.connect();
//
//         // Write the request body to the output stream
//         try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
//         os.writeBytes(requestBody.toString());
//         }
//         // Get response code
//         int responseCode = connection.getResponseCode();
//         if (responseCode == HttpURLConnection.HTTP_OK) {
//         // Read and process the response
//         try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//         String responseText = in.lines().collect(Collectors.joining());
//         JSONObject jsonResponse = new JSONObject(responseText);
//         JSONArray choices = jsonResponse.getJSONArray("choices");
//         JSONObject firstChoice = choices.getJSONObject(0);
//         String message = firstChoice.getString("message").trim();
//         return message;
//         }
//         } else {
//         return "Error: HTTP " + responseCode + " - Unable to generate response";
//         }
//         } catch (Exception e) {
//         e.printStackTrace();
//         return "Error: Unable to generate response";
//         }
