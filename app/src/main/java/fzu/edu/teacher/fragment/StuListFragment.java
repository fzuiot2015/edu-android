package fzu.edu.teacher.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
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
import fzu.edu.entiy.Report;
import fzu.edu.entiy.Result;
import fzu.edu.teacher.adapter.StuListAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 学生列表子界面
 */
public class StuListFragment extends Fragment {
    private StuListAdapter stuListAdapter;
    private List<Report> reports = new ArrayList<>();
    private String cid;

    public static StuListFragment newInstance(String cid) {
        StuListFragment stuListFragment = new StuListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("cid", cid);
        stuListFragment.setArguments(bundle);
        return stuListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            cid = args.getString("cid");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stulist, container, false);
        ListView listView = view.findViewById(R.id.list_stu);
        stuListAdapter = new StuListAdapter(getActivity(), R.layout.item_stulist_score, reports,this);
        listView.setAdapter(stuListAdapter);
        getRequest();
        return view;
    }

    /**
     * 从服务器获取数据,通过课程号 cid 查询对应的学生列表
     */
    private void getRequest() {
        final Request request = new Request.Builder()
                .url(MyApplication.getAPI() + "/StudentServlet?method=findAllStudentByCid2&cid="
                        + cid + "&phone=1").build();

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
                Type type = new TypeToken<Result<List<Report>>>() {
                }.getType();
                Result<List<Report>> result = gson.fromJson(jsonRes, type);

                if (result.getCode() == 1) {

                    reports.clear();
                    reports.addAll(result.getData());

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "学生列表加载成功", Toast.LENGTH_SHORT).show();
                            stuListAdapter.notifyDataSetChanged();
                        }
                    });
                }else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "学生列表加载失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void refresh(){
        getRequest();
    }

    public String getCid() {
        return cid;
    }
}
