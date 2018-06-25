package fzu.edu.teacher;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
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

import fzu.edu.MyApplication;
import fzu.edu.R;
import fzu.edu.activity.LoginActivity;
import fzu.edu.entiy.Teacher;
import fzu.edu.teacher.fragment.StuListFragment;

import static fzu.edu.MyApplication.getContext;

public class MainActivityForTeacher extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private StuListFragment stuListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_for_teacher);

        Toolbar toolbar = findViewById(R.id.toolbar_for_teacher);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout_teacher);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view_for_teacher);
        navigationView.setCheckedItem(R.id.nav_stu);               //侧边栏默认选项
        navigationView.setNavigationItemSelectedListener(this);

        Teacher teacher = MyApplication.getTeacher();

        View headView = navigationView.getHeaderView(0);
        TextView userNameView = headView.findViewById(R.id.nav_header_name_teacher);
        userNameView.setText(teacher.getTname());
        TextView userIdView = headView.findViewById(R.id.nav_header_id_teacher);
        userIdView.setText(teacher.getTid());

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        stuListFragment = new StuListFragment();
        transaction.add(R.id.main_fragment_for_student, stuListFragment).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout_teacher);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
        //右上角菜单
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
//        hideFragment(transaction);

        //TODO：侧边栏点击事件
        switch (item.getItemId()) {
            case R.id.nav_stu:
                if (stuListFragment == null) {
                    stuListFragment = new StuListFragment();
                    transaction.replace(R.id.main_fragment_for_student, stuListFragment).commit();
                } else {
                    transaction.replace(R.id.main_fragment_for_student, stuListFragment).commit();
                }
                break;

            case R.id.nav_logout:
                Intent intent = new Intent(MainActivityForTeacher.this, MainActivityForTeacher.class);
                MainActivityForTeacher.this.startActivity(intent);
                MainActivityForTeacher.this.finish();
                MyApplication.setTeacher(null);
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout_teacher);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
