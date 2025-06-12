package com.example.mealmate.startingActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mealmate.Activity.MainActivity;
import com.example.mealmate.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText loginemail, loginpassword;
    Button loginBtn;
    TextView gotosignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginemail=findViewById(R.id.etEmail);
        loginpassword=findViewById(R.id.etPassword);
        loginBtn=findViewById(R.id.btnLogin);
        gotosignup=findViewById(R.id.gotosignup);

        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(in);
            }
        });


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email,password;
                email=loginemail.getText().toString();
                password=loginpassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Please Input Email and Password", Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Welcome To MealMate", Toast.LENGTH_LONG).show();
                                Intent in=new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(in);
                            }else{
                                Toast.makeText(LoginActivity.this,"Invalid Credentials", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }
            }
        });
    }
        @Override
        protected void onStart() {
            super.onStart();
            if (FirebaseAuth.getInstance().getCurrentUser()!=null){
                Intent in=new Intent(this, MainActivity.class);
                startActivity(in);
            }


    }
}