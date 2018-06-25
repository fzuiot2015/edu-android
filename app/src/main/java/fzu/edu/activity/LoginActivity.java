package fzu.edu.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import fzu.edu.MyApplication;
import fzu.edu.R;
import fzu.edu.entiy.Result;
import fzu.edu.entiy.Student;
import fzu.edu.entiy.Teacher;
import fzu.edu.student.MainActivityForStudent;
import fzu.edu.teacher.MainActivityForTeacher;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 登录界面
 */
public class LoginActivity extends AppCompatActivity {

    private Context context = this;

    private AutoCompleteTextView mAccountView;
    private EditText mPasswordView;

    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAccountView = findViewById(R.id.login_input_account);
        mPasswordView = findViewById(R.id.register_input_password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                //当前焦点位于密码输入框，并按下键盘回车键时，进行预登录操作
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mRadioGroup = findViewById(R.id.radio_group_login);

        Button mLoginButton = findViewById(R.id.login_button);
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

    }

    /**
     * 预登录，检查账号以及密码的格式，若格式正确则进行正式的登录操作，否则不进行登录操作并提示用户
     */
    private void attemptLogin() {
        //错误信息初始化
        mAccountView.setError(null);
        mPasswordView.setError(null);

        //获取输入的账号和密码
        String account = mAccountView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean isValid = true;
        View focusView = null;

        //检查输入的密码格式是否合法
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_password_empty));
            focusView = mPasswordView;
            isValid = false;
        } else if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_password_invalid));
            focusView = mPasswordView;
            isValid = false;
        }

        //检查输入的账号格式是否合法
        if (TextUtils.isEmpty(account)) {
            mAccountView.setError(getString(R.string.error_account_empty));
            focusView = mAccountView;
            isValid = false;
        } else if (!isAccountValid(account)) {
            mAccountView.setError(getString(R.string.error_account_invalid));
            focusView = mAccountView;
            isValid = false;
        }

        if (isValid) {
            //如果输入的账号密码格式正确，进行正式的登录操作
            switch (mRadioGroup.getCheckedRadioButtonId()) {
                case R.id.radio_student_login:
                    studentLogin(account, password);
                    break;
                case R.id.radio_teacher_login:
                    teacherLogin(account, password);
                    break;
            }


        } else {
            //如果输入的账号密码格式错误，不进行登录操作，输入框重新获得输入焦点
            focusView.requestFocus();
        }
    }

    /**
     * 账号格式检查
     *
     * @param account 用于检查格式的账号
     * @return true:格式正确 false:格式错误
     */
    private boolean isAccountValid(String account) {
        //TODO:完善账号名格式检查
        return account.length() > 0;
    }

    /**
     * 密码格式检查
     *
     * @param password 用于检查格式的密码
     * @return true:格式正确 false:格式错误
     */
    private boolean isPasswordValid(String password) {
        //TODO: 完善密码格式检查
        return password.length() > 0;
    }

    /**
     * 学生登录
     *
     * @param account  账号
     * @param password 密码
     */
    private void studentLogin(String account, String password) {
        final Request request = new Request.Builder()
                .url(MyApplication.getAPI() + "/StudentServlet?method=login&susername="
                        + account + "&spassword=" + password + "&phone=1").build();

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
                    MyApplication.setTeacher(null);
                    MyApplication.setStudent(result.getData());
                    Intent intent = new Intent(LoginActivity.this, MainActivityForStudent.class);
                    LoginActivity.this.startActivity(intent);
                    LoginActivity.this.finish();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mPasswordView.setError(getString(R.string.error_password_incorrect));
                            mPasswordView.requestFocus();
                        }
                    });
                }
            }
        });
    }

    /**
     * 教师登录
     *
     * @param account  账号
     * @param password 密码
     */
    private void teacherLogin(String account, String password) {
        final Request request = new Request.Builder()
                .url(MyApplication.getAPI() + "/TeacherServlet?method=login&tusername="
                        + account + "&tpassword=" + password + "&phone=1").build();

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
                Type type = new TypeToken<Result<Teacher>>() {
                }.getType();
                Result<Teacher> result = gson.fromJson(jsonRes, type);

                if (result.getCode() == 1) {
                    MyApplication.setStudent(null);
                    MyApplication.setTeacher(result.getData());
                    Intent intent = new Intent(LoginActivity.this, MainActivityForTeacher.class);
                    LoginActivity.this.startActivity(intent);
                    LoginActivity.this.finish();
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mPasswordView.setError(getString(R.string.error_password_incorrect));
                            mPasswordView.requestFocus();
                        }
                    });
                }
            }
        });
    }

}

