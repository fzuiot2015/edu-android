package fzu.edu.student.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import fzu.edu.MyApplication;
import fzu.edu.R;
import fzu.edu.entiy.Course;
import fzu.edu.entiy.Result;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CourseListAdapter extends ArrayAdapter<Course> {

    private int resourceId;
    private Activity activity;

    public CourseListAdapter(@NonNull Context context, int resource, List<Course> courses) {
        super(context, resource, courses);
        resourceId = resource;
        activity = (Activity) context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Course course = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }
        TextView textView = view.findViewById(R.id.item_course_name);

        textView.setText(course.getCname());

        TextView textView1 = view.findViewById(R.id.item_course_teacher);
        textView1.setText(course.getTeacher().getTname());

        RelativeLayout layout = view.findViewById(R.id.item_course);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setPositiveButton("选课", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        select(course.getCid());
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setMessage("确认选" + course.getCname() + "课？");
                builder.setTitle("选课");
                builder.show();
            }
        });

        return view;
    }


    private void select(String cid) {
        String sid = MyApplication.getStudent().getSid();

        final Request request = new Request.Builder()
                .url(MyApplication.getAPI() + "/SyllabusServlet?method=select&cid="
                        +cid+"&sid="+sid+"&phone=1").build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "选课失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonRes = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<Result>() {
                }.getType();
                Result result = gson.fromJson(jsonRes, type);

                if (result.getCode() == 1) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "选课成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "选课失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}

