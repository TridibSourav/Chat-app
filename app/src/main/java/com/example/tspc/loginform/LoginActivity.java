package com.example.tspc.loginform;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


    public class LoginActivity extends AppCompatActivity {
        Button log_in;
        Button sign_up;
        EditText email3 , pass3;
        FirebaseAuth loginAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            log_in=findViewById(R.id.lgin);
            sign_up=findViewById(R.id.regstr);
            email3=findViewById(R.id.email);
            pass3=findViewById(R.id.pass);
            loginAuth= FirebaseAuth.getInstance();

            log_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });


            sign_up.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent1);

                }
            });
            log_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String emailText = email3.getText().toString();
                    String passwordd = pass3.getText().toString();

                    if (emailText.isEmpty()||passwordd.isEmpty()) {

                        email3.setError("Fill Id");
                        pass3.setError("Fill valid Pass");
                        Toast.makeText(LoginActivity.this, "Please enter all of informatin", Toast.LENGTH_SHORT).show();
                    }  else {
                        loginUser(emailText,passwordd);
                    }
                }
            });



        }

        private void loginUser(final String emailText, final String passwordd) {
            loginAuth.signInWithEmailAndPassword(emailText,passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Log In SuccesFully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, NavActivity.class);
                        intent.putExtra("email",email3.getText().toString());
                        startActivity(intent);
                    }    else {
                        Toast.makeText(LoginActivity.this, "Not Success ", Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
