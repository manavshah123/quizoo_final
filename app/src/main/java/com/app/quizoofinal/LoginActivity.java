package com.app.quizoofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {


    ImageView Facebook,Google;
    ImageButton Signin;
    TextView Signup;
    TextInputLayout Phonenum,Password;
    float v=0;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CardView logincard= findViewById(R.id.login_card);
        logincard.setBackgroundResource(R.drawable.cardback_login);

        Signup= findViewById(R.id.signup);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });




        /// Social Media Animation //////////////////

        Facebook= findViewById(R.id.facebook);
        Google = findViewById(R.id.google);

        Facebook.setTranslationY(500);
        Google.setTranslationY(500);

        Facebook.setAlpha(v);
        Google.setAlpha(v);

        Facebook.animate().translationY(0).alpha(1).setDuration(5000).setStartDelay(400).start();
        Google.animate().translationY(0).alpha(1).setDuration(5000).setStartDelay(600).start();



        // Firebase
        Phonenum=(TextInputLayout)findViewById(R.id.Phonenum);
        Password=(TextInputLayout)findViewById(R.id.Password);

        Signin = findViewById(R.id.Signin_button);
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String _phonenum= Phonenum.getEditText().getText().toString().trim();
                final String _password= Password.getEditText().getText().toString().trim();
                if(_phonenum.charAt(0)=='0'){
                    _phonenum = _phonenum.substring(1);
                }

                Query checkuser= FirebaseDatabase.getInstance().getReference("user").orderByChild("phone").equalTo(_phonenum);

                final String final_phonenum = _phonenum;
                checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String systempass= snapshot.child(final_phonenum).child("password").getValue(String.class);
                            if(systempass.equals(_password)){
                                Intent intent= new Intent(LoginActivity.this, HomeScreen.class);
                                startActivity(intent);
                            }

                            else{
                                Toast.makeText(LoginActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                            }
                        }

                        else{
                            Toast.makeText(LoginActivity.this, "No such user exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}