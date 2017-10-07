package com.example.android.androiduploadimage;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class home extends Activity
{
    Button btnSignIn,btnSignUp,btnAdmin;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mmaa);


        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
        loginDataBaseAdapter=loginDataBaseAdapter.open();


        btnSignIn=(Button)findViewById(R.id.buttonSignIN);
        btnSignUp=(Button)findViewById(R.id.buttonSignUP);
        btnAdmin=(Button)findViewById(R.id.buttonSign);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
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

        Button btnSignIn=(Button)dialog.findViewById(R.id.buttonSignIn);
        Button btnadmin=(Button)dialog.findViewById(R.id.buttonSign);


        btnSignIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();


                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(userName);


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
