/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.conor.catest1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author conorprunty
 */
public class Httppractice {

    /**
     * @param args the command line arguments
     */
    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        Httppractice http = new Httppractice();

        http.getRequest();

    }

    // HTTP GET request
    private void getRequest() throws Exception {

        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a city:");
        String userAns = scan.next();

        String url = "http://api.openweathermap.org/data/2.5/forecast?q=" + userAns + "&mode=json&appid=b6ebccaaa6b5c659d5c39405c2f9fa61";
        //String url = "http://www.google.com/search?q=conorprunty";

        //https://www.mkyong.com/java/how-to-send-http-request-getpost-in-java/
        URL object = new URL(url);
        try {
            HttpURLConnection httpConnection = (HttpURLConnection) object.openConnection();

            // using a GET method
            httpConnection.setRequestMethod("GET");

            //request header 
            httpConnection.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = httpConnection.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            //System.out.println("Response Code : " + responseCode);
            System.out.println();

            if (responseCode == 200) {
                System.out.println("Successful Connection!");
            }

            System.out.println();

            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpConnection.getInputStream()))) {
                String input;
                while ((input = in.readLine()) != null) {
                    response.append(input);
                }
            }

            //print result
            System.out.println(response.toString());
        } catch (Exception e) {
            System.out.println("Something went wrong, please check the API key");
            System.out.println();
        }

    }

}
