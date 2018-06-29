package fzu.edu.teacher.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fzu.edu.MyApplication;
import fzu.edu.R;
import fzu.edu.entiy.Student;
import fzu.edu.entiy.Teacher;

/**
 * 个人信息子界面
 */
public class InfoTeacherFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_teacher, container, false);

        Teacher teacher = MyApplication.getTeacher();
        TextView textView = view.findViewById(R.id.item_stu_id);
        textView.setText(teacher.getTusername());

        TextView textView1= view.findViewById(R.id.item_stu_name);
        textView1.setText(teacher.getTname());

        TextView textView2=view.findViewById(R.id.item_stu_academy);
        textView2.setText(teacher.getTdept());

        TextView textView3=view.findViewById(R.id.item_stu_major);
        textView3.setText(teacher.getTmajor());

        return view;
    }

}
