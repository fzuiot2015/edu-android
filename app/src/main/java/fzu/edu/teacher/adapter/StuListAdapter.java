package fzu.edu.teacher.adapter;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import fzu.edu.R;
import fzu.edu.entiy.Report;
import fzu.edu.entiy.ReportItem;
import fzu.edu.entiy.Student;

public class StuListAdapter extends ArrayAdapter<Report> {

    private int resourceId;

    public StuListAdapter(@NonNull Context context, int resource, List<Report> reports) {
        super(context, resource, reports);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Report report = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        } else {
            view = convertView;
        }

        Student student=report.getStudent();

        TextView textView=view.findViewById(R.id.item_stu_id);
        textView.setText(student.getSusername());
        TextView textView1=view.findViewById(R.id.item_stu_major);
        textView1.setText(student.getSmajor());
        TextView textView2=view.findViewById(R.id.item_stu_name);
        textView2.setText(student.getSname());
        TextView textView3=view.findViewById(R.id.item_stu_academy);
        textView3.setText(student.getSdept());
        EditText editText=view.findViewById(R.id.item_stu_score_edit);

        Button button=view.findViewById(R.id.item_stu_score_button);

        ReportItem reportItem=report.getReportItems().get(0);
        if(reportItem!=null){
            editText.setText(String.valueOf(reportItem.getScore()));
            button.setVisibility(view.INVISIBLE);
            editText.setEnabled(false);
        }else {
            SpannableString aa=new SpannableString("成绩未录入");
            AbsoluteSizeSpan ass= new AbsoluteSizeSpan(15,true);
            aa.setSpan(ass,0,aa.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            editText.setHint(new SpannedString(aa));


        }




        return view;
    }
}
