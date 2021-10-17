package com.example.courswork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LogActivity extends AppCompatActivity {

    EditText email_log, pass_log;
    Button btnSignIn;

    DBHelper db;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_log);

        email_log = (EditText) findViewById(R.id.email_log);
        pass_log = (EditText) findViewById(R.id.password_log);
        btnSignIn = (Button) findViewById(R.id.btnSignIn1);
        db = new DBHelper(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = email_log.getText().toString();
                String pass = pass_log.getText().toString();

                if(email.equals("")|| pass.equals("")){
                    Toast.makeText(LogActivity.this, "Заполниет все полян", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkEmail = db.checkEmailPassword(email, pass);
                    if(checkEmail==true){
                        Toast.makeText(LogActivity.this, "Добро пожаловать", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(LogActivity.this, "Неверные данные", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
