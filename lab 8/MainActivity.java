package com.example.lab8a;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;
    Button btn0, btn8, btn9, btnstar, btnhash, btndelete, btncall, btnsave;

    EditText txtnumber;
    int pid = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtnumber = findViewById(R.id.editText);

        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btn0 = findViewById(R.id.button0);

        btncall = findViewById(R.id.buttoncall);
        btnsave = findViewById(R.id.buttonsave);
        btnstar = findViewById(R.id.buttonstar);
        btnhash = findViewById(R.id.buttonhash);
        btndelete = findViewById(R.id.buttondel);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("9");
            }
        });
        btnstar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("*");
            }
        });
        btnhash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtnumber.append("#");
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = txtnumber.getText().toString();
                if (num.length() > 0) {
                    num = num.substring(0, num.length() - 1);
                }
                txtnumber.setText(num);
            }
        });

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callatruntimepermission();
            }
            });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = txtnumber.getText().toString();
                Intent it = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
                it.putExtra(ContactsContract.Intents.Insert.PHONE,num);
                startActivity(it);
            }
        });
    }

    private void callatruntimepermission()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE},pid);

        }
        else
        {
                String num = txtnumber.getText().toString();
                Intent it = new Intent(Intent.ACTION_CALL);
                it.setData(Uri.parse("tel:" + num));
            startActivity(it);
        }

    }

    public  void onRequestPermissionResult(int requestCode, @NonNull String[]permissions,@NonNull int[] grantResult)
    {
        super.onRequestPermissionsResult(requestCode,permissions,grantResult);

        if(requestCode==pid)
        {
            if(grantResult[0]==PackageManager.PERMISSION_GRANTED)
            {
                callatruntimepermission();
            }
        }
    }
}



