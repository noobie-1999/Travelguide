package com.example.travelguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());;
        final SharedPreferences.Editor editor = preferences.edit();

        /*if(preferences.contains("username")){
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
        }*/

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                LinearLayout mLayout = findViewById(R.id.login);
                mLayout.setVisibility(View.VISIBLE);
            }
        };
        handler.postDelayed(runnable, 3000);

        Button login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                v.startAnimation(animation);
                EditText mEmail = findViewById(R.id.loginEmail);
                String email = mEmail.getText().toString();
                editor.putString("username", email);
                editor.apply();
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button signup = findViewById(R.id.sign_up_button);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                v.startAnimation(animation);
                Intent intent = new Intent(SplashScreen.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
