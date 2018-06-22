package fzu.edu;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static String API = "http://192.168.1.4:8080";
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static String getAPI() {
        return API;
    }

    public static void setAPI(String api) {
        API = api;
    }

    public static Context getContext() {
        return context;
    }
}
