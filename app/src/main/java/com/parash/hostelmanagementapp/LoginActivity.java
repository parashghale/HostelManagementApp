package com.parash.hostelmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText etusername,etpassword;
    FirebaseAuth firebaseAuth;
    Button btnLogin, btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etpassword=findViewById(R.id.etusername);
        etpassword=findViewById(R.id.etpassword);
        btnLogin= findViewById(R.id.btnLogin);
        btnSignup= findViewById(R.id.signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signInWithEmailAndPassword(etusername.getText().toString(),etpassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(LoginActivity.this,task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
