package com.yashtourtravel.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.btnLogin);

        loginBtn.setOnClickListener(v ->
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(
                        email.getText().toString(),
                        password.getText().toString()
                ).addOnSuccessListener(a -> {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                })
        );
    }
}
