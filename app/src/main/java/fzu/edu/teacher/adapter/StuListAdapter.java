package fzu.edu.teacher.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fzu.edu.R;
import fzu.edu.entiy.Report;
import fzu.edu.entiy.ReportItem;
import fzu.edu.entiy.Student;

public class StuListAdapter extends ArrayAdapter<Student> {

    private int resourceId;

    public StuListAdapter(@NonNull Context context, int resource, List<Student> students) {
        super(context, resource, students);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Student student = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }

        Report report=new Report();
        student=report.getStudent();



        TextView textView=view.findViewById(R.id.item_stu_id);
        textView.setText(student.getSusername());
        TextView textView1=view.findViewById(R.id.item_stu_major);
        textView1.setText(student.getSmajor());
        TextView textView2=view.findViewById(R.id.item_stu_name);
        textView2.setText(student.getSname());
        TextView textView3=view.findViewById(R.id.item_stu_academy);
        textView3.setText(student.getSdept());


        return view;
    }
}
