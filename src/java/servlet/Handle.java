/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.parseInt;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.json.*;
import java.util.*;

/**
 *
 * @author ec
 */
public class Handle {
     public static String ParseJsonResponse(String status){
        try {
            String api = "";
            int s = parseInt(status);
            switch(s) {
                case 1:
                    api = "https://data.tmd.go.th/api/WeatherForecastDaily/V1/";
                    URL url1 = new URL(api);
                    HttpURLConnection httpURLConnection1 = (HttpURLConnection) url1.openConnection();
                    httpURLConnection1.setRequestMethod("GET");

                    //adding header
                    httpURLConnection1.setRequestProperty("api-key", "2XodQZlfVlJRWRTUb66va83SNuxPiT39");

                    String line1 = "";
                    InputStreamReader inputStreamReader1=new InputStreamReader(httpURLConnection1.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader1);
                    StringBuilder response = new StringBuilder();
                    while ((line1 = bufferedReader.readLine()) != null){
                        response.append(line1);
                    }
                    bufferedReader.close();
                    JSONObject json = new JSONObject(response.toString());
                    JSONObject DailyForecast = json.getJSONObject("DailyForecast");
                    JSONArray RegionsForecast = DailyForecast.getJSONArray("RegionsForecast");
                    String temp1 = DailyForecast.getString("Date");
                    String temp2 = DailyForecast.getString("DescTh");
                    String tempRG[] = new String[100];
                    for (int i = 0; i < RegionsForecast.length(); ++i) {
                        JSONObject rec = RegionsForecast.getJSONObject(i);
                        tempRG[i] = rec.getString("RegionName");
                        //String loc = rec.getString("loc");
                        
                    }
                    String tempDes[] = new String[100];
                    for (int j = 0; j < RegionsForecast.length(); ++j) {
                        JSONObject rec = RegionsForecast.getJSONObject(j);
                        tempDes[j] = rec.getString("Description");
                        //String loc = rec.getString("loc");
                        
                    }
                    return "Date : "+temp1+"<br>"+"<br>"+"DailyForecast : "+temp2+"<br>"+"<br>"+tempRG[0]+" : "+tempDes[0]+"<br>"+"<br>"
                           +tempRG[1]+" : "+tempDes[1]+"<br>"+"<br>"+tempRG[2]+" : "+tempDes[2]+"<br>"+"<br>"+tempRG[3]+" : "+tempDes[3]+"<br>"+"<br>"
                           +tempRG[4]+" : "+tempDes[4]+"<br>"+"<br>"+tempRG[5]+" : "+tempDes[5]+"<br>"+"<br>"+tempRG[6]+" : "+tempDes[6]+"<br>"+"<br>";
                   
                case 2:
                    api = "https://data.tmd.go.th/api/Station/v1/?uid=demo&ukey=demokey";
                    URL url2 = new URL(api);
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) url2.openConnection();
                    httpURLConnection2.setRequestMethod("GET");

                    //adding header
                    httpURLConnection2.setRequestProperty("api-key", "2XodQZlfVlJRWRTUb66va83SNuxPiT39");

                    String line2 = "";
                    InputStreamReader inputStreamReader2=new InputStreamReader(httpURLConnection2.getInputStream());
                    BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                    StringBuilder response2 = new StringBuilder();
                    while ((line2 = bufferedReader2.readLine()) != null){
                        response2.append(line2);
                    }
                    bufferedReader2.close();
                    JSONObject json2 = XML.toJSONObject(response2.toString());  
                    JSONObject Stations = json2.getJSONObject("Stations");
                    JSONArray Station = Stations.getJSONArray("Station");
                    //System.out.println(Station.length());
                    String tempP[] = new String[126];
                    for (int i = 0; i < 126; ++i) {
                        JSONObject rec = Station.getJSONObject(i);
                        tempP[i] = rec.getString("Province");
                        //String loc = rec.getString("loc");
                        
                    }
                    String tempSN[] = new String[126];
                    for (int i = 0; i < 126; ++i) {
                        JSONObject rec = Station.getJSONObject(i);
                        tempSN[i] = rec.getString("StationNameThai");
                        //String loc = rec.getString("loc");
                        
                    }
                    String tempT[] = new String[126];
                    for (int i = 0; i < 126; ++i) {
                        JSONObject rec = Station.getJSONObject(i);
                        tempT[i] = rec.getString("StationType");
                        //String loc = rec.getString("loc");
                        
                    }
                    StringBuilder str = new StringBuilder();
                    for(int x=0;x<tempP.length;++x){
                        str = str.append((x+1)+"<br>"+"Province : "+tempP[x]+"<br>"+"<br>"+"StationNameThai : "+tempSN[x]+"<br>"+"<br>"+"StationType : "+tempT[x]+"<br>"+"<br>"+"<br>"+"<br>");
                    }
                    //String jsonString = json2.toString(4); 
                    //StringBuilder str1 = new StringBuilder("dfff");
                    String temppp = str.toString();
                return temppp;
            }
         
        }
        catch (Exception e){
            return  "error"+e;
        }
         return null;
     }
  }
