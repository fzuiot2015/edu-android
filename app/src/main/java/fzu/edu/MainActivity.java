package fzu.edu;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import fzu.edu.entiy.Student;
import fzu.edu.fragment.CourseFragment;
import fzu.edu.fragment.InfoFragment;
import fzu.edu.fragment.StuListFragment;
import fzu.edu.fragment.SyllabusFragment;

import static fzu.edu.MyApplication.getContext;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SyllabusFragment syllabusFragment;
    private CourseFragment courseFragment;
    private InfoFragment infoFragment;
    private StuListFragment stuListFragment;// TODO: 2018/6/23 转移到教师用户界面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_syllabus);               //侧边栏默认选项
        navigationView.setNavigationItemSelectedListener(this);

        Student student=MyApplication.getStudent();

        View headView =navigationView.getHeaderView(0);
        TextView userNameView=headView.findViewById(R.id.nav_header_name);
        userNameView.setText(student.getSname());
        TextView userIdView=headView.findViewById(R.id.nav_header_id);
        userIdView.setText(student.getSusername());

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        syllabusFragment = new SyllabusFragment();
        transaction.add(R.id.main_fragment, syllabusFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    finish();
                }
            });

            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });

            builder.setMessage("确认退出？");
            builder.setTitle("退出");
            builder.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //TODO:右上角菜单点击事件
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        //TODO：侧边栏点击事件
        switch (item.getItemId()) {
            case R.id.nav_syllabus:
                if (syllabusFragment == null) {
                    syllabusFragment = new SyllabusFragment();
                    transaction.add(R.id.main_fragment, syllabusFragment).commit();
                } else {
                    transaction.show(syllabusFragment).commit();
                }
                break;

            case R.id.nav_course:
                if (courseFragment == null) {
                    courseFragment = new CourseFragment();
                    transaction.add(R.id.main_fragment, courseFragment).commit();
                } else {
                    transaction.show(courseFragment).commit();
                }
                break;

            case R.id.nav_info:
                if (infoFragment == null) {
                    infoFragment = new InfoFragment();
                    transaction.add(R.id.main_fragment, infoFragment).commit();
                } else {
                    transaction.show(infoFragment).commit();
                }
                break;

            // TODO: 2018/6/23 转移到教师用户界面
            case R.id.nav_stu:
                if (stuListFragment == null) {
                    stuListFragment = new StuListFragment();
                    transaction.add(R.id.main_fragment, stuListFragment).commit();
                } else {
                    transaction.show(stuListFragment).commit();
                }
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void hideFragment(FragmentTransaction transaction){
        //TODO：隐藏fragment
        if(syllabusFragment !=null){
            transaction.hide(syllabusFragment);
        }
        if(courseFragment!=null){
            transaction.hide(courseFragment);
        }
        if(infoFragment!=null){
            transaction.hide(infoFragment);
        }

        // TODO: 2018/6/23 转移到教师用户界面
        if(stuListFragment!=null){
            transaction.hide(stuListFragment);
        }
    }
}
