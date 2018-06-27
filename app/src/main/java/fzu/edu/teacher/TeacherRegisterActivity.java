package fzu.edu.teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import fzu.edu.MyApplication;
import fzu.edu.R;
import fzu.edu.entiy.Result;
import fzu.edu.entiy.Teacher;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TeacherRegisterActivity extends AppCompatActivity {

    private Context context = this;


    private AutoCompleteTextView mAccount;
    private EditText mPassword;
    private EditText mPasswordCheck;
    private EditText mName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_for_teacher);

        mAccount = findViewById(R.id.register_input_account_teacher);
        mName = findViewById(R.id.register_input_name_teacher);
        mPassword = findViewById(R.id.register_input_password_teacher);
        mPasswordCheck = findViewById(R.id.register_input_password_check_teacher);
        Button mRegister = findViewById(R.id.register_button_teacher);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mAccount.getText().toString();
                String password = mPassword.getText().toString();
                String passwordCheck = mPasswordCheck.getText().toString();
                String name = mName.getText().toString();
                String dept = "物理与信息工程学院";
                String major = "物联网工程";

                boolean isValid = true;
                if (!password.equals(passwordCheck)) {
                    mPasswordCheck.setError("两次输入的密码不一致");
                    isValid = false;
                } else if (account.isEmpty()) {
                    mAccount.setError("输入账号不能为空！");
                    isValid = false;
                } else if (account.length() < 3 || account.length() > 12) {
                    mAccount.setError("输入账号长度应为[3-12]个字符！");
                    isValid = false;
                } else  if(password.isEmpty()){
                    mPassword.setError("输入密码不能为空！");
                    isValid = false;
                }else if(password.length()<3||password.length()>12){
                    mPassword.setError("输入密码长度应为[3-12]个字符！");
                    isValid = false;
                }else if (name.isEmpty()) {
                    mName.setError("输入姓名不能为空！");
                    isValid = false;
                }else if(name.length()<2||name.length()>4){
                    mName.setError("输入姓名长度应为[2-4]个字符！");
                    isValid = false;
                }

                if (isValid) {
                    register(account, password, name, dept, major);
                }
            }
        });
    }



    private void register(String account, String password, String name, String dept, String major) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("method", "regist");
        builder.add("tusername", account);
        builder.add("tpassword", password);
        builder.add("tname", name);
        builder.add("tdept", dept);
        builder.add("tmajor", major);
        builder.add("phone", "1");

        final Request request = new Request.Builder()
                .url(MyApplication.getAPI() + "/TeacherServlet").post(builder.build()).build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "连接服务器失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String jsonRes = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<Result<Teacher>>() {
                }.getType();
                Result<Teacher> result = gson.fromJson(jsonRes, type);

                if (result.getCode() == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    MyApplication.setStudent(null);
                    MyApplication.setTeacher(result.getData());
                    Intent intent = new Intent(TeacherRegisterActivity.this, MainActivityForTeacher.class);
                    TeacherRegisterActivity.this.startActivity(intent);
                    TeacherRegisterActivity.this.finish();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, jsonRes, Toast.LENGTH_LONG).show();
                            mAccount.setError("该账号已被注册");
                            mAccount.requestFocus();
                        }
                    });
                }
            }
        });

    }


}
