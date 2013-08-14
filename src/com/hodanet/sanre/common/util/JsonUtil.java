package com.hodanet.sanre.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtil {

    /**
     * 将json对象转化为list对象
     * 
     * @param key
     * @param jsonString
     * @return
     */
    public static ArrayList<Map<String, Object>> JsonObjToList(String key, String jsonString) {
        ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonarray = jsonObject.getJSONArray(key);
            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonObject2 = jsonarray.getJSONObject(i);
                Map<String, Object> map = new HashMap<String, Object>();
                Iterator<String> iterator = jsonObject2.keys();
                while (iterator.hasNext()) {
                    String json_key = iterator.next();
                    Object json_value = jsonObject2.get(json_key);
                    if (json_value == null) {
                        json_value = "";
                    }
                    map.put(json_key, json_value);
                }
                list.add(map);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return list;
    }
}
