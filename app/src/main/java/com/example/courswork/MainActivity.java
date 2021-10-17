package com.example.courswork;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button SingUp, SignIn, btnGoSignIn;

    EditText email, password, repPass, Name;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        SignIn = (Button) findViewById(R.id.btnSignIn);

        SingUp = (Button) findViewById(R.id.btnSingUp);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repPass = (EditText) findViewById(R.id.repPass);
        Name = (EditText) findViewById(R.id.Name);

        db = new DBHelper(this);

        SingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emai = email.getText().toString();
                String pass = password.getText().toString();
                String reppass = repPass.getText().toString();
                String name = Name.getText().toString();

                if (emai.equals("") || pass.equals("") || reppass.equals("") || name.equals(""))
                    Toast.makeText(MainActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(reppass)) {
                        Boolean checkEmail = db.checkEmail(emai);
                        if (checkEmail == false) {
                            Boolean insert = db.insertData(emai, name, pass);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity.this, "Ошибка регистрации", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Пользователь уже зарегистрирован", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LogActivity.class);
                startActivity(intent);

            }
        });

    }
}

