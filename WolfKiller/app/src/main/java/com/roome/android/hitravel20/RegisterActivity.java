package com.roome.android.hitravel20;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.roome.android.hitravel20.Bean.UserModel;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity extends BaseActivity {
    private static final String TAG = "HiTravel";
    private TextView back;
    private EditText et_Name,et_pwd,et_age,et_email,et_phone;
    private Button btn_sign;
    private String nameStr,pwdStr,ageStr,sexStr,emailStr,phoneStr;
    private int age;
    private RadioGroup rg;
    private RadioButton rb_famale,rb_male;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();

    }

    private void initView() {
        //edittext:
        et_Name = (EditText) findViewById(R.id.et_regist_name);
        et_pwd = (EditText) findViewById(R.id.et_regist_pwd);
        et_email = (EditText) findViewById(R.id.et_regist_email);
        et_age = (EditText) findViewById(R.id.et_regist_age);
        et_phone = (EditText) findViewById(R.id.et_regist_phone);

        //radiogroup:
        rg = (RadioGroup) findViewById(R.id.rg_regist);
        rb_male = (RadioButton) findViewById(R.id.rb_male);
        rb_famale = (RadioButton) findViewById(R.id.rb_female);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rb_female){
                    Log.i(TAG,"checkedId---女");

                    sexStr = "女";
                }else if (checkedId==R.id.rb_male){
                    sexStr = "男";

                    Log.i(TAG,"checkedId---男");
                }
            }
        });

        //textview
        back = (TextView) findViewById(R.id.tv_register_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });

        //button:

        btn_sign = (Button) findViewById(R.id.buttonsign);
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if (checkData()){
                        sign(nameStr,pwdStr,age,sexStr,emailStr,phoneStr);
                    }
            }
        });

    }


    private boolean checkData() {
        //用户名
        nameStr = et_Name.getText().toString();
        if (nameStr==null||"".equals(nameStr)){
            showdialog(RegisterActivity.this,SweetAlertDialog.WARNING_TYPE,"请输入用户名");
            return false;
        }
        //密码
        pwdStr = et_pwd.getText().toString();
        if (pwdStr==null||"".equals(pwdStr)){
            showdialog(RegisterActivity.this,SweetAlertDialog.WARNING_TYPE,"请输入密码");
            return false;
        }
        //年龄
        ageStr = et_age.getText().toString();
        if (isEmpty(ageStr)){
            showdialog(RegisterActivity.this,SweetAlertDialog.WARNING_TYPE,"请输入年龄");
            return false;
        }
         age = Integer.parseInt(ageStr);
        if (age>120||age<0){
            showdialog(RegisterActivity.this,SweetAlertDialog.WARNING_TYPE,"请输入正确的年龄");
            return false;
        }
        //性别
        if (sexStr==null||"".equals(sexStr)){
            showdialog(RegisterActivity.this,SweetAlertDialog.WARNING_TYPE,"请选择性别");
            return false;
        }
        //邮箱地址
        emailStr = et_email.getText().toString();
        if (!isEmail(emailStr)){
            showdialog(RegisterActivity.this,SweetAlertDialog.WARNING_TYPE,"请输入正确的邮箱地址");
            return false;
        }
        //手机号码
        phoneStr = et_phone.getText().toString();
        if (!isMobileNO(phoneStr)){
            showdialog(RegisterActivity.this,SweetAlertDialog.WARNING_TYPE,"请输入正确的电话号码");
            return false;
        }
        return true;
    }

    private void sign(String name,String pwd,int age,String sex,String email,String phone) {
        final SweetAlertDialog alert = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        alert.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        alert.setTitleText("Loading");
        alert.setCancelable(false);
        alert.show();
        UserModel model = new UserModel();
        model.setUsername(name);
        model.setPassword(pwd);
        model.setAge(age);
        model.setSex(sex);
        model.setMobilePhoneNumber(phone);
               Log.i("TAG","email="+email) ;
        model.setEmail(email);
        model.signUp(new SaveListener<UserModel>() {
            @Override
            public void done(UserModel s, BmobException e) {
                alert.dismiss();
                if (e == null) {
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    RegisterActivity.this.finish();
                    if (LoginActivity.loginActivity!=null){
                        LoginActivity.loginActivity.finish();
                    }
                } else {
                    loge(e);
                    switch (e.getErrorCode()){
                        case 101://用户名或密码不对
                            showdialog(RegisterActivity.this, SweetAlertDialog.WARNING_TYPE,"用户名或密码不对");
                            break;
                        case 202://用户名已存在
                            showdialog(RegisterActivity.this,SweetAlertDialog.WARNING_TYPE,"用户名已存在");
                            break;
                        case 301://不是有效的邮箱
                            showdialog(RegisterActivity.this,SweetAlertDialog.WARNING_TYPE,"请输入有效的邮箱");
                            break;
                    }
                }
            }
        });
    }


}
