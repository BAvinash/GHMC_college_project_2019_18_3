package com.avinash_badramoni.GHMC;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.libizo.CustomEditText;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class SignUpActivity extends AppCompatActivity {


    CustomEditText memail,mpassword;
    ProSwipeButton submit;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        memail =(CustomEditText)findViewById(R.id.signemail);
        mpassword =(CustomEditText)findViewById(R.id.signpassword);
        submit = (ProSwipeButton)findViewById(R.id.proswipebutton_main_error);

        submit.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {

                String email = memail.getText().toString();
                String password = mpassword.getText().toString();

                if (TextUtils.isEmpty(email)){

                    submit.showResultIcon(false);

                    Toast.makeText(SignUpActivity.this, "Please provide email", Toast.LENGTH_SHORT).show();
                }
                if (TextUtils.isEmpty(password))
                {
                    submit.showResultIcon(false);

                    Toast.makeText(SignUpActivity.this, "Please provide password", Toast.LENGTH_SHORT).show();

                }

                else {

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {

                                        submit.showResultIcon(true);
                                        Intent intent = new Intent(SignUpActivity.this, SecondActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {

                                        Toast.makeText(SignUpActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });


                }



            }
        });
    }
}
