/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.conor.catest1;

/**
 *
 * @author conorprunty
 */
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import com.google.gson.Gson;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

@Path("/webservice")
public class WebService {

    ArrayList list = new ArrayList();
    Gson gson;
    int sum;
    int avg;
    int total;

    int userSum;
    int userAvg;
    int userTotal;
    int userCardinality;

    public void populateList() {
        Random ran = new Random();
        int randomNum;
        for (int i = 0; i < 10; i++) {
            randomNum = ran.nextInt(100);
            list.add(randomNum);
        }

        sum = 0;
        avg = 0;
        total = 0;
    }

    @Path("/getlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getList(@Context UriInfo info) {
        populateList();
        gson = new Gson();

        return Response.status(200).entity(gson.toJson(list)).build();
    }

    @Path("/getdata")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getData(@Context UriInfo info) {
        populateList();
        Map<String, Integer> allDetails = new HashMap();
        int cardinality = list.size();

        for (int i = 0; i < cardinality; i++) {
            sum += Integer.parseInt(list.get(i).toString());
        }

        int average = sum / cardinality;

        Collections.sort(list);
        int median = Integer.parseInt(list.get(cardinality / 2).toString());

        for (int i = 0; i < cardinality; i++) {
            total += Integer.parseInt(list.get(i).toString());
        }

        allDetails.put("Cardinality", cardinality);
        allDetails.put("Average", average);
        allDetails.put("Median", median);
        allDetails.put("Sum", total);

        String jsonString = new Gson().toJson(allDetails);

        return Response.status(200).entity((jsonString) + "\n" + list.toString()).build();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postTestResponse(String data) {

        String result = "Result:\n" + data;

        return Response.status(200).entity(result).build();
    }

}

