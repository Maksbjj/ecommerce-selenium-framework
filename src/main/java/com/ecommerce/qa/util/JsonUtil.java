package com.ecommerce.qa.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.io.*;

public class JsonUtil {


    public static <T> T readJsonToPojo(Class<T> className, String jsonPath) {
        try {
        JsonReader reader = new JsonReader(new FileReader(jsonPath));
        return new Gson().fromJson(reader, className);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public static String getValueFromJson(String object,String value) {
//        return jsonData.getJSONObject(object).getString(value);
//    }
//
//    public static String getValueFromJsonArray(String object,int index,String value) {
//        String jsonValue = null;
//        JSONArray jsonArray = jsonData.getJSONArray(object);
//        for (int i = 0; i < jsonArray.length(); i++) {
//            jsonValue = jsonArray.getJSONObject(index).getString(value);
//        }
//        return jsonValue;
//    }
}
