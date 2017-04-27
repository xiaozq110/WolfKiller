package com.roome.android.hitravel20;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.roome.android.hitravel20.wereWolf.JudgeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WolfKillActivity extends BaseActivity implements View.OnClickListener,AdapterView.OnLongClickListener {
    private TextView tv_population;
    private Button btn_toGod,btn_wash;
    private GridLayout gl_card;
    private ImageView iv_1, iv_2, iv_3, iv_4, iv_5, iv_6, iv_7, iv_8, iv_9, iv_10, iv_11, iv_12;
    private Spinner sp;
    private int mPersons = 0;
    private String[] mCard;
    private String[] mRandomCard;
    private boolean isOneShowRole = false;
    private boolean isTwoShowRole = false;
    private boolean isThrShowRole = false;
    private boolean isFourShowRole = false;
    private boolean isFiveShowRole = false;
    private boolean isSixShowRole = false;
    private boolean isSevShowRole = false;
    private boolean isEigShowRole = false;
    private boolean isNinShowRole = false;
    private boolean isTenShowRole = false;
    private boolean isEleShowRole = false;
    private boolean isTweShowRole = false;
    private List<String> black;


    private String[] sevenCard = {"狼人", "村民", "狼人", "女巫", "村民", "狼人", "预言家"};   //杀人对象：4
    private String[] eightCard = {"狼人", "村民", "预言家", "女巫", "狼人", "村民", "狼人", "村民"}; //杀人对象：5
    private String[] nineCard = {"狼人", "村民", "狼人", "女巫", "村民", "预言家", "狼人", "村民", "村民"};//杀人对象：6
    private String[] tenCard = {"狼人", "村民", "猎人", "女巫", "村民", "村民", "狼人", "预言家", "狼人", "村民"};//杀人对象：7
    private String[] elevenCard = {"狼人", "村民", "猎人", "女巫", "村民", "预言家", "狼人", "村民", "狼人", "守卫", "村民"};//杀人对象：8
    private String[] twelveCard = {"狼人", "村民", "猎人", "女巫", "村民", "预言家", "狼人", "村民", "狼人", "守卫", "村民", "丘比特"};//杀人对象：9

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wolf_kill);
        init();
    }

    private void init() {
        black = new ArrayList<String>();
        tv_population = (TextView) findViewById(R.id.tv_population);
        gl_card = (GridLayout) findViewById(R.id.gl_card);
        btn_toGod = (Button) findViewById(R.id.btn_toGod);
        btn_wash = (Button) findViewById(R.id.btn_wash);
        btn_toGod.setOnClickListener(this);
        btn_wash.setOnClickListener(this);
        iv_1 = (ImageView) findViewById(R.id.gl_v1);
        iv_2 = (ImageView) findViewById(R.id.gl_v2);
        iv_3 = (ImageView) findViewById(R.id.gl_v3);
        iv_4 = (ImageView) findViewById(R.id.gl_v4);
        iv_5 = (ImageView) findViewById(R.id.gl_v5);
        iv_6 = (ImageView) findViewById(R.id.gl_v6);
        iv_7 = (ImageView) findViewById(R.id.gl_v7);
        iv_8 = (ImageView) findViewById(R.id.gl_v8);
        iv_9 = (ImageView) findViewById(R.id.gl_v9);
        iv_10 = (ImageView) findViewById(R.id.gl_v10);
        iv_11 = (ImageView) findViewById(R.id.gl_v11);
        iv_12 = (ImageView) findViewById(R.id.gl_v12);
        iv_1.setOnClickListener(this);
        iv_2.setOnClickListener(this);
        iv_3.setOnClickListener(this);
        iv_4.setOnClickListener(this);
        iv_5.setOnClickListener(this);
        iv_6.setOnClickListener(this);
        iv_7.setOnClickListener(this);
        iv_8.setOnClickListener(this);
        iv_9.setOnClickListener(this);
        iv_10.setOnClickListener(this);
        iv_11.setOnClickListener(this);
        iv_12.setOnClickListener(this);
        
        iv_1.setOnLongClickListener(this);
        iv_2.setOnLongClickListener(this);
        iv_3.setOnLongClickListener(this);
        iv_4.setOnLongClickListener(this);
        iv_5.setOnLongClickListener(this);
        iv_6.setOnLongClickListener(this);
        iv_7.setOnLongClickListener(this);
        iv_8.setOnLongClickListener(this);
        iv_9.setOnLongClickListener(this);
        iv_10.setOnLongClickListener(this);
        iv_11.setOnLongClickListener(this);
        iv_12.setOnLongClickListener(this);
        sp = (Spinner) findViewById(R.id.sp_population);
        sp.setSelection(2);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] person = getResources().getStringArray(R.array.people);
                mCard = null;
                tv_population.setText("游戏人数为：" + person[position] + "人。");
                mPersons = Integer.parseInt(person[position]);
                showCard(mPersons);
                clearCard();
                mRandomCard = randomCard(mCard);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tv_population.setText("请选择游戏人数:");

            }
        });
    }

    private void clearCard(){
        //black
        if (black.size()>0){
            black.clear();
        }
        //focus
        iv_4.setFocusable(true);
        iv_3.setFocusable(true);
        iv_2.setFocusable(true);
        iv_1.setFocusable(true);
        iv_5.setFocusable(true);
        iv_6.setFocusable(true);
        iv_7.setFocusable(true);
        iv_8.setFocusable(true);
        iv_9.setFocusable(true);
        iv_10.setFocusable(true);
        iv_11.setFocusable(true);
        iv_12.setFocusable(true);
        //click
        iv_4.setClickable(true);
        iv_3.setClickable(true);
        iv_2.setClickable(true);
        iv_1.setClickable(true);
        iv_5.setClickable(true);
        iv_6.setClickable(true);
        iv_7.setClickable(true);
        iv_8.setClickable(true);
        iv_9.setClickable(true);
        iv_10.setClickable(true);
        iv_11.setClickable(true);
        iv_12.setClickable(true);
        
        //isshowed
          isOneShowRole = false;
          isTwoShowRole = false;
          isThrShowRole = false;
          isFourShowRole = false;
          isFiveShowRole = false;
          isSixShowRole = false;
          isSevShowRole = false;
          isEigShowRole = false;
          isNinShowRole = false;
          isTenShowRole = false;
          isEleShowRole = false;
          isTweShowRole = false;
        
        
        //background
        iv_4.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_3.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_2.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_1.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_5.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_6.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_7.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_8.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_9.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_10.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_11.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
        iv_12.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill));
    }
    
    private void showCard(int number) {

        mCard = new String[number];
        switch (number) {
            case 7:
                System.arraycopy(sevenCard, 0, mCard, 0, number);
                gl_card.setVisibility(View.VISIBLE);
                iv_4.setVisibility(View.VISIBLE);
                iv_3.setVisibility(View.VISIBLE);
                iv_2.setVisibility(View.VISIBLE);
                iv_1.setVisibility(View.VISIBLE);
                iv_5.setVisibility(View.VISIBLE);
                iv_6.setVisibility(View.VISIBLE);
                iv_7.setVisibility(View.VISIBLE);
                iv_8.setVisibility(View.INVISIBLE);
                iv_9.setVisibility(View.INVISIBLE);
                iv_10.setVisibility(View.INVISIBLE);
                iv_11.setVisibility(View.INVISIBLE);
                iv_12.setVisibility(View.INVISIBLE);
                break;
            case 8:
                System.arraycopy(eightCard, 0, mCard, 0, number);
                gl_card.setVisibility(View.VISIBLE);
                iv_4.setVisibility(View.VISIBLE);
                iv_3.setVisibility(View.VISIBLE);
                iv_2.setVisibility(View.VISIBLE);
                iv_1.setVisibility(View.VISIBLE);
                iv_5.setVisibility(View.VISIBLE);
                iv_6.setVisibility(View.VISIBLE);
                iv_7.setVisibility(View.VISIBLE);
                iv_8.setVisibility(View.VISIBLE);
                iv_9.setVisibility(View.INVISIBLE);
                iv_10.setVisibility(View.INVISIBLE);
                iv_11.setVisibility(View.INVISIBLE);
                iv_12.setVisibility(View.INVISIBLE);
                break;
            case 9:
                System.arraycopy(nineCard, 0, mCard, 0, number);
                gl_card.setVisibility(View.VISIBLE);
                iv_4.setVisibility(View.VISIBLE);
                iv_3.setVisibility(View.VISIBLE);
                iv_2.setVisibility(View.VISIBLE);
                iv_1.setVisibility(View.VISIBLE);
                iv_5.setVisibility(View.VISIBLE);
                iv_6.setVisibility(View.VISIBLE);
                iv_7.setVisibility(View.VISIBLE);
                iv_8.setVisibility(View.VISIBLE);
                iv_9.setVisibility(View.VISIBLE);
                iv_10.setVisibility(View.INVISIBLE);
                iv_11.setVisibility(View.INVISIBLE);
                iv_12.setVisibility(View.INVISIBLE);
                break;
            case 10:
                System.arraycopy(tenCard, 0, mCard, 0, number);
                gl_card.setVisibility(View.VISIBLE);
                iv_4.setVisibility(View.VISIBLE);
                iv_3.setVisibility(View.VISIBLE);
                iv_2.setVisibility(View.VISIBLE);
                iv_1.setVisibility(View.VISIBLE);
                iv_5.setVisibility(View.VISIBLE);
                iv_6.setVisibility(View.VISIBLE);
                iv_7.setVisibility(View.VISIBLE);
                iv_8.setVisibility(View.VISIBLE);
                iv_9.setVisibility(View.VISIBLE);
                iv_10.setVisibility(View.VISIBLE);
                iv_11.setVisibility(View.INVISIBLE);
                iv_12.setVisibility(View.INVISIBLE);
                break;
            case 11:
                System.arraycopy(elevenCard, 0, mCard, 0, number);
                gl_card.setVisibility(View.VISIBLE);
                iv_4.setVisibility(View.VISIBLE);
                iv_3.setVisibility(View.VISIBLE);
                iv_2.setVisibility(View.VISIBLE);
                iv_1.setVisibility(View.VISIBLE);
                iv_5.setVisibility(View.VISIBLE);
                iv_6.setVisibility(View.VISIBLE);
                iv_7.setVisibility(View.VISIBLE);
                iv_8.setVisibility(View.VISIBLE);
                iv_9.setVisibility(View.VISIBLE);
                iv_10.setVisibility(View.VISIBLE);
                iv_11.setVisibility(View.VISIBLE);
                iv_12.setVisibility(View.INVISIBLE);
                break;
            case 12:
                System.arraycopy(twelveCard, 0, mCard, 0, number);
                gl_card.setVisibility(View.VISIBLE);
                iv_4.setVisibility(View.VISIBLE);
                iv_3.setVisibility(View.VISIBLE);
                iv_2.setVisibility(View.VISIBLE);
                iv_1.setVisibility(View.VISIBLE);
                iv_5.setVisibility(View.VISIBLE);
                iv_6.setVisibility(View.VISIBLE);
                iv_7.setVisibility(View.VISIBLE);
                iv_8.setVisibility(View.VISIBLE);
                iv_9.setVisibility(View.VISIBLE);
                iv_10.setVisibility(View.VISIBLE);
                iv_11.setVisibility(View.VISIBLE);
                iv_12.setVisibility(View.VISIBLE);
                break;
        }


    }
    //若true,则表示有身份牌正在显示

    private boolean checkIsShow(int pos){

        switch (pos){
            case 1:
                if (isTwoShowRole||isThrShowRole||isFourShowRole
                        ||isFiveShowRole||isSixShowRole||isSevShowRole||isEigShowRole
                        ||isNinShowRole||isTenShowRole||isEleShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 2:
                if (isOneShowRole||isThrShowRole||isFourShowRole
                        ||isFiveShowRole||isSixShowRole||isSevShowRole||isEigShowRole
                        ||isNinShowRole||isTenShowRole||isEleShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 3:
                if (isOneShowRole||isTwoShowRole||isFourShowRole
                        ||isFiveShowRole||isSixShowRole||isSevShowRole||isEigShowRole
                        ||isNinShowRole||isTenShowRole||isEleShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 4:
                if (isOneShowRole||isTwoShowRole||isThrShowRole
                        ||isFiveShowRole||isSixShowRole||isSevShowRole||isEigShowRole
                        ||isNinShowRole||isTenShowRole||isEleShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 5:
                if (isOneShowRole||isTwoShowRole||isThrShowRole||isFourShowRole
                        ||isSixShowRole||isSevShowRole||isEigShowRole
                        ||isNinShowRole||isTenShowRole||isEleShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 6:
                if (isOneShowRole||isTwoShowRole||isThrShowRole||isFourShowRole
                        ||isFiveShowRole||isSevShowRole||isEigShowRole
                        ||isNinShowRole||isTenShowRole||isEleShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 7:
                if (isOneShowRole||isTwoShowRole||isThrShowRole||isFourShowRole
                        ||isFiveShowRole||isSixShowRole||isEigShowRole
                        ||isNinShowRole||isTenShowRole||isEleShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 8:
                if (isOneShowRole||isTwoShowRole||isThrShowRole||isFourShowRole
                        ||isFiveShowRole||isSixShowRole||isSevShowRole
                        ||isNinShowRole||isTenShowRole||isEleShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 9:
                if (isOneShowRole||isTwoShowRole||isThrShowRole||isFourShowRole
                        ||isFiveShowRole||isSixShowRole||isSevShowRole||isEigShowRole
                        ||isTenShowRole||isEleShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 10:
                if (isOneShowRole||isTwoShowRole||isThrShowRole||isFourShowRole
                        ||isFiveShowRole||isSixShowRole||isSevShowRole||isEigShowRole
                        ||isNinShowRole||isEleShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 11:
                if (isOneShowRole||isTwoShowRole||isThrShowRole||isFourShowRole
                        ||isFiveShowRole||isSixShowRole||isSevShowRole||isEigShowRole
                        ||isNinShowRole||isTenShowRole||isTweShowRole){
                    return true;
                }
                break;
            case 12:
                if (isOneShowRole||isTwoShowRole||isThrShowRole||isFourShowRole
                        ||isFiveShowRole||isSixShowRole||isSevShowRole||isEigShowRole
                        ||isNinShowRole||isTenShowRole||isEleShowRole){
                    return true;
                }


                break;

        }

       return false;
    }

    public String[] randomCard(String[] arr) {
        String[] RandomCard = new String[arr.length];
        int count = arr.length;
        int cbRandCount = 0;// 索引
        int cbPosition = 0;// 位置
        int k = 0;
        do {
            Random rand = new Random();
            int r = count - cbRandCount;
            cbPosition = rand.nextInt(r);

            RandomCard[k++] = arr[cbPosition];
            cbRandCount++;
            arr[cbPosition] = arr[r - 1];// 将最后一位数值赋值给已经被使用的cbPosition
        } while (cbRandCount < count);
        return RandomCard;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_wash:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("提示");
                builder.setMessage("确定要进行洗牌吗？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        wash();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                break;
            case R.id.btn_toGod:
                Bundle b = new Bundle();
                b.putStringArray("card",mRandomCard);
                b.putInt("people",mPersons);
                Intent i = new Intent(WolfKillActivity.this, JudgeActivity.class);
                i.putExtras(b);
                startActivity(i);
                break;
            case R.id.gl_v1:
                if (!isOneShowRole&&!black.contains("1")) {
                    if (checkIsShow(1)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[0]));
                    iv_1.setImageDrawable(drawable);
                    isOneShowRole = true;
                }else{
                    iv_1.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    iv_1.setFocusable(false);
                    iv_1.setClickable(false);
                    black.add("1");
                    isOneShowRole = false;
                }
                
                break;
            case R.id.gl_v2:

                if (!isTwoShowRole&&!black.contains("2")) {
                    if (checkIsShow(2)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[1]));
                    iv_2.setImageDrawable(drawable);
                    isTwoShowRole = true;
                }else{
                iv_2.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    black.add("2");
                    iv_2.setFocusable(false);
                    iv_2.setClickable(false);
                    isTwoShowRole = false;
            }
                break;
            case R.id.gl_v3:

                if (!isThrShowRole&&!black.contains("3")) {
                    if (checkIsShow(3)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[2]));
                    iv_3.setImageDrawable(drawable);
                    isThrShowRole = true;
                }else{
                    iv_3.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    black.add("3");
                    iv_3.setFocusable(false);
                    iv_3.setClickable(false);
                    isThrShowRole = false;
                }
                break;
            case R.id.gl_v4:
                if (!isFourShowRole&&!black.contains("4")) {
                    if (checkIsShow(4)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[3]));
                    iv_4.setImageDrawable(drawable);
                    isFourShowRole = true;
                }else{
                    iv_4.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    black.add("4");
                    iv_4.setFocusable(false);
                    iv_4.setClickable(false);
                    isFourShowRole = false;
                }
                break;
            case R.id.gl_v5:

                if (!isFiveShowRole&&!black.contains("5")) {
                    if (checkIsShow(5)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[4]));
                    iv_5.setImageDrawable(drawable);
                    isFiveShowRole = true;
                }else{
                    iv_5.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    iv_5.setFocusable(false);
                    iv_5.setClickable(false);
                    black.add("5");
                    isFiveShowRole = false;
                }
                break;
            case R.id.gl_v6:
                if (!isSixShowRole&&!black.contains("6")) {
                    if (checkIsShow(6)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[5]));
                    iv_6.setImageDrawable(drawable);
                    isSixShowRole = true;
                }else{
                    iv_6.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    iv_6.setFocusable(false);
                    iv_6.setClickable(false);
                    black.add("6");
                    isSixShowRole = false;
                }
                break;
            case R.id.gl_v7:

                if (!isSevShowRole&&!black.contains("7")) {
                    if (checkIsShow(7)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[6]));
                    iv_7.setImageDrawable(drawable);
                    isSevShowRole = true;
                }else{
                    iv_7.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    iv_7.setFocusable(false);
                    iv_7.setClickable(false);
                    black.add("7");
                    isSevShowRole = false;
                }
                break;
            case R.id.gl_v8:

                if (!isEigShowRole&&!black.contains("8")) {
                    if (checkIsShow(8)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[7]));
                    iv_8.setImageDrawable(drawable);
                    isEigShowRole = true;
                }else{
                    iv_8.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    iv_8.setFocusable(false);
                    iv_8.setClickable(false);
                    black.add("8");
                    isEigShowRole = false;
                }
                break;
            case R.id.gl_v9:

                if (!isNinShowRole&&!black.contains("9")) {
                    if (checkIsShow(9)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[8]));
                    iv_9.setImageDrawable(drawable);
                    isNinShowRole = true;
                }else{
                    iv_9.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    iv_9.setFocusable(false);
                    iv_9.setClickable(false);
                    black.add("9");
                    isNinShowRole = false;
                }
                break;
            case R.id.gl_v10:

                if (!isTenShowRole&&!black.contains("10")) {
                    if (checkIsShow(10)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[9]));
                    iv_10.setImageDrawable(drawable);
                    isTenShowRole = true;
                }else{
                    iv_10.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    iv_10.setFocusable(false);
                    iv_10.setClickable(false);
                    black.add("10");
                    isTenShowRole = true;
                }
                break;
            case R.id.gl_v11:

                if (!isEleShowRole&&!black.contains("11")) {
                    if (checkIsShow(11)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[10]));
                    iv_11.setImageDrawable(drawable);
                    isEleShowRole = true;
                }else{
                    iv_11.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    iv_11.setFocusable(false);
                    iv_11.setClickable(false);
                    black.add("11");
                    isEleShowRole = false;
                }
                break;
            case R.id.gl_v12:

                if (!isTweShowRole&&!black.contains("12")) {
                    if (checkIsShow(12)){
                        return;
                    }
                    Drawable drawable = getResources().getDrawable(getRoleResource(mRandomCard[11]));
                    iv_12.setImageDrawable(drawable);
                    isTweShowRole = true;
                }else{
                    iv_12.setImageDrawable(getResources().getDrawable(R.drawable.wolfkill_gray));
                    iv_12.setFocusable(false);
                    iv_12.setClickable(false);
                    black.add("12");
                    isTweShowRole = false;
                }
                break;
        }
    }

    private void wash() {
        showCard(mPersons);
        clearCard();
        mRandomCard = randomCard(mCard);
    }

    //"狼人","村民","猎人","女巫",,"白痴",,"狼人","守卫",,"丘比特，预言家
    private int getRoleResource(String name) {
        if (name.equals("村民")) {
            return R.drawable.villager;
        }
        if (name.equals("狼人")) {
            return R.drawable.wolf;
        }
        if (name.equals("猎人")) {
            return R.drawable.guner;
        }
        if (name.equals("女巫")) {
            return R.drawable.witch;
        }
        if (name.equals("丘比特")) {
            return R.drawable.qiubit;
        }
        if (name.equals("白痴")) {
            return R.drawable.fool;
        }
        if (name.equals("守卫")) {
            return R.drawable.safeguand;
        }
        if (name.equals("预言家")) {
            return R.drawable.seer;
        }
        return R.drawable.wolfkill;
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.gl_v1:
               toast(mRandomCard[0]);
                break;
            case R.id.gl_v2:
                toast(mRandomCard[1]);
                break;
            case R.id.gl_v3:
                toast(mRandomCard[2]);
                break;
            case R.id.gl_v4:
                toast(mRandomCard[3]);
                break;
            case R.id.gl_v5:
                toast(mRandomCard[4]);
                break;
            case R.id.gl_v6:
                toast(mRandomCard[5]);
                break;
            case R.id.gl_v7:
                toast(mRandomCard[6]);
                break;
            case R.id.gl_v8:
                toast(mRandomCard[7]);
                break;
            case R.id.gl_v9:
                toast(mRandomCard[8]);
                break;
            case R.id.gl_v10:
                toast(mRandomCard[9]);
                break;
            case R.id.gl_v11:
                toast(mRandomCard[10]);
                break;
            case R.id.gl_v12:
                toast(mRandomCard[11]);
                break;
        }
        
        
        return false;
    }








}
