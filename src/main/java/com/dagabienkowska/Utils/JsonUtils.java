package com.dagabienkowska.Utils;

import com.dagabienkowska.shop.JsonPOJO;
import com.google.gson.Gson;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JsonUtils {

    private static final Logger LOGGER = Logger.getLogger(JsonUtils.class.getName());//log z tej klasy

    private static final String JSONPATH =
            "C:\\Users\\Froggy\\Desktop\\kurs_java\\Projekty\\MiniShop\\src\\main\\webapp\\WEB-INF\\jsonV1.json";
    private static Gson gson = new Gson();

    public static JsonPOJO getJson(){
        JsonPOJO jsonPOJO;
        try{
            InputStream is = new FileInputStream(JSONPATH);
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isr);
            jsonPOJO = gson.fromJson(reader, JsonPOJO.class);
            is.close();
            LOGGER.log(Level.INFO, "Fetched json" + jsonPOJO);
        } catch (IOException e){
            LOGGER.log(Level.SEVERE, "IOException", e);
            throw new RuntimeException("Cannot find file");
        }

        return jsonPOJO;
    }

    public static void writeJson(JsonPOJO jsonAccess){
        String json = gson.toJson(jsonAccess, JsonPOJO.class);

        try{
            FileWriter fileWriter = new FileWriter(JSONPATH);
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e){
            LOGGER.log(Level.SEVERE, "IOException", e);
            throw new RuntimeException("Cannot find file");
        }
    }

}
