package fzu.edu;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    private static final String TEST1="http://2119574u5y.iask.in:10678/Amadeus";
    private static final String TEST2="http://192.168.1.4:8080";

    private static String API = TEST2;
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
