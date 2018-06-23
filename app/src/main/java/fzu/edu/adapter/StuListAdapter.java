package fzu.edu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import fzu.edu.Course;
import fzu.edu.util.Student;

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


        return view;
    }
}
