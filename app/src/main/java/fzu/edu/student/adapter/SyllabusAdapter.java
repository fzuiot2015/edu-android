package fzu.edu.student.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import fzu.edu.R;
import fzu.edu.entiy.Course;

import static fzu.edu.MyApplication.getContext;

/**
 * 课程表适配器
 */
public class SyllabusAdapter extends BaseAdapter {

    private Course[][] contents;

    private int rowTotal;

    private int columnTotal;

    private int positionTotal;

    /**
     * 要绑定的条目的数目,即格子的数量
     */
    @Override
    public int getCount() {
        return positionTotal;
    }

    /**
     * 获取条目的id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 根据一个索引（位置）获得该位置的对象
     */
    @Override
    public Course getItem(int position) {
        //求余得到二维索引
        int column = position % columnTotal;
        //求商得到二维索引
        int row = position / columnTotal;
        return contents[row][column];
    }

    /**
     * 获取该条目要显示的界面
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_syllabus, null);
        }
        TextView textView = convertView.findViewById(R.id.item_syllabus_name);
        TextView textView1=convertView.findViewById(R.id.item_syllabus_addr);
        RelativeLayout relativeLayout=convertView.findViewById(R.id.item_syllabus);


        //如果有课,那么添加数据
        Course course=getItem(position);
        if ( course!= null) {
            textView.setText(course.getCname());
            textView1.setText(course.getAddress());
            relativeLayout.setBackgroundResource(R.color.courseItem);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int row = position / columnTotal;
                    int column = position % columnTotal;
                    String msg = "当前选中的是" + contents[row][column].getCname() + "课";
                    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }

    /**
     * 设置内容、行数、列数
     */
    public void setContent(Course[][] contents, int row, int column) {
        this.contents = contents;
        this.rowTotal = row;
        this.columnTotal = column;
        positionTotal = rowTotal * columnTotal;
    }
}
