package fzu.edu.teacher.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import fzu.edu.R;
import fzu.edu.entiy.Course;
import fzu.edu.teacher.fragment.StuListFragment;

/**
 * 选课列表适配器
 */
public class CourseListAdapterForTeacher extends ArrayAdapter<Course> {

    private int resourceId;
    private Activity activity;

    public CourseListAdapterForTeacher(@NonNull Context context, int resource, List<Course> courses) {
        super(context, resource, courses);
        resourceId = resource;
        activity = (Activity) context;
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
        TextView textView = view.findViewById(R.id.item_course_name_for_teacher);

        textView.setText(course.getCname());

        TextView textView1 = view.findViewById(R.id.item_course_teacher_for_teacher);
        textView1.setText("任课教师：" + course.getTeacher().getTname());

        RelativeLayout layout = view.findViewById(R.id.item_course_for_teacher);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StuListFragment stuListFragment = StuListFragment.newInsatnce(course.getCid());
                activity.getFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_for_teacher, stuListFragment, null)
                        .commit();
            }
        });

        return view;
    }

}

