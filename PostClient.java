/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.conorpractice;

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

        try {

            Client client = Client.create();

            WebResource webResource = client.resource("http://localhost:8080/api/webservice/post");
            ArrayList list = new ArrayList();
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter 5 numbers, one after the other");
            int userEntry1 = scan.nextInt();
            int userEntry2 = scan.nextInt();
            int userEntry3 = scan.nextInt();
            int userEntry4 = scan.nextInt();
            int userEntry5 = scan.nextInt();
            list.add(userEntry1);
            list.add(userEntry2);
            list.add(userEntry3);
            list.add(userEntry4);
            list.add(userEntry5);

            System.out.println("Entries " + userEntry1 + " and " + userEntry2 + " "
                    + "and " + userEntry3 + " and " + userEntry4 + " "
                    + "and " + userEntry5 + " added");

            int card = list.size();

            for (int i = 0; i < card; i++) {
                sum += Integer.parseInt(list.get(i).toString());
            }

            int avg = sum / card;

            Collections.sort(list);
            int median = Integer.parseInt(list.get(card / 2).toString());

            String input = "Cardinality: " + card + "\nSum: " + sum
                    + "\nAverage: " + avg + "\nMedian: " + median + "\n";

            input += new Gson().toJson(list);

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
