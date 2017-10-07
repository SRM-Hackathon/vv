package com.example.android.androiduploadimage;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends Activity
{
    Button btnSignIn,btnSignUp,btnAdmin;
    Ldba loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mmaa);


        ldba=new LoginDataBaseAdapter(this);
        ldba=loginDataBaseAdapter.open();


        SignIn=(Button)findViewById(R.id.SignIN);
        SignUp=(Button)findViewById(R.id.SignUP);
        Admin=(Button)findViewById(R.id.Sign);

        SignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentSignUP=new Intent(getApplicationContext(),SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
    }

    public void signIn(View V)
    {
        final Dialog dialog = new Dialog(home.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");


        final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextUserNameToLogin);
        final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPasswordToLogin);

        Button SignIn=(Button)dialog.findViewById(R.id.SignIn);
        Button admin=(Button)dialog.findViewById(R.id.Sign);


        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();


                String storedPassword=ldba.getSinlgeEntry(userName);


                if(password.equals(storedPassword)) {
                    Toast.makeText(home.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Button bnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);
                    bnSignIn.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent(home.this, MainActivity.class);
                            startActivity(intent);}
                });}
                else
                {
                    Toast.makeText(home.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();




    }




    @Override
    protected void onDestroy() {
        super.onDestroy();

        loginDataBaseAdapter.close();
    }
}
