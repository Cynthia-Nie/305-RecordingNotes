package com.example.recordingnotes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = findViewById(R.id.textView);
        TextView nameInput = findViewById(R.id.textView2);
        TextView pwdInput = findViewById(R.id.textView3);
        username = findViewById(R.id.editText2);
        password = findViewById(R.id.editText);
        login = findViewById(R.id.button);
        register = findViewById(R.id.button2);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "OUIJADOR.TTF");
        title.setTypeface(typeface);
        nameInput.setTypeface(typeface);
        pwdInput.setTypeface(typeface);
        login.setTypeface(typeface);
        register.setTypeface(typeface);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString().trim();
                String passWord = password.getText().toString().trim();
                int result = User_service.getInstance(getApplicationContext()).Quer(passWord, userName);
                if (result == 1) {
                    Toast.makeText(MainActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent();
                    intent1.setClass(MainActivity.this, MainActivity2.class);
                    startActivity(intent1);
                } else if (result == 0) {
                    Toast.makeText(MainActivity.this, "Login failed, you need to register your identity", Toast.LENGTH_SHORT).show();
                } else if (result == -1) {
                    Toast.makeText(MainActivity.this, "Repeat your password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Register_Activity.class);
                startActivity(intent);
            }
        });
    }
}
