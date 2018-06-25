package fzu.edu;

import android.app.Application;
import android.content.Context;

import fzu.edu.entiy.Student;
import fzu.edu.entiy.Teacher;

public class MyApplication extends Application {
    private static final String TEST1 = "http://2119574u5y.iask.in:10678/Amadeus";
    private static final String TEST2 = "http://192.168.1.4:8080";

    private static String API = TEST1;
    private static Context context;
    private static Student student;
    private static Teacher teacher;

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

    public static Student getStudent() {
        return student;
    }

    public static void setStudent(Student student) {
        MyApplication.student = student;
    }

    public static Teacher getTeacher() {
        return teacher;
    }

    public static void setTeacher(Teacher teacher) {
        MyApplication.teacher = teacher;
    }
}
