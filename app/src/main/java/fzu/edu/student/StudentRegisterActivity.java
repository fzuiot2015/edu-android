package fzu.edu.student;

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
import fzu.edu.entiy.Student;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StudentRegisterActivity extends AppCompatActivity {

    private Context context = this;

    private AutoCompleteTextView mAccount;
    private EditText mPassword;
    private EditText mPasswordCheck;
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_for_student);

        mAccount = findViewById(R.id.register_input_account_student);
        mName = findViewById(R.id.register_input_name_student);
        mPassword = findViewById(R.id.register_input_password_srudent);
        mPasswordCheck = findViewById(R.id.register_input_password_check_student);
        Button mRegister = findViewById(R.id.register_button_student);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mAccount.getText().toString();
                String password = mPassword.getText().toString();
                String passwordCheck = mPasswordCheck.getText().toString();
                String name = mName.getText().toString();
                String sdept = "物理与信息工程学院";
                String smajor = "物联网工程";

                boolean isValid = true;

                if (!password.equals(passwordCheck)) {
                    mPasswordCheck.setError("两次输入的密码不一致");
                    isValid = false;
                }else if (account.length() == 0) {
                    mAccount.setError("输入账号不能为空！");
                    isValid = false;
                } else if (account.length() != 9) {
                    mAccount.setError("请输入正确学号");
                    isValid = false;
                }else if (name.length() == 0) {
                    mName.setError("输入姓名不能为空！");
                    isValid = false;
                }
                if (isValid) {
                    String url = MyApplication.getAPI() + "/StudentServlet?method=regist&susername="
                            + account + "&spassword=" + password + "&sname=" + name + "&sdept=" + sdept + "&smajor="
                            + smajor + "&phone=1";
                    register(url);
                }
            }
        });
    }

    private void register(String url) {
        final Request request = new Request.Builder()
                .url(url).build();


        OkHttpClient client = new OkHttpClient();
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
            public void onResponse(Call call, Response response) throws IOException {
                final String jsonRes = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<Result<Student>>() {
                }.getType();
                Result<Student> result = gson.fromJson(jsonRes, type);

                if (result.getCode() == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
                        }
                    });
                    MyApplication.setTeacher(null);
                    MyApplication.setStudent(result.getData());
                    Intent intent = new Intent(StudentRegisterActivity.this, MainActivityForStudent.class);
                    StudentRegisterActivity.this.startActivity(intent);
                    StudentRegisterActivity.this.finish();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mAccount.setError("该账号已被注册");
                            mAccount.requestFocus();
                        }
                    });
                }
            }
        });
    }

}
