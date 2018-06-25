package fzu.edu.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fzu.edu.MyApplication;
import fzu.edu.R;

// TODO: 2018/6/25 移除测试类

/**
 * 测试用，更改Api地址
 */
public class SettingActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Button button = findViewById(R.id.setting_api_ok);
        editText = findViewById(R.id.setting_api);
        editText.setText(MyApplication.getAPI());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyApplication.setAPI(editText.getText().toString());
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                startActivity(intent);
                SettingActivity.this.finish();
            }
        });
    }
}
