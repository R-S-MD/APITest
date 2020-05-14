package com.company;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.Scanner;

import static java.lang.Math.ceil;
import static java.lang.Math.sin;

public class Main {

    //private static HttpURLConnection connection;

    public static void main(String[] args) {
        //Scanner console = new Scanner(System.in); u need this

        /*try {
            URL url = new URL("https://jsonplaceholder.typicode.com/albums");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println(status);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        /* weather client api
        System.out.println("City Name: ");
        String city = console.nextLine();


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=f0345f589327d695ce92d3be2f91978d")).build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(System.out::println)
                .thenApply(Main::parse)
                .join();

        */



        /*

        HttpClient client2 = HttpClient.newHttpClient();
        HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("https://api.covid19api.com/summary")).build();

        client2.sendAsync(request2, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(System.out::println)
                .thenApply(Main::covid_19)
                .join();

                */

        /*

        HttpClient client3 = HttpClient.newHttpClient();
        HttpRequest request3 = HttpRequest.newBuilder().uri(URI.create("https://www.dictionaryapi.com/api/v3/references/sd4/json/algorithm?key=0b8cfa93-f0ba-469c-b79f-f82f17d3eab7")).build();

        client3.sendAsync(request3, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(System.out::println)
                .thenApply(Main::define)
                .join();
*/

        /*

        HttpClient client4 = HttpClient.newHttpClient();
        HttpRequest request4 = HttpRequest.newBuilder().uri(URI.create("http://data.fixer.io/api/latest?access_key=2595a4295288a45dc770b612d7b5ccb4")).build();

        client4.sendAsync(request4, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(System.out::println)
                .thenApply(Main::exchange)
                .join();
        */

        for(int x=0; x<2; x++){

            HttpClient client5 = HttpClient.newHttpClient();
            HttpRequest request5 = HttpRequest.newBuilder().uri(URI.create("https://icanhazdadjoke.com/slack")).build();

            String response = client5.sendAsync(request5, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    //.thenAccept(System.out::println)
                    //.thenApply(Main::joke)
                    .join();



            String joke = Main.joke(response);
            System.out.println(joke);



        }






    }



    public static String parse(String responseBody){
        /*
        JSONArray albums = new JSONArray(responseBody);
        for(int i=0 ; i< albums.length(); i++){
            JSONObject album = albums.getJSONObject(i);
            int id= album.getInt("id");
            int userId = album.getInt("userId");
            String title = album.getString("title");
            System.out.println(id +" " + title + " " + userId );
        }
        */
        //JSONArray weatherList = new JSONArray(responseBody);
        JSONObject weather = new JSONObject(responseBody);
        JSONObject weather1 = new JSONObject(weather.getJSONObject("main").toString());
        double temp =(weather1.getInt("temp"));
        System.out.println("The weather is: " + Math.round(temp-272.1) + " Degrees celcius");

        return null;
    }

    public static String covid_19(String responseBody){
        Scanner console = new Scanner(System.in);
        System.out.println("Country Name: ");
        String c = console.nextLine();

        JSONObject covid19 = new JSONObject(responseBody);
        for(int i=0; i<covid19.getJSONArray("Countries").length(); i++){
            JSONObject country = covid19.getJSONArray("Countries").getJSONObject(i);
            String name = country.getString("Country");
            if(name.equalsIgnoreCase(c)){
                int tConfirmed = country.getInt("TotalConfirmed");
                int tRecovered = country.getInt("TotalRecovered");
                System.out.println(name + ", Total confirmed: " + tConfirmed + ", Total Recovered: " + tRecovered );
                System.out.println(" ");
            }

        }

        return null;
    }



    public static String define(String responseBody){

        JSONArray words = new JSONArray(responseBody);

        JSONObject shortDef = new JSONObject(words.getJSONObject(0).toString());

        JSONArray shortDefArray = shortDef.getJSONArray("shortdef");
        for(int i=0; i<shortDefArray.length(); i++){
            System.out.println("definition "+(i+1)+": " + shortDefArray.getString(i) );
        }

        return null;
    }



    public static String exchange(String responseBody){

        int Amount = 5;
        String from = "USD";
        String to = "GBP";

        JSONObject exchange = new JSONObject(responseBody);
        JSONObject rates = new JSONObject(exchange.getJSONObject("rates").toString());
        System.out.println(rates.getDouble("GBP"));

        double divider = rates.getDouble(from);
        double multiplier = rates.getDouble(to);

        float exchage_rate =(float)((Amount/divider) * multiplier);

        System.out.println(Amount + " " + from + " is now: " + exchage_rate + " " + to);

        return null;
    }

    public static String joke(String responseBody){

        JSONObject joke = new JSONObject(responseBody);
        JSONArray joke1 = new JSONArray(joke.getJSONArray("attachments").toString());
        JSONObject joke2 = joke1.getJSONObject(0);
        String jokeFinal = joke2.getString("text");
        //System.out.println("Your joke: " + jokeFinal );
        String joker = "Your joke: " + jokeFinal;

        return joker;
    }



}
