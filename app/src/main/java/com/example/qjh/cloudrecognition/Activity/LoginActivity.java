package com.example.qjh.cloudrecognition.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.qjh.cloudrecognition.R;

import com.example.qjh.cloudrecognition.Control.BaseActivity;
import com.example.qjh.cloudrecognition.logins.Person;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

@SuppressWarnings("all")
public class LoginActivity extends BaseActivity {
    Button login;
    EditText uname;
    EditText pw;
    Button reg;
    private final static String TGA="Logins";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case 1:
                if(resultCode==1)
                {
                    String username=data.getStringExtra("username");
                    if(username!=null)
                    {
                       uname.setText(username);
                    }
                }
                break;
        }
    }

    /*
     登录界面
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_activity_login);
        Person person= BmobUser.getCurrentUser(Person.class);
        if(person!=null)  //判断当前是否登录
        {
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);   //跳转到主界面
            startActivity(intent);
            finish();
        }
        init();
    }

    private void init() {
        login = (Button) findViewById(R.id.login);
        uname = (EditText) findViewById(R.id.username);
        pw = (EditText) findViewById(R.id.password);
        reg=(Button)findViewById(R.id.register);
        if(uname.getText().toString()==""||pw.getText().toString()=="")
        {
            Toast.makeText(LoginActivity.this,"账号密码不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);   //跳转到主界面

                startActivityForResult(intent,1);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  Person person=new Person();
                person.setUsername(uname.getText().toString());
                person.setPassword(pw.getText().toString());
                person.login(new SaveListener<Person>() {
                    @Override
                    public void done(Person person, BmobException e) {
                        if (e == null) {
                            Intent intent=new Intent(LoginActivity.this, MainActivity.class);   //跳转到主界面
                            startActivity(intent);
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }
}
