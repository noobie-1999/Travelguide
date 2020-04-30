package com.example.travelguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);;
        final SharedPreferences.Editor editor = preferences.edit();

        Button signup = findViewById(R.id.sign_up);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
                v.startAnimation(animation);
                EditText mEmail = findViewById(R.id.signUpEmail);
                String email = mEmail.getText().toString();
                editor.putString("username", email);
            }
        });
    }
}
