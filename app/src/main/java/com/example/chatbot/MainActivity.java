package com.example.chatbot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class ChatGPTAPI {

    private static final String API_KEY = "AIzaSyCP_Xw6y_WbVju4Nj37COKoRKD10Vzvw1E";
    private static final String API_URL = "https://api.openai.com/v1/completions/";

    public static String generateResponse(String prompt) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + API_KEY);
            con.setDoOutput(true);

            String requestBody = "{\"prompt\": \"" + prompt + "\", \"max_tokens\": 150}";
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(requestBody);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String prompt = "Once upon a time,";
        String response = generateResponse(prompt);
        System.out.println(response);
    }
}