package com.example.cptestls;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

        private Button onActivityLogin;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            onActivityLogin = findViewById(R.id.perehod);
            onActivityLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = MainActivity.this;
                    Class destinationActivity = Register.class;
                    Intent mainActivityIntent = new Intent(context, destinationActivity);
                    startActivity(mainActivityIntent);

                }
            });
        }
    }
