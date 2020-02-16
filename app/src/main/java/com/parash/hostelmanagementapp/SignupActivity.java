package com.parash.hostelmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.parash.hostelmanagementapp.Model.User;

public class SignupActivity extends AppCompatActivity {
    EditText etusername,etpassword;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    Button btnLogin, btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etusername=findViewById(R.id.username);
        progressBar= findViewById(R.id.progressbar);
        firebaseAuth=FirebaseAuth.getInstance();
        etpassword=findViewById(R.id.password);
        btnLogin= findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSignup= findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.createUserWithEmailAndPassword(etusername.getText().toString(),etpassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    String image = "noimage";
                                    User user = new User(etusername.getText().toString(),etpassword.getText().toString(),image);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user);


                                    Toast.makeText(SignupActivity.this, "User created", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Toast.makeText(SignupActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}
