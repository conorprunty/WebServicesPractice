/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.conor.catest1;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.ClientResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author conorprunty
 */
public class PostClient {

    public static void main(String[] args) {

        int sum = 0;
        String out = "";

        try {

            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:8080/api/webservice/post");
            ArrayList list = new ArrayList();
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter numbers. To stop, enter '0'.");
            int userEntry;
            while (scan.hasNextLine()) {
                userEntry = scan.nextInt();
                if (userEntry != 0) {
                    list.add(userEntry);
                } else {
                    System.out.println("You have stopped the list");
                    break;
                }
            }

            for (Object userList : list) {
                out += userList.toString() + ", ";
            }

            System.out.println("User entries are: " + out);

            int card = list.size();

            for (int i = 0; i < card; i++) {
                sum += Integer.parseInt(list.get(i).toString());
            }

            int avg = sum / card;

            Collections.sort(list);
            int median = Integer.parseInt(list.get(card / 2).toString());

            System.out.println("\nCardinality: " + card + "\nSum: " + sum
                    + "\nAverage: " + avg + "\nMedian: " + median + "\n");

            String input = new Gson().toJson(list);

            ClientResponse response = webResource.type("application/json")
                    .post(ClientResponse.class, input);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }
            System.out.println("Result from Server .... \n");
            String output = response.getEntity(String.class);
            System.out.println(output);

        } catch (Exception e) {
        }

    }
}
