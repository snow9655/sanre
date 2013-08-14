package com.hodanet.sanre.common.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConstants {

    private static String CONSTANT_FILE_PATH = "common.properties";
    public static String  BASE_API_URL       = "";
    public static String  MARKET_KEY         = "";

    static {
        Properties prop = new Properties();
        InputStream in = PropertiesConstants.class.getClassLoader().getResourceAsStream(CONSTANT_FILE_PATH);
        try {
            prop.load(in);
            BASE_API_URL = prop.getProperty("base_api_url");
            MARKET_KEY = prop.getProperty("market_key");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            }
        }
    }
}
