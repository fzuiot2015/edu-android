package fzu.edu.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fzu.edu.R;
import fzu.edu.adapter.StuListAdapter;
import fzu.edu.entiy.Student;

public class StuListFragment extends Fragment {
    private StuListAdapter stuListAdapter;
    private List<Student> students = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_score, container, false);
        ListView listView = view.findViewById(R.id.list_stu);
        stuListAdapter = new StuListAdapter(getActivity(), R.layout.item_stulist, students);
        listView.setAdapter(stuListAdapter);
        return view;
    }

}
