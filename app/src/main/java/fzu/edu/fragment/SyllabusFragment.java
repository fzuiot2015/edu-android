package fzu.edu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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
import fzu.edu.adapter.SyllabusAdapter;
import fzu.edu.entiy.Syllabus;
import fzu.edu.entiy.SyllabusItem;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SyllabusFragment extends Fragment {
    private String[][] contents = new String[6][7];
    private SyllabusAdapter syllabusAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_syllabus, container, false);

        GridView syllabusView = view.findViewById(R.id.syllabus);
        syllabusAdapter = new SyllabusAdapter();
        syllabusAdapter.setContent(contents, 6, 7);
        syllabusView.setAdapter(syllabusAdapter);
        getRequest();
        return view;
    }

    public void setCourseData() {
        contents = new String[6][7];
        contents[0][0] = "现代测试技术\nB211";
        contents[1][0] = "微机原理及应用\nE203";
        contents[2][0] = "电磁场理论\nA212";
        contents[3][0] = "传感器电子测量A\nC309";

        contents[0][1] = "数据结构与算法\nB211";
        contents[2][1] = "面向对象程序设计\nA309";
        contents[3][1] = "面向对象程序设计\nA309";

        contents[0][2] = "微机原理及应用\nE203";
        contents[1][2] = "电磁场理论\nA212";
        contents[2][2] = "现代测试技术\nB211";

        contents[0][3] = "面向对象程序设计\nA309";
        contents[1][3] = "传感器电子测量A\nC309";

        contents[0][4] = "数据结构与算法\nB211";

        contents[3][5] = "微机原理及应用\nE203";

        contents[5][6] = "微机原理及应用\nE203";
    }

    // TODO: 2018/6/23 调整接口
    private void getRequest() {

        String sid = MyApplication.getStudent().getSid();

        final Request request = new Request.Builder()
                .url(MyApplication.getAPI() + "/SyllabusServlet?method=findBySid&sid=" + sid + "&phone=1").build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "课表更新失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonRes = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<Result<Syllabus>>() {
                }.getType();
                Result<Syllabus> result = gson.fromJson(jsonRes, type);
                Syllabus syllabus = result.getData();
                setCourseData(syllabus.getSyllabusItems());
            }
        });
    }

    private void setCourseData(List<SyllabusItem> list) {
        for (SyllabusItem syllabusItem : list) {
            Course course = syllabusItem.getCourse();
            int courseTime1 = course.getTime1();
            int row = courseTime1 % 10 - 1;
            int col = courseTime1 / 10 - 1;
            contents[row][col] = course.getCname() + "\n" + course.getAddress();

            int courseTime2 = course.getTime2();
            row = courseTime2 % 10 - 1;
            col = courseTime2 / 10 - 1;
            contents[row][col] = course.getCname() + "\n" + course.getAddress();
        }
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "课表更新成功", Toast.LENGTH_SHORT).show();
                syllabusAdapter.notifyDataSetChanged();
            }
        });
    }

}
