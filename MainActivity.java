package com.anil.lab3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPersonName2);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if(password.getText().toString().length()>=8 && validatePassword(password.getText().toString())){
                    Toast.makeText(getBaseContext(),"Successful Sign UP",Toast.LENGTH_LONG).show();
                    Intent it = new Intent(getBaseContext(), login_activity.class);

                    Bundle b = new Bundle();
                    b.putString("username",username.getText().toString());
                    b.putString("pass",password.getText().toString());
                    it.putExtras(b);

                    startActivity(it);

                }else{
                    Toast.makeText(getBaseContext(),"not VALID",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean validatePassword(String password){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
