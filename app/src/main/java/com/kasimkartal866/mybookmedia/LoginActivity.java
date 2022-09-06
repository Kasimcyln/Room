package com.kasimkartal866.mybookmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button btnLogin;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {

           /* Boolean state = false;
            if (TextUtils.isEmpty(email.getText().toString())) {
                email.setError("enter e-mail");
                state = true;
            }
            if (TextUtils.isEmpty(pass.getText().toString())) {
                pass.setError("enter password");
                state = true;
            }
              preferences = getSharedPreferences("validation", Context.MODE_PRIVATE);

            String emailDisk = preferences.getString("email", "empty");
            String passwordDisk = preferences.getString("password", "empty");

            if (email.getText().toString().contentEquals(emailDisk) && pass.getText().toString().contentEquals(passwordDisk) ) {
                Intent intent = new Intent(LoginActivity.this, MainPageActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Username or password is incorrect.", Toast.LENGTH_SHORT).show();
            }

              */

            String emailText = email.getText().toString();
            String passwordText = pass.getText().toString();
            if(emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(this, "Fill all Fields!", Toast.LENGTH_SHORT).show();
            }else {
                UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                UserDao userDao = userDatabase.userDao();

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        User user = userDao.login(emailText, passwordText);
                        if(user == null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            String name = user.email;
                            startActivity(new Intent(LoginActivity.this,MainPageActivity.class).putExtra("name",name));
                        }

                    }
                }).start();
            }


        });
    }
}