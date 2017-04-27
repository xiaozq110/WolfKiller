package com.roome.android.hitravel20;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.roome.android.hitravel20.Bean.TestTable;

import java.lang.reflect.Array;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


public class MainActivity extends BaseActivity {
    private long backPressTime;
    EditText et_array;
    Button add,play;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        play =  (Button)findViewById(R.id.play);
        play .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,WolfKillActivity.class));
            }
        });
        add = (Button) findViewById(R.id.btton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
            }
        });
        et_array = (EditText) findViewById(R.id.et_array);
    }

    private void addData() {
        String[] words = {"北京", "上海", "深圳", "广州", "重庆111"};
        TestTable table = new TestTable();
        table.setArray(words);
        /*table.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    toast("创建数据成功：" + s);
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });*/
        table.update("24d83cdd32", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Log.i("bmob","更新成功");
                }else{
                    Log.i("bmob","更新失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });



    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - backPressTime > 2000) {
            toast("再按一次退出应用");
            backPressTime = System.currentTimeMillis();
            return;
        }
        super.onBackPressed();
    }





}
