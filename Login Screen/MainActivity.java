package com.example.loginscreen;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(onClickLoginButton);

    }

    private View.OnClickListener onClickLoginButton = new View.OnClickListener(){

        int attempts = 3;
        @Override
        public void onClick(View view) {


            EditText userNameEditText = findViewById(R.id.userNameEditText);
            EditText passwordEditText = findViewById(R.id.passwordEditText);
            TextView errorTextView = findViewById(R.id.errorTextView);
            Button loginButton = findViewById(R.id.loginButton);
            TextView attemptTextView = findViewById(R.id.attemptTextView);

             String userName = userNameEditText.getText().toString();
             String password = passwordEditText.getText().toString();

             //Creating new object from LoginManager Class
             LoginManager loginManager = new LoginManager(userName, password);

             //This loginManager is created as a new LoginManager object
             if(loginManager.hasValidCredentials()){
                 //Success
                 errorTextView.setVisibility(View.INVISIBLE);
                 attemptTextView.setVisibility(View.INVISIBLE);
                 attempts = 3;
             }else{
                 //Fail

                 //Three attempt
                 attempts--;
                     errorTextView.setText(R.string.error_text);
                     errorTextView.setVisibility(View.VISIBLE);
                     attemptTextView.setText(Integer.toString(attempts));
                     attemptTextView.setVisibility(View.VISIBLE);


                 if(attempts == 0){
                     loginButton.setVisibility(View.INVISIBLE);
                 }
             }
        }
    };

}