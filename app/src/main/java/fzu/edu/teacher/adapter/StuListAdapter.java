package fzu.edu.teacher.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import fzu.edu.MyApplication;
import fzu.edu.R;
import fzu.edu.entiy.Report;
import fzu.edu.entiy.ReportItem;
import fzu.edu.entiy.Result;
import fzu.edu.entiy.Student;
import fzu.edu.teacher.fragment.StuListFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StuListAdapter extends ArrayAdapter<Report> {

    private int resourceId;
    private Activity activity;
    private StuListFragment fragment;

    public StuListAdapter(@NonNull Context context, int resource, List<Report> reports, StuListFragment fragment) {
        super(context, resource, reports);
        resourceId = resource;
        activity = (Activity) context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Report report = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }

        final Student student = report.getStudent();

        TextView textView = view.findViewById(R.id.item_stu_id);
        textView.setText(student.getSusername());
        TextView textView1 = view.findViewById(R.id.item_stu_major);
        textView1.setText(student.getSmajor());
        TextView textView2 = view.findViewById(R.id.item_stu_name);
        textView2.setText(student.getSname());
        TextView textView3 = view.findViewById(R.id.item_stu_academy);
        textView3.setText(student.getSdept());
        final EditText editText = view.findViewById(R.id.item_stu_score_edit);

        Button button = view.findViewById(R.id.item_stu_score_button);

        ReportItem reportItem = report.getReportItems().get(0);
        if (reportItem != null) {
            editText.setText(String.valueOf(reportItem.getScore()));
            button.setVisibility(view.INVISIBLE);
            editText.setEnabled(false);
        } else {
            SpannableString aa = new SpannableString("成绩未录入");
            AbsoluteSizeSpan ass = new AbsoluteSizeSpan(15, true);
            aa.setSpan(ass, 0, aa.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            editText.setHint(new SpannedString(aa));

            String cid = fragment.getCid();
            String sid = report.getStudent().getSid();
            button.setOnClickListener(new MyOnClickListener(cid, sid, editText));
        }

        return view;
    }

    class MyOnClickListener implements View.OnClickListener {
        private String cid;
        private String sid;
        private EditText editText;

        public MyOnClickListener(String cid, String sid, EditText editText) {
            this.cid = cid;
            this.sid = sid;
            this.editText = editText;
        }

        @Override
        public void onClick(View v) {
            setScore(cid, sid, editText.getText().toString());
        }
    }


    /**
     * 录入成绩
     */
    private void setScore(String cid, String sid, String score) {
        final Request request = new Request.Builder()
                .url(MyApplication.getAPI() + "/ReportServlet?method=add&sid="
                        + sid + "&cid=" + cid + "&score=" + score + "&phone=1").build();

        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, "连接服务器失败", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(activity, "成绩录入成功", Toast.LENGTH_SHORT).show();
                            fragment.refresh();
                        }
                    });
                } else {
                   activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "成绩录入失败", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}
