package fzu.edu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        Course course = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }
        TextView textView = view.findViewById(R.id.item_course_text);
        textView.setText(course.getName());

        return view;
    }
}
