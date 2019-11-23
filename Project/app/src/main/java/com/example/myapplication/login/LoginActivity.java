package com.example.myapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.UserDatabase.UserDatabaseHelper;
import com.example.myapplication.navigation.NaviActivity;
import com.example.myapplication.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity
{
    private UserDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new UserDatabaseHelper(this);
    }

    public void ClickOnLogin(View v)
    {
        EditText insert_username = (EditText)findViewById(R.id.editText);
        EditText insert_password = findViewById(R.id.editText2);
        String username = insert_username.getText().toString();
        String password = insert_password.getText().toString();

        if (UserExist(username, password))
        {
            Toast.makeText(this,"Welcome " + username + "!",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, NaviActivity.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Username or Password not correct", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean UserExist(String username,String password)
    {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String sql = "select * from user where username=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[] {username, password});
        if (cursor.moveToFirst())
        {
            cursor.close();
            return true;
        }
        return false;
    }

    public void startRegisterActivity(View v)
    {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
}
