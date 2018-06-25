package fzu.edu.student.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import fzu.edu.MyApplication;
import fzu.edu.R;
import fzu.edu.entiy.Student;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 个人信息子界面
 */
public class InfoFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        Student student = MyApplication.getStudent();
        TextView textView = view.findViewById(R.id.item_stu_id);
        textView.setText(student.getSusername());

        TextView textView1= view.findViewById(R.id.item_stu_name);
        textView1.setText(student.getSname());

        TextView textView2=view.findViewById(R.id.item_stu_academy);
        textView2.setText(student.getSdept());

        TextView textView3=view.findViewById(R.id.item_stu_major);
        textView3.setText(student.getSmajor());

        return view;
    }

}
