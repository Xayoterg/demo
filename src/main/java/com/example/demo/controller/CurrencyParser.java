package com.example.demo.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

public class CurrencyParser {
    static final String APP_TOKEN = "229aaa50842143d4b2984d5dd518592b";
    static final String SITE_URL = "https://openexchangerates.org/api/";

    public static String start()throws Exception{
        LocalDate date = LocalDate.now();
        date = date.minusDays(1);
        System.out.println(date);
        double today = getRate(SITE_URL + "latest.json?app_id=" + APP_TOKEN);

        double yesterday = getRate(SITE_URL +"historical/"+date+".json?app_id=" + APP_TOKEN);
        //   String gif=null;


        //тернарный оператор вместо if else
        String gif = getGIF(today > yesterday ? "rich" : "broke");

        //   gif=today>yesterday?getGIF("rich"):getGIF("broke");//тернарный оператор вместо if else

        //   if (today > yesterday)gif= getGIF("rich");
        //    else gif= getGIF("broke");

        System.out.println(gif);
        return gif;

    }
    static double getRate(String siteUrl) throws Exception {
        URL url = new URL(siteUrl);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(content.toString());
        JSONObject rates = (JSONObject) jsonParser.parse(jsonObject.get("rates").toString());
        System.out.println(rates.get("RUB"));

        double result = Double.parseDouble(rates.get("RUB").toString());
        return result;
    }
    static String getGIF(String q) throws Exception {
        final String API_KEY = "ecf05e478d75418986fec0a3840309a7ab2f5777b2e753c9";
        URL url = new URL("https://media1.giphy.com/media/f2ExpHhfiPJNceqORt/giphy.gif?cid=" + API_KEY + "&amp;rid=giphy.gif&amp;ct=" + q);
        URLConnection urlConnection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);


        }
        in.close();

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(content.toString());
        int randomInt = (int) (Math.random() * 49);


        JSONArray data = (JSONArray) jsonParser.parse(jsonObject.get("data").toString());
        //  String gifInfo =arr.get(randomInt).toString();

        JSONObject jsonGif = (JSONObject) jsonParser.parse(data.get(randomInt).toString());
        JSONObject images = (JSONObject) jsonParser.parse(jsonGif.get("images").toString());
        JSONObject original = (JSONObject) jsonParser.parse(images.get("original").toString());
        String result = original.get("url").toString();


        return result;
    }

}
