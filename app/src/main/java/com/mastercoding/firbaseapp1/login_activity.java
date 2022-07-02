package com.mastercoding.firbaseapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_activity extends AppCompatActivity {

    EditText userETlogin, passETlogin;
    Button loginbtn,Regisbtn;

    //firebase auth
    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // Checking for users existance Saving the current user
        if(firebaseUser != null){
            Intent i = new Intent(login_activity.this,MainActivity.class);
            startActivity(i);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userETlogin = findViewById(R.id.userName);
        passETlogin = findViewById(R.id.passlogin);

        loginbtn = findViewById(R.id.logbtn);
        Regisbtn = findViewById(R.id.registrationbtn);

        // firebase Authentication
        auth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // Checking for users existance
         if(firebaseUser != null){

         Intent i = new Intent(login_activity.this,MainActivity.class);
         startActivity(i);
         finish();


         }



        //Registration Button
        Regisbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login_activity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        //login Button:
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_text = userETlogin.getText().toString();
                String pass_text = passETlogin.getText().toString();

                // Checking if it is empty;
                if(TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)){
                    Toast.makeText(login_activity.this, "Please Fill the Fields", Toast.LENGTH_SHORT).show();

                }
                else{
                    auth.signInWithEmailAndPassword(email_text, pass_text)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(login_activity.this,MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(login_activity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                }

            }
        });

    }
}