package com.example.verification.voter;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity
{
    Button btnSignIn,btnSignUp,btnAdmin;
    LoginDB loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loginDataBaseAdapter=new LoginDB(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();


        btnSignIn=(Button)findViewById(R.id.SignIN);
        btnSignUp=(Button)findViewById(R.id.SignUP);
        btnAdmin=(Button)findViewById(R.id.adminIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intentSignUP=new Intent(getApplicationContext(),Signup.class);
                startActivity(intentSignUP);
            }
        });
    }

    public void signIn(View V)
    {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("Login");


        final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextuntl);
        final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPtl);

        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);


        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);


                if(password.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();
                    Button bnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);
                    bnSignIn.setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);}
                    });}
                else
                {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });

        dialog.show();




    }


    public void adminIn(View V)
    {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.login);
        dialog.setTitle("login");
        final  EditText editTextUserName=(EditText)dialog.findViewById(R.id.editTextuntl);
        final  EditText editTextPassword=(EditText)dialog.findViewById(R.id.editTextPtl);

        Button btnadminIn=(Button)dialog.findViewById(R.id.adminIn);

        btnadminIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();


                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);


                if(password.equals(storedPassword)) {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successfull", Toast.LENGTH_LONG).show();


                }
                else
                {
                    Toast.makeText(MainActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
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
