package fzu.edu.student.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import fzu.edu.MyApplication;
import fzu.edu.R;
import fzu.edu.entiy.Report;
import fzu.edu.entiy.Student;

public class StudentScoreForTeacher extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_StuScoreForTeacher, container, false);
        Student student = MyApplication.getStudent();

        Report report=new Report();

        ListView listView = view.findViewById(R.id.list_test);

        TextView mMajor=view.findViewById(R.id.item_stu_major);
        TextView mName=view.findViewById(R.id.item_stu_name);
        TextView mId=view.findViewById(R.id.item_stu_id);
        TextView mAcad =view.findViewById(R.id.item_stu_academy);
        EditText mScore=view.findViewById(R.id.item_stu_score_edit);
        Button mButton=view.findViewById(R.id.item_stu_score_button);

        mMajor.setText(student.getSmajor());
        mName.setText(student.getSname());
        mId.setText(student.getSusername());
        mAcad.setText(student.getSdept());


        return view;
    }

}
