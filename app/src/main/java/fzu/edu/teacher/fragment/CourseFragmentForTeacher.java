package fzu.edu.teacher.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import fzu.edu.MyApplication;
import fzu.edu.R;
import fzu.edu.entiy.Course;
import fzu.edu.entiy.Result;
import fzu.edu.teacher.adapter.CourseListAdapterForTeacher;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 课程列表子界面
 */
public class CourseFragmentForTeacher extends Fragment {
    private List<Course> courses = new ArrayList<>();
    private CourseListAdapterForTeacher courseListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list_for_teacher, container, false);

        ListView listView = view.findViewById(R.id.course_list_for_teacher);
        courseListAdapter = new CourseListAdapterForTeacher(getActivity(), R.layout.item_course_for_teacher, courses);
        listView.setAdapter(courseListAdapter);
        getRequest();
        return view;
    }

    /**
     * 从服务器获取数据
     */
    private void getRequest() {
        final Request request = new Request.Builder()
                .url(MyApplication.getAPI() + "/CourseServlet?method=findByTid&tid="
                        + MyApplication.getTeacher().getTid() + "&phone=1").build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "课程列表加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonRes = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<Result<List<Course>>>() {
                }.getType();
                Result<List<Course>> result = gson.fromJson(jsonRes, type);
                courses.clear();
                courses.addAll(result.getData());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "课表列表加载成功", Toast.LENGTH_SHORT).show();
                        courseListAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}
