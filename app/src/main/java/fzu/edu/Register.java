package fzu.edu;

import android.content.res.AssetFileDescriptor;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        AutoCompleteTextView mAccount=findViewById(R.id.register_input_account);
        EditText mId=findViewById(R.id.register_input_id);
        EditText mName=findViewById(R.id.register_input_name);
        EditText mPassword=findViewById(R.id.register_input_password);
        EditText mPasswordCheck=findViewById(R.id.register_input_password_check);
        Button mRegister=findViewById(R.id.register_button);
    }
}
