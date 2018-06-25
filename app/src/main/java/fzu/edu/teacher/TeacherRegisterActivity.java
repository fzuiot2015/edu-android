package fzu.edu.teacher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import fzu.edu.R;

public class TeacherRegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_for_student);

        AutoCompleteTextView mAccount=findViewById(R.id.register_input_account_student);
        EditText mId=findViewById(R.id.register_input_id_student);
        EditText mName=findViewById(R.id.register_input_name_student);
        EditText mPassword=findViewById(R.id.register_input_password_srudent);
        EditText mPasswordCheck=findViewById(R.id.register_input_password_check_student);
        Button mRegister=findViewById(R.id.register_button_student);
    }
}
