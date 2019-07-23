package user_msg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.qjh.cloudrecognition.R;

import Control.BaseActivity;

public class User_Msg extends BaseActivity implements View.OnClickListener {
    private ProgressBar msg_pro;
    private EditText U_N;
    private EditText Mail; //邮箱
    private EditText Sex; //性别
    private EditText Birthday; //生日
    private EditText Address; //地址
    private TextView Msg_Source; //记录当前进度

    private CollapsingToolbarLayout CTL;
    private AppBarLayout appBar;

    private Edit_All U_Ns;
    private Edit_All Mails;
    private Edit_All Sexs;
    private Edit_All Birthdays;
    private Edit_All Addresss;
    private ImageView RTU;
   private Toolbar toolbar_base;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_msg);
        toolbar_base=(Toolbar)findViewById(R.id.toolbar_base);
       // toolbar_base.setTitle("我的");
        setSupportActionBar(toolbar_base);
        BaseActivity.Set_Window(this);
        CTL=(CollapsingToolbarLayout)findViewById(R.id.CTL);
        appBar=(AppBarLayout)findViewById(R.id.appbar);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset <= -200) {
                    CTL.setTitle("个人中心");//设置标题
                    //使用下面两个CollapsingToolbarLayout的方法设置展开透明->折叠时你想要的颜色
                    CTL.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
                    CTL.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));

                } else {

                    CTL.setTitle("");
                }

            }
        });

//        msg_pro = (ProgressBar) findViewById(R.id.msg_pro);
//        U_N = (EditText) findViewById(R.id.U_N);
//        Mail = (EditText) findViewById(R.id.Mail);
//        Sex = (EditText) findViewById(R.id.Sex);
//        Birthday = (EditText) findViewById(R.id.Birthday);
//        Address = (EditText) findViewById(R.id.address);
//        Msg_Source = (TextView) findViewById(R.id.Msg_Source);
//        RTU=(ImageView)findViewById(R.id.RTU);

//        U_Ns= new Edit_All(U_N,false);
//        Mails= new Edit_All(Mail,false);
//        Sexs= new Edit_All(Sex,false);
//        Birthdays= new Edit_All(Birthday,false);
//        Addresss= new Edit_All(Address,false);
//
//
////        添加点击事件
//        U_N.addTextChangedListener(U_Ns);
//        Mail.addTextChangedListener(Mails);
//        Sex.addTextChangedListener(Sexs);
//        Address.addTextChangedListener(Addresss);
//        Birthday.addTextChangedListener(Birthdays);
//        RTU.setOnClickListener(this);
    }


     public void Change(Edit_All targets)
     {
         if (!targets.getTarget().getText().toString().equals("")) {
             if (!targets.getCheck()) {
                 int pro = msg_pro.getProgress();
                 pro += 20;
                 msg_pro.setProgress(pro);
                 Msg_Source.setText("当前资料完善进度" + pro + "%");
                 targets.setCheck(true);
             }
         }
        else {
             targets.setCheck(false);
              int pro = msg_pro.getProgress();
             if(pro!=0)
             {
                pro -= 20;
                msg_pro.setProgress(pro);
                Msg_Source.setText("当前资料完善进度" + pro + "%");
           }

        }
     }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
           // case R.id.RTU:
              //  this.finish();
               // break;
        }
    }

    /**
     * 封装输入窗口
     */
    class  Edit_All implements TextWatcher{

        private  EditText target;
        private  Boolean check;
        public Edit_All(EditText tar,Boolean checks)
        {
            this.target=tar;
            this.check=checks;
        }
        public void setCheck(Boolean check) {
            this.check=check;
        }
        public Boolean getCheck() {
            return check;
        }
        public EditText getTarget() {
            return target;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            Change(this);
        }
    }
}
