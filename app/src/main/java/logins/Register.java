package logins;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qjh.cloudrecognition.R;

import Control.BaseActivity;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register  extends BaseActivity implements View.OnClickListener{
    ImageView back;
    Button RG;
    EditText username;
    EditText pw;
    EditText pw2;
    TextView change;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();

    }

    private void init() {
        back=(ImageView)findViewById(R.id.Return);
        back.setOnClickListener(this);
        username=(EditText)findViewById(R.id.username);
        pw=(EditText)findViewById(R.id.password);
        pw2=(EditText)findViewById(R.id.password_confirm);
        RG=(Button)findViewById(R.id.RG);
        RG.setOnClickListener(this);
        change=(TextView)findViewById(R.id.Enter_change);
        change.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Return:
                this.finish();
                break;
            case R.id.RG:
                Person person=new Person();
                person.setUsername(username.getText().toString());
                if(pw.getText().toString().equals(pw2.getText().toString()))
                {
                    person.setPassword(pw.getText().toString().trim());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"密码不一致",Toast.LENGTH_LONG).show();
                    return;
                }
                person.signUp(new SaveListener<Person>() {
                    @Override
                    public void done(Person person, BmobException e) {
                        if(e==null)
                        {
                            Intent intent=new Intent();
                            intent.putExtra("username",username.getText().toString().trim());
                            setResult(1,intent);
                            Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                            Register.this.finish();

                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"账号已存在",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
            case R.id.Enter_change:
                this.finish();

        }
    }
}
