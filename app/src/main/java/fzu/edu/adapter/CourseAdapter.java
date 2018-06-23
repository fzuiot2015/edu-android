package fzu.edu.adapter;

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

import java.util.List;

import fzu.edu.Course;
import fzu.edu.R;

public class CourseAdapter extends ArrayAdapter<Course> {

    private int resourceId;

    public CourseAdapter(@NonNull Context context, int resource, List<Course> courses) {
        super(context, resource, courses);
        resourceId = resource;
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

        textView.setText(course.getName());

        TextView textView1=view.findViewById(R.id.item_course_teacher);
        textView1.setText(course.getTeacher());

        RelativeLayout layout=view.findViewById(R.id.item_course);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setPositiveButton("选课", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // TODO: 2018/6/23 增加点击事件
                        Toast.makeText(getContext(), "选课成功", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setMessage("确认选"+course.getName()+"课？");
                builder.setTitle("选课");
                builder.show();
            }
        });

        return view;
    }
}
