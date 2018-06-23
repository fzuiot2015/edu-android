package fzu.edu.fragment;

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

import fzu.edu.Course;
import fzu.edu.MyApplication;
import fzu.edu.R;
import fzu.edu.adapter.StuListAdapter;
import fzu.edu.entiy.Result;
import fzu.edu.entiy.Student;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StuListFragment extends Fragment {
    private StuListAdapter stuListAdapter;
    private List<Student> students = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stulist, container, false);
        ListView listView = view.findViewById(R.id.list_stu);
        stuListAdapter = new StuListAdapter(getActivity(), R.layout.item_stulist, students);
        listView.setAdapter(stuListAdapter);
        return view;
    }

    /**
     * 从服务器获取数据
     */
    private void getRequest() {
        final Request request = new Request.Builder()
                .url("http://2119574u5y.iask.in:10678/Amadeus/StudentServlet?method=findAllStudentByCid2&cid=2c8eb2ec8d6c4c06959a9570d794de35&phone=1").build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "学生列表加载失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonRes = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<Result<List<Student>>>() {
                }.getType();
                Result<List<Student>> result = gson.fromJson(jsonRes, type);
                students.clear();
                students.addAll(result.getData());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "学生列表加载成功", Toast.LENGTH_SHORT).show();
                        stuListAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }


}
