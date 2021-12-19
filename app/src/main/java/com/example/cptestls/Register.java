package com.example.cptestls;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Register extends AppCompatActivity {
    private EditText editTextTextEmailAddress;
    private EditText editTextTextPersonName;
    private EditText editTextTextPassword;
    private Boolean sexF;
    private RadioButton radioButtonRegSex1;
    private RadioButton radioButtonRegSex2;
    private Boolean isSexChecked;
    private Button register;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextName);
        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        radioButtonRegSex1 = (RadioButton) findViewById(R.id.radioButtonRegSex1);
        radioButtonRegSex2 = (RadioButton) findViewById(R.id.radioButtonRegSex2);
        register = (Button) findViewById(R.id.register);

        View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton)v;
                switch (rb.getId()) {
                    case R.id.radioButtonRegSex1: {
                        sexF = true;
                        isSexChecked = true;
                        break;
                    }
                    case R.id.radioButtonRegSex2: {
                        sexF = false;
                        isSexChecked = true;
                        break;
                    }
                }
            }
        };


        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reg(v);
            }
        };

        register.setOnClickListener(buttonClickListener);
        radioButtonRegSex1.setOnClickListener(radioButtonClickListener);
        radioButtonRegSex2.setOnClickListener(radioButtonClickListener);

    }
    public void reg(View v) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL =  "https://backandroid.herokuapp.com/api/reg/register";
        Map params = new HashMap();
        params.put("email", editTextTextEmailAddress.getText().toString());
        params.put("username", editTextTextPersonName.getText().toString());
        params.put("password", editTextTextPassword.getText().toString());
        //params.put("sexF", sexF);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,URL,
                new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                               "приветики", Toast.LENGTH_SHORT);
                        goToAuth();
                        toast.show();
                    }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response error", "" + error);
                Toast toast = Toast.makeText(getApplicationContext(),
                       "что-то произошло", Toast.LENGTH_SHORT);
                toast.show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };

        requestQueue.add(request);
    }
    private void goToAuth() {
        Intent i = new Intent(this, Login.class);
        startActivity(i);
    }
}