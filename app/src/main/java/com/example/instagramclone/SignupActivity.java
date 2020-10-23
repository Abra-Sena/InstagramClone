package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = "SignupActivity";
    private EditText etCreateUser;
    private EditText etCreatePassword;
    private EditText etEmailAddress;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etCreateUser = findViewById(R.id.etCreateUser);
        etCreatePassword = findViewById(R.id.etCreatePassword);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        btnSignUp = findViewById(R.id.btnCreateAccount);


        // set custom properties
//        user.put("phone: ", "8000-123-4567");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create the ParseUser
                ParseUser user = new ParseUser();
                String userName = etCreateUser.getText().toString();
                String userPassword = etCreatePassword.getText().toString();
                String userEmail = etEmailAddress.getText().toString();
                // set core properties
                user.setUsername(userName);
                user.setPassword(userPassword);
                user.setEmail(userEmail);

                // invoke sign up in background
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if(e != null) {
                            Log.e(TAG, "Issue with login", e);
                            Toast.makeText(SignupActivity.this, "Issue with login", Toast.LENGTH_SHORT).show(); //improve this by specifying where the error is coming from
                            return;
                        }
                        goToLoginActivity();
                        //indicate to the user that they successfully logged in
                        Toast.makeText(SignupActivity.this, "Sign Up Success!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void goToLoginActivity() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }
}