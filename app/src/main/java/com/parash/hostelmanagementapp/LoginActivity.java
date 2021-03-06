package com.parash.hostelmanagementapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
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
    NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etusername=findViewById(R.id.etusername);
        etpassword=findViewById(R.id.etpassword);
        btnLogin= findViewById(R.id.btnLogin);
        firebaseAuth = FirebaseAuth.getInstance();

        notificationManagerCompat = NotificationManagerCompat.from(this);
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
                loginuser();
            }
        });
        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        final Sensor proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                if(event.sensor.getType()== Sensor.TYPE_PROXIMITY){

                    if(event.values[0]==0){
                        loginuser();
                    }
                    else{
                        params.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                        params.screenBrightness = -1f;
                        getWindow().setAttributes(params);
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (proximitySensor != null) {
            sensorManager.registerListener(sensorEventListener, proximitySensor, sensorManager.SENSOR_DELAY_NORMAL);

        }else {
            Toast.makeText(this, "No sensor found", Toast.LENGTH_SHORT).show();
        }

    }

    private void loginuser() {
        String email = etusername.getText().toString();
        String password= etpassword.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                            DisplayNotification();

                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
                            vibrator.vibrate(500);                                }
                    }
                });
    }
    private void DisplayNotification( ) {

        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CreateChannel.CHANNEL_1)
                .setSmallIcon(R.drawable.ic_action_profile)
                .setContentTitle("Welcome to hostel app ").setContentText("You will find all the things u need here")
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManagerCompat.notify(1,notification);
    }

}
