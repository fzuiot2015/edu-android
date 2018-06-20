package fzu.edu.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import fzu.edu.R;

/**
 * 课程表适配器
 */
public class SyllabusAdapter extends BaseAdapter {

    private Context mContext;

    private String[][] contents;

    private int rowTotal;

    private int columnTotal;

    private int positionTotal;

    public SyllabusAdapter(Context context) {
        this.mContext = context;
    }

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
    public Object getItem(int position) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.course_item, null);
        }
        TextView textView = convertView.findViewById(R.id.text);

        //如果有课,那么添加数据
        if (getItem(position)!=null) {
            textView.setText((String) getItem(position));
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.color.courseItem);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int row = position / columnTotal;
                    int column = position % columnTotal;
                    String msg = "当前选中的是" + contents[row][column] + "课";
                    Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
        return convertView;
    }

    /**
     * 设置内容、行数、列数
     */
    public void setContent(String[][] contents, int row, int column) {
        this.contents = contents;
        this.rowTotal = row;
        this.columnTotal = column;
        positionTotal = rowTotal * columnTotal;
    }
}
