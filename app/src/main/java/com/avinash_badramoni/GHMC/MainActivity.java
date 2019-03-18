package com.avinash_badramoni.GHMC;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.libizo.CustomEditText;

import in.shadowfax.proswipebutton.ProSwipeButton;

public class MainActivity extends AppCompatActivity {

    CustomEditText memail,mname,mpassword,mepassword,mphone;
    private ProSwipeButton proSwipeBtn;
    private FirebaseAuth mAuth;
    private DatabaseReference dbmail,dbname,dbpassword,dbphone,databaseReference;
    Users users;
    TextView txt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");


        memail = (CustomEditText)findViewById(R.id.email);
        mname = (CustomEditText)findViewById(R.id.name);
        mpassword = (CustomEditText)findViewById(R.id.password);
        mepassword = (CustomEditText)findViewById(R.id.repassword);
        mphone = (CustomEditText)findViewById(R.id.phone);
        txt= (TextView)findViewById(R.id.login);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
                //finish();
            }
        });

       proSwipeBtn = (ProSwipeButton) findViewById(R.id.proswipebutton_main_error);

        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        emailRegister();
                    }
                }, 2000);


            }
        });




    }



    private void emailRegister() {
        String email = memail.getText().toString();
        String password = mpassword.getText().toString();
        String name = memail.getText().toString();
        String phone = mpassword.getText().toString();
        String repassword = mepassword.getText().toString();



        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            proSwipeBtn.showResultIcon(false);
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            proSwipeBtn.showResultIcon(false);
        }
        if (TextUtils.isEmpty(repassword)){
            Toast.makeText(this, "Please enter RE-Password", Toast.LENGTH_SHORT).show();
            proSwipeBtn.showResultIcon(false);
        }
        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show();
            proSwipeBtn.showResultIcon(false);
        }
        if (TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Please enter phone", Toast.LENGTH_SHORT).show();
            proSwipeBtn.showResultIcon(false);
        }
        if (!password.equals(repassword)){

            Toast.makeText(this, "Please type same password", Toast.LENGTH_SHORT).show();
            proSwipeBtn.showResultIcon(false);
        }
        else {


            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                saveDataToFirebase();
                                proSwipeBtn.showResultIcon(true);
                                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                startActivity(intent);
                                finish();

                            } else {
                                proSwipeBtn.showResultIcon(false);
                                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();

                            }

                        }
                    });

        }


    }

    private void saveDataToFirebase() {

        String Name = mname.getText().toString().trim();
        String Email = memail.getText().toString().trim();
        String Password = mpassword.getText().toString().trim();
        String Phone = mphone.getText().toString().trim();

        Users users = new Users(Name,Email,Password,Phone);

        databaseReference.child("profile").push().setValue(users);

    }


}
