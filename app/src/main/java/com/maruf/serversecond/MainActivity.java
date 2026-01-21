package com.maruf.serversecond;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;

    Button btninput;
    EditText edname, edemail,edadress;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btninput=findViewById(R.id.btninput);
        edname=findViewById(R.id.edname);
        edemail=findViewById(R.id.edemail);
        edadress=findViewById(R.id.edadress);
        progressBar=findViewById(R.id.progressBar);

        btninput.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String name = edname.getText().toString();
                String email = edemail.getText().toString();
                String adress = edadress.getText().toString();

                String url = "http://192.168.1.107/Apps/connect.php?n="+name+"&e="+email+"&a="+adress;

                progressBar.setVisibility(VISIBLE);

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                StringRequest stringRequest= new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String s) {

                        progressBar.setVisibility(GONE);

                        Log.d("serverresponse","response is"+s);



                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }

                );

                 requestQueue.add(stringRequest);








            }
        });



    }

}