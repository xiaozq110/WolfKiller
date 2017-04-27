package com.roome.android.hitravel20;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.roome.android.hitravel20.Application.TravelApplication;
import com.roome.android.hitravel20.Bean.UserModel;
import com.roome.android.hitravel20.Constants.Constants;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_sign;
    private EditText et_Name,et_pwd;
    private Button btn_login;
    private String nameStr,pwdStr;
    private CheckBox cb_remPwd;
    public static LoginActivity loginActivity;
    private  long backpresstime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.loginActivity = LoginActivity.this;
        initView();



    }

    private void initView() {
        et_Name = (EditText) findViewById(R.id.et_name);
        if (!TravelApplication.mTravelPreference.getString(Constants.SP_USERNAME,"0").equals("0")){
            et_Name.setText(TravelApplication.mTravelPreference.getString(Constants.SP_USERNAME,"0"));
        }

        et_pwd = (EditText) findViewById(R.id.et_pwd);
        tv_sign = (TextView) findViewById(R.id.tv_sign);
        btn_login = (Button) findViewById(R.id.buttonlogin);
        cb_remPwd = (CheckBox) findViewById(R.id.cb_remember_pwd);
        cb_remPwd.setChecked(TravelApplication.mTravelPreference.getBoolean(Constants.LOIGN_REMEMBERPWD_KEY,false));
        cb_remPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = TravelApplication.mTravelPreference.edit();
                editor.putBoolean(Constants.LOIGN_REMEMBERPWD_KEY,isChecked);
                editor.commit();
            }
        });
        tv_sign.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        nameStr = et_Name.getText().toString();
        pwdStr = et_pwd.getText().toString();
     switch (v.getId()){
         case R.id.tv_sign:
             startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
         break;
         case R.id.buttonlogin:

             if (nameStr!=null&&!"".equals(nameStr)){
                 if (pwdStr!=null&&!"".equals(pwdStr)){
                     login(nameStr,pwdStr);
                 }else{
                     Toast.makeText(this,"password cannot be null",Toast.LENGTH_LONG).show();
                 }
             }else{
                 Toast.makeText(this,"userName cannot be null",Toast.LENGTH_LONG).show();
             }

             break;
     }
    }

    private void login(final String name, String pwd) {
        UserModel model = new UserModel();
        model.setUsername(name);
        model.setPassword(pwd);
        model.login(new SaveListener<UserModel>() {
            @Override
            public void done(UserModel s, BmobException e) {

                Log.i("TAG","s="+s);
                if (e == null) {
                        SharedPreferences.Editor editor = TravelApplication.mTravelPreference.edit();
                        editor.putString(Constants.SP_USERNAME,name);
                        editor.commit();
                    SweetAlertDialog alertDialog = new SweetAlertDialog(LoginActivity.this,SweetAlertDialog.SUCCESS_TYPE);
                    alertDialog.setContentText("登录成功！");
                    alertDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            LoginActivity.this.finish();
                        }
                    });
                    alertDialog.show();
                   /* startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    LoginActivity.this.finish();*/
                } else {
                    loge(e);
                    if(e.getErrorCode()==101){
                        toast("用户名或密码不正确");

                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - backpresstime>2000){
            toast("再按一次退出应用");
            backpresstime = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }
}