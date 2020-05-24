package com.example.recordingnotes;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_);

        TextView nameInput = findViewById(R.id.textView4);
        TextView pwdInput = findViewById(R.id.textView5);
        username = findViewById(R.id.editText3);
        password = findViewById(R.id.editText4);
        register = findViewById(R.id.button3);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "OUIJADOR.TTF");
        nameInput.setTypeface(typeface);
        pwdInput.setTypeface(typeface);
        register.setTypeface(typeface);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = username.getText().toString().trim();
                String passWord = password.getText().toString().trim();
                User user = new User();
                user.setUsername(userName);
                user.setPassword(passWord);
                int result = User_service.getInstance(getApplicationContext()).saveUser(user);
                if(result == -1) {
                    Toast.makeText(Register_Activity.this, "You have already registered, please log in", Toast.LENGTH_SHORT).show();
                }
                else if(result == 1) {
                    Toast.makeText(Register_Activity.this, "Registration success", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }
}
