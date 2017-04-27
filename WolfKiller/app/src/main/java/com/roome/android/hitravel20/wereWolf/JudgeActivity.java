package com.roome.android.hitravel20.wereWolf;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.roome.android.hitravel20.BaseActivity;
import com.roome.android.hitravel20.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class JudgeActivity extends BaseActivity implements View.OnClickListener,View.OnLongClickListener {
    private String[] card;
    private int mPersons = 0;
    private String dayOffContent;
    private Button btn_wolf, btn_police, btn_seer, btn_guard, btn_history, btn_qiubit, btn_witch, btn_end;
    private int day = 1;
    private int wolfCount = 3;
    private int villageCount;
    private int witchClick = 0;//0---救   1----毒
    private int PoliceLocation = 0;//警长的位置
    private boolean isNight = false;
    private boolean isWolfKillTime = false;
    private boolean isSeerTime = false;
    private boolean isWitchHealTime = false;
    private boolean isWitchKillTime = false;
    private boolean isChosePolice = false;
    private boolean HasPolice = false;
    private boolean isHunterDied = false;
    private ArrayList<String> historyContent;
    private int witchDrag = 1;
    private int witchpoison = 1;
    private boolean isHunterTime = false;
    private boolean isQiuBbitTime = false;
    private boolean isGuardTime = false;
    private List<String> killList;//存的是被杀的序号
    private Map<Integer,String> dieContent;//死后长按可显示死亡原因
    private TextView tv_play1, tv_play2, tv_play3, tv_play4,
            tv_play5, tv_play6, tv_play7, tv_play8, tv_play9,
            tv_play10, tv_play11, tv_play12, tv_godnotice, tv_rednotice,tv_day,tv_wolfCount,tv_village,tv_witchUsage;
    private ImageView iv_1, iv_2, iv_3, iv_4, iv_5, iv_6, iv_7, iv_8, iv_9, iv_10, iv_11, iv_12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_judge);
        Bundle b = getIntent().getExtras();
        mPersons = b.getInt("people", 0);
        card = b.getStringArray("card");
        init();
    }

    private void init() {
        historyContent = new ArrayList<String>();
        killList = new ArrayList<String>();
        dieContent = new HashMap<Integer, String>();
        btn_wolf = (Button) findViewById(R.id.btn_wolf);
        btn_end = (Button) findViewById(R.id.btn_end);
        btn_witch = (Button) findViewById(R.id.btn_witch);
        btn_seer = (Button) findViewById(R.id.btn_seer);
        btn_guard = (Button) findViewById(R.id.btn_guard);
        btn_police = (Button) findViewById(R.id.btn_police);
        btn_qiubit = (Button) findViewById(R.id.btn_qiubit);
        btn_history = (Button) findViewById(R.id.btn_history);
        btn_wolf.setOnClickListener(this);
        btn_end.setOnClickListener(this);
        btn_witch.setOnClickListener(this);
        btn_seer.setOnClickListener(this);
        btn_guard.setOnClickListener(this);
        btn_police.setOnClickListener(this);
        btn_qiubit.setOnClickListener(this);
        btn_history.setOnClickListener(this);

        //textview
        tv_day = (TextView) findViewById(R.id.tv_day);
        tv_day.setText("day 1");
        tv_wolfCount = (TextView) findViewById(R.id.tv_wolfCount);
        tv_wolfCount.setText("狼："+wolfCount);
        tv_witchUsage = (TextView) findViewById(R.id.tv_witchUseage);
        tv_witchUsage.setText("女巫药"+witchDrag+"----毒"+witchpoison);
        tv_village = (TextView) findViewById(R.id.tv_villageCount);
        villageCount = mPersons-wolfCount;
        tv_village.setText("其他："+String.valueOf(villageCount));
        tv_wolfCount = (TextView) findViewById(R.id.tv_wolfCount);
        tv_rednotice = (TextView) findViewById(R.id.tv_rednotice);
        tv_godnotice = (TextView) findViewById(R.id.tv_godnotice);
        tv_play1 = (TextView) findViewById(R.id.tv_judge_player1);
        tv_play2 = (TextView) findViewById(R.id.tv_judge_player2);
        tv_play3 = (TextView) findViewById(R.id.tv_judge_player3);
        tv_play4 = (TextView) findViewById(R.id.tv_judge_player4);
        tv_play5 = (TextView) findViewById(R.id.tv_judge_player5);
        tv_play6 = (TextView) findViewById(R.id.tv_judge_player6);
        tv_play7 = (TextView) findViewById(R.id.tv_judge_player7);
        tv_play8 = (TextView) findViewById(R.id.tv_judge_player8);
        tv_play9 = (TextView) findViewById(R.id.tv_judge_player9);
        tv_play10 = (TextView) findViewById(R.id.tv_judge_player10);
        tv_play11 = (TextView) findViewById(R.id.tv_judge_player11);
        tv_play12 = (TextView) findViewById(R.id.tv_judge_player12);

        tv_play1.setOnClickListener(this);
        tv_play2.setOnClickListener(this);
        tv_play3.setOnClickListener(this);
        tv_play4.setOnClickListener(this);
        tv_play5.setOnClickListener(this);
        tv_play6.setOnClickListener(this);
        tv_play7.setOnClickListener(this);
        tv_play8.setOnClickListener(this);
        tv_play9.setOnClickListener(this);
        tv_play10.setOnClickListener(this);
        tv_play11.setOnClickListener(this);
        tv_play12.setOnClickListener(this);
        
        tv_play1.setOnLongClickListener(this);
        tv_play2.setOnLongClickListener(this);
        tv_play3.setOnLongClickListener(this);
        tv_play4.setOnLongClickListener(this);
        tv_play5.setOnLongClickListener(this);
        tv_play6.setOnLongClickListener(this);
        tv_play7.setOnLongClickListener(this);
        tv_play8.setOnLongClickListener(this);
        tv_play9.setOnLongClickListener(this);
        tv_play10.setOnLongClickListener(this);
        tv_play11.setOnLongClickListener(this);
        tv_play12.setOnLongClickListener(this);

        showRole();
        //imageview
        iv_1 = (ImageView) findViewById(R.id.iv_check1);
        iv_2 = (ImageView) findViewById(R.id.iv_check2);
        iv_3 = (ImageView) findViewById(R.id.iv_check3);
        iv_4 = (ImageView) findViewById(R.id.iv_check4);
        iv_5 = (ImageView) findViewById(R.id.iv_check5);
        iv_6 = (ImageView) findViewById(R.id.iv_check6);
        iv_7 = (ImageView) findViewById(R.id.iv_check7);
        iv_8 = (ImageView) findViewById(R.id.iv_check8);
        iv_9 = (ImageView) findViewById(R.id.iv_check9);
        iv_10 = (ImageView) findViewById(R.id.iv_check10);
        iv_11 = (ImageView) findViewById(R.id.iv_check11);
        iv_12 = (ImageView) findViewById(R.id.iv_check12);
    }

    private void showRole() {
        switch (mPersons) {
            case 7:

                tv_play1.setText("1." + card[0]);
                tv_play2.setText("2." + card[1]);
                tv_play3.setText("3." + card[2]);
                tv_play4.setText("4." + card[3]);
                tv_play5.setText("5." + card[4]);
                tv_play6.setText("6." + card[5]);
                tv_play7.setText("7." + card[6]);
                tv_play1.setVisibility(View.VISIBLE);
                tv_play2.setVisibility(View.VISIBLE);
                tv_play3.setVisibility(View.VISIBLE);
                tv_play4.setVisibility(View.VISIBLE);
                tv_play5.setVisibility(View.VISIBLE);
                tv_play6.setVisibility(View.VISIBLE);
                tv_play7.setVisibility(View.VISIBLE);
                break;
            case 8:

                tv_play1.setText("1." + card[0]);
                tv_play2.setText("2." + card[1]);
                tv_play3.setText("3." + card[2]);
                tv_play4.setText("4." + card[3]);
                tv_play5.setText("5." + card[4]);
                tv_play6.setText("6." + card[5]);
                tv_play7.setText("7." + card[6]);
                tv_play8.setText("8." + card[7]);
                tv_play1.setVisibility(View.VISIBLE);
                tv_play2.setVisibility(View.VISIBLE);
                tv_play3.setVisibility(View.VISIBLE);
                tv_play4.setVisibility(View.VISIBLE);
                tv_play5.setVisibility(View.VISIBLE);
                tv_play6.setVisibility(View.VISIBLE);
                tv_play7.setVisibility(View.VISIBLE);
                tv_play8.setVisibility(View.VISIBLE);

                break;
            case 9:

                tv_play1.setText("1." + card[0]);
                tv_play2.setText("2." + card[1]);
                tv_play3.setText("3." + card[2]);
                tv_play4.setText("4." + card[3]);
                tv_play5.setText("5." + card[4]);
                tv_play6.setText("6." + card[5]);
                tv_play7.setText("7." + card[6]);
                tv_play8.setText("8." + card[7]);
                tv_play9.setText("9." + card[8]);
                tv_play1.setVisibility(View.VISIBLE);
                tv_play2.setVisibility(View.VISIBLE);
                tv_play3.setVisibility(View.VISIBLE);
                tv_play4.setVisibility(View.VISIBLE);
                tv_play5.setVisibility(View.VISIBLE);
                tv_play6.setVisibility(View.VISIBLE);
                tv_play7.setVisibility(View.VISIBLE);
                tv_play8.setVisibility(View.VISIBLE);
                tv_play9.setVisibility(View.VISIBLE);
                break;
            case 10:

                tv_play1.setText("1." + card[0]);
                tv_play2.setText("2." + card[1]);
                tv_play3.setText("3." + card[2]);
                tv_play4.setText("4." + card[3]);
                tv_play5.setText("5." + card[4]);
                tv_play6.setText("6." + card[5]);
                tv_play7.setText("7." + card[6]);
                tv_play8.setText("8." + card[7]);
                tv_play9.setText("9." + card[8]);
                tv_play10.setText("10." + card[9]);
                tv_play1.setVisibility(View.VISIBLE);
                tv_play2.setVisibility(View.VISIBLE);
                tv_play3.setVisibility(View.VISIBLE);
                tv_play4.setVisibility(View.VISIBLE);
                tv_play5.setVisibility(View.VISIBLE);
                tv_play6.setVisibility(View.VISIBLE);
                tv_play7.setVisibility(View.VISIBLE);
                tv_play8.setVisibility(View.VISIBLE);
                tv_play9.setVisibility(View.VISIBLE);
                tv_play10.setVisibility(View.VISIBLE);
                break;
            case 11:

                tv_play1.setText("1." + card[0]);
                tv_play2.setText("2." + card[1]);
                tv_play3.setText("3." + card[2]);
                tv_play4.setText("4." + card[3]);
                tv_play5.setText("5." + card[4]);
                tv_play6.setText("6." + card[5]);
                tv_play7.setText("7." + card[6]);
                tv_play8.setText("8." + card[7]);
                tv_play9.setText("9." + card[8]);
                tv_play10.setText("10." + card[9]);
                tv_play11.setText("11." + card[10]);

                tv_play1.setVisibility(View.VISIBLE);
                tv_play2.setVisibility(View.VISIBLE);
                tv_play3.setVisibility(View.VISIBLE);
                tv_play4.setVisibility(View.VISIBLE);
                tv_play5.setVisibility(View.VISIBLE);
                tv_play6.setVisibility(View.VISIBLE);
                tv_play7.setVisibility(View.VISIBLE);
                tv_play8.setVisibility(View.VISIBLE);
                tv_play9.setVisibility(View.VISIBLE);
                tv_play10.setVisibility(View.VISIBLE);
                tv_play11.setVisibility(View.VISIBLE);
                break;
            case 12:
                tv_play1.setText("1." + card[0]);
                tv_play2.setText("2." + card[1]);
                tv_play3.setText("3." + card[2]);
                tv_play4.setText("4." + card[3]);
                tv_play5.setText("5." + card[4]);
                tv_play6.setText("6." + card[5]);
                tv_play7.setText("7." + card[6]);
                tv_play8.setText("8." + card[7]);
                tv_play9.setText("9." + card[8]);
                tv_play10.setText("10." + card[9]);
                tv_play11.setText("11." + card[10]);
                tv_play12.setText("12." + card[11]);

                tv_play1.setVisibility(View.VISIBLE);
                tv_play2.setVisibility(View.VISIBLE);
                tv_play3.setVisibility(View.VISIBLE);
                tv_play4.setVisibility(View.VISIBLE);
                tv_play5.setVisibility(View.VISIBLE);
                tv_play6.setVisibility(View.VISIBLE);
                tv_play7.setVisibility(View.VISIBLE);
                tv_play8.setVisibility(View.VISIBLE);
                tv_play9.setVisibility(View.VISIBLE);
                tv_play10.setVisibility(View.VISIBLE);
                tv_play11.setVisibility(View.VISIBLE);
                tv_play12.setVisibility(View.VISIBLE);
                break;

        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_wolf:
                if (!checkPolice()){
                    return;
                }
                isNight = true;
                clearBoolean();
                isWolfKillTime = true;
                int wolfonepos = GetWolfPosition().get(0);
                int wolftwopos = GetWolfPosition().get(1);
                int wolfthrpos = GetWolfPosition().get(2);
                //  ShowAims(wolfonepos,wolftwopos,wolfthrpos);
                //2.选中击杀目标（再次确认）
                //3.修改状态
                if (day == 1) {
                    tv_rednotice.setText("请狼人互相确认身份");
                    tv_godnotice.setText("杀人，狼人请统一意见");
                } else {
                    tv_godnotice.setText("杀人，狼人请统一意见");
                }
                break;
            case R.id.btn_witch:
                if (!checkPolice()){
                    return;
                }
                clearBoolean();
                if (witchClick == 0) {
                    //1.解药
                    witchClick = 1;
                    isWitchHealTime = true;
                    tv_godnotice.setText("请女巫选择救人");
                } else if (witchClick == 1) {
                    //2.毒药
                    witchClick = 0;
                    isWitchKillTime = true;
                    tv_godnotice.setText("请女巫选择毒人");
                }
                break;
            case R.id.btn_police:
                clearBoolean();
                isChosePolice = true;
                tv_godnotice.setText("选择其中一位成为警长");
                break;
            case R.id.btn_seer:
                if (!checkPolice()){
                    return;
                }
                clearBoolean();
                isSeerTime = true;
                tv_godnotice.setText("亮一个人的身份");
                break;

            case R.id.btn_guard:
                if (!checkPolice()){
                    return;
                }
                clearBoolean();
                isGuardTime = true;
                tv_godnotice.setText("开始守卫");
                break;

            case R.id.btn_end:
                clearBoolean();
                if (!isNight){//还没开始呢
                    SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("提示");
                    dialog.setContentText("还没结束呢");
                    dialog.show();
                    return;
                }
                day++;
                isNight = false;
                tv_day.setText("day"+String.valueOf(day));
                if (isHunterDied){
                    tv_godnotice.setText("猎人死亡，带走一位垫背");
                    historyContent.add("猎人死亡，带走一位垫背");

                    isHunterTime = true;
                }
                if (!HasPolice){
                    tv_rednotice.setText("重新竞选一位警长");
                }

                tv_godnotice.setText("天亮了，讨论时间");

                break;

            case R.id.btn_history:
                Bundle b = new Bundle();
                b.putStringArrayList("list",historyContent);
                Intent i = new Intent(this,WolfHistoryActivity.class);
                i.putExtras(b);
                startActivity(i);
                break;


            case R.id.tv_judge_player1:
                checkCheck(1,tv_play1,iv_1);
                break;
            case R.id.tv_judge_player2:
                //isSeerTime||isWolfKillTime||isWitchTime||isGuardTime||isHunterTime||isQiuBbitTime){
                checkCheck(2,tv_play2,iv_2);
               /* if (isWolfKillTime) {
                    if (isDied(2))return;
                    showWolfKillDialog(card[1], tv_play2, "2");
                } else if (isWitchHealTime) {
                    if (!isDied(2))return;
                    showWitchHealDialog(card[1], tv_play2,"2");
                } else if (isWitchKillTime) {
                    if (isDied(2))return;
                    showWitchKillDialog(card[1], tv_play2, "2");
                } else if (isChosePolice&&!HasPolice) {
                    showPoliceDialog(card[1], iv_2, 2);
                } else if (isSeerTime) {
                    doSeer(2);
                } else if (isGuardTime) {
                    showGrandDialog(card[1], tv_play2, 2);
                } else {
                    return;
                }*/
                break;
            case R.id.tv_judge_player3:
                //isSeerTime||isWolfKillTime||isWitchTime||isGuardTime||isHunterTime||isQiuBbitTime){
                checkCheck(3,tv_play3,iv_3);

               /* if (isWolfKillTime) {
                    if (isDied(3))return;
                    showWolfKillDialog(card[2], tv_play3, "3");
                } else if (isWitchHealTime) {
                    if (!isDied(3))return;
                    showWitchHealDialog(card[2], tv_play3,"3");
                } else if (isWitchKillTime) {
                    if (isDied(3))return;
                    showWitchKillDialog(card[2], tv_play3, "3");
                } else if (isChosePolice&&!HasPolice) {
                    showPoliceDialog(card[2], iv_3, 3);
                } else if (isSeerTime) {
                    doSeer(3);
                } else if (isGuardTime) {
                    showGrandDialog(card[2], tv_play3, 3);
                }else {
                    return;
                }*/
                break;


            case R.id.tv_judge_player4:
                //isSeerTime||isWolfKillTime||isWitchTime||isGuardTime||isHunterTime||isQiuBbitTime){
                checkCheck(4,tv_play4,iv_4);
               /* if (isWolfKillTime) {
                    if (isDied(4))return;
                    showWolfKillDialog(card[3], tv_play4, "4");
                } else if (isWitchHealTime) {
                    if (!isDied(4))return;
                    showWitchHealDialog(card[3], tv_play4,"4");
                } else if (isWitchKillTime) {
                    if (isDied(4))return;
                    showWitchKillDialog(card[3], tv_play4, "4");
                } else if (isChosePolice&&!HasPolice) {
                    showPoliceDialog(card[3], iv_4, 4);
                }  else if (isSeerTime) {
                    doSeer(4);
                } else if (isGuardTime) {
                    showGrandDialog(card[3], tv_play4, 4);
                }else {
                    return;
                }*/
                break;

            case R.id.tv_judge_player5:
                checkCheck(5,tv_play5,iv_5);
            break;

            case R.id.tv_judge_player6:
                checkCheck(6,tv_play6,iv_6);
            break;
            case R.id.tv_judge_player7:
                checkCheck(7,tv_play7,iv_7);
            break;
            case R.id.tv_judge_player8:
                checkCheck(8,tv_play8,iv_8);
            break;
            case R.id.tv_judge_player9:
                checkCheck(9,tv_play9,iv_9);
            break;
            case R.id.tv_judge_player10:
                checkCheck(10,tv_play10,iv_10);
            break;
            case R.id.tv_judge_player11:
                checkCheck(11,tv_play11,iv_11);
            break;
            case R.id.tv_judge_player12:
                checkCheck(12,tv_play12,iv_12);
            break;

        }






    }

    private void doSeer(int i) {
        tv_godnotice.setText("预言家查看了" + i + "号身份");
        historyContent.add("day"+day+"---"+"预言家查看了" + i + "号身份");
        tv_rednotice.setText("守卫睁眼，选择守护对象");
    }
    private void showWitchHealDialog(final String role, final TextView view,final String pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要救" + role + "吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (witchDrag==0){
                    tv_rednotice.setText("女巫解药已经使用掉了");
                    return;
                }

                tv_godnotice.setText(role + "被救活。");
                witchDrag = 0;
                setDataChanged(3,witchDrag,witchpoison);
                historyContent.add("day"+day+"---"+role + "被救活。");
                tv_rednotice.setText("女巫毒药要不要用。");
                isWitchHealTime = false;
                if (role.equals("猎人")){
                    isHunterDied = false;
                }
                savePeople(Integer.parseInt(pos),role);
                view.setTextColor(Color.BLACK);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tv_rednotice.setText("女巫毒药要不要用。");
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showWitchKillDialog(final String role, final TextView view, final String pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要毒死" + role + "吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (witchpoison==0){
                    tv_rednotice.setText("女巫毒药已经使用掉了");
                    return;
                }

                tv_godnotice.setText(String.valueOf(pos) + "号" + role + "被毒死。");
                historyContent.add("day"+day+"---"+String.valueOf(pos) + "号" + role + "被毒死。");
                dieContent.put(Integer.parseInt(pos),"day"+day+"---"+String.valueOf(pos) + "号" + role + "被毒死。");
                killList.add(pos);
                witchpoison = 0;
                if (role.equals("狼人")){
                  wolfCount--;
                    setDataChanged(1,wolfCount,0);
                }else{
                    villageCount--;
                    setDataChanged(2,villageCount,0);
                }

                setDataChanged(3,witchDrag,witchpoison);

                if (role.equals("猎人")){
                    isHunterDied = true;
                }
                isWitchKillTime = false;
                tv_rednotice.setText("预言家预言身份");
                view.setTextColor(Color.RED);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    private void showPoliceDialog(final String role, final ImageView view, final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要选择" + pos+"号" + "成为警长吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isChosePolice = false;
                btn_police.setText("更换警长");
                HasPolice = true;
                PoliceLocation = pos;
                tv_godnotice.setText(pos + "号" + role + "成为警长。");
                historyContent.add("day"+day+"---"+pos + "号" + role + "成为警长。");
                tv_rednotice.setText("天黑请闭眼，狼人开杀！");
                view.setImageResource(R.drawable.police);
                view.setVisibility(View.VISIBLE);

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showHunterDialog(final String role, final TextView view, final String pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要带走" + role + "成为垫背吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isHunterTime = false;
                killList.add(pos);
                tv_godnotice.setText(pos + "号" + role + "成功被猎人带走。");
                historyContent.add("day"+day+"---"+pos + "号" + role + "成功被猎人带走。");
                dieContent.put(Integer.parseInt(pos),"day"+day+"---"+pos + "号" + role + "成功被猎人带走。");
                view.setTextColor(Color.RED);

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showGrandDialog(final String role, final TextView view, final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定守护" + role + "吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isGuardTime = false;
                    tv_godnotice.setText(pos + "号" + role + "为守护对象。");
                historyContent.add("day"+day+"---"+pos + "号" + role + "为守护对象。");
                      savePeople(pos,role);

                if (role.equals("猎人")){
                    isHunterDied = false;
                }
                 view.setTextColor(Color.BLACK);
                    if (killList.size() > 0) {
                        StringBuffer buffer = new StringBuffer();
                        for (int i = 0; i < killList.size(); i++) {
                            int index = Integer.parseInt(killList.get(i));
                                String name = card[index-1];
                                if (i == killList.size() - 1||i==0) {
                                    buffer.append(killList.get(i) + "号" + name);
                                } else buffer.append(killList.get(i) + "号" + name + ",");

                        }
                        dayOffContent = "天亮了，昨天晚上" + buffer.toString() + "死了";
                       if (isDied(PoliceLocation)){
                           shotPolice();
                       }
                    } else {
                        dayOffContent ="天亮了，昨天晚上是个风平浪静的夜晚。";
                    }
                tv_godnotice.setText(dayOffContent);
                historyContent.add("day"+day+"---"+dayOffContent);
                tv_rednotice.setText("结束一天，请睁眼");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showQiubitDialog(String role, String role2, final TextView view1, final TextView view2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要关联" + role + "和" + role2 + "吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                isQiuBbitTime = false;
                view1.setTextColor(Color.GREEN);
                view2.setTextColor(Color.GREEN);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();

    }

    private void clearBoolean(){
             isWolfKillTime = false;
             isSeerTime = false;
             isWitchHealTime = false;
             isWitchKillTime = false;
             isChosePolice = false;
             isHunterTime = false;
             isQiuBbitTime = false;
             isGuardTime = false;
        
    }

    //提示是否要杀掉该角色
    private void showWolfKillDialog(final String role, final TextView view, final String pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("确定要杀死" + pos+ "号吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                killList.add(pos);
                if (!role.equals("狼人")){
                    --villageCount;
                    setDataChanged(2,villageCount,0);
                }else{
                    wolfCount--;
                    log("狼人杀死=="+wolfCount);
                    setDataChanged(1,wolfCount,0);
                }
                if (role.equals("猎人")){
                    isHunterDied = true;
                }
                String text =String.valueOf(pos) + "号" + role + "被狼人杀掉。";
                tv_godnotice.setText(String.valueOf(pos) + "号" + role + "被狼人杀掉。");
                dieContent.put(Integer.parseInt(pos),"day"+day+"---"+text);
                historyContent.add("day"+day+"---"+String.valueOf(pos) + "号" + role + "被狼人杀掉。");
                tv_rednotice.setText("女巫请睁眼，今晚他被杀，要不要救");
                isWolfKillTime = false;
                view.setTextColor(Color.RED);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void ShowAims(int wolfonepos, int wolftwopos, int wolfthrpos) {
        if (wolfonepos == 0 || wolftwopos == 0 || wolfthrpos == 0) {
            tv_play1.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 1 || wolftwopos == 1 || wolfthrpos == 1) {
            tv_play2.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 2 || wolftwopos == 2 || wolfthrpos == 2) {
            tv_play3.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 3 || wolftwopos == 3 || wolfthrpos == 3) {
            tv_play4.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 4 || wolftwopos == 4 || wolfthrpos == 4) {
            tv_play5.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 5 || wolftwopos == 5 || wolfthrpos == 5) {
            tv_play6.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 6 || wolftwopos == 6 || wolfthrpos == 6) {
            tv_play7.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 7 || wolftwopos == 7 || wolfthrpos == 7) {
            tv_play8.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 8 || wolftwopos == 8 || wolfthrpos == 8) {
            tv_play9.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 9 || wolftwopos == 9 || wolfthrpos == 9) {
            tv_play10.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 10 || wolftwopos == 10 || wolfthrpos == 10) {
            tv_play11.setVisibility(View.INVISIBLE);
        }
        if (wolfonepos == 11 || wolftwopos == 11 || wolfthrpos == 11) {
            tv_play12.setVisibility(View.INVISIBLE);
        }

    }

    private void showAllPlayers() {
        tv_play1.setVisibility(View.VISIBLE);
        tv_play2.setVisibility(View.VISIBLE);
        tv_play3.setVisibility(View.VISIBLE);
        tv_play4.setVisibility(View.VISIBLE);
        tv_play5.setVisibility(View.VISIBLE);
        tv_play6.setVisibility(View.VISIBLE);
        tv_play7.setVisibility(View.VISIBLE);
        tv_play8.setVisibility(View.VISIBLE);
        tv_play9.setVisibility(View.VISIBLE);
        tv_play10.setVisibility(View.VISIBLE);
        tv_play11.setVisibility(View.VISIBLE);
        tv_play12.setVisibility(View.VISIBLE);
    }

    //狼人杀人时间，可以点击确认杀人对象
    private List<Integer> GetWolfPosition() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < card.length; i++) {
            if (card[i].equals("狼人")) {
                list.add(i);
            }
        }
        return list;
    }
    private void shotPolice(){
        HasPolice = false;
        switch (PoliceLocation){
            case 1:

                iv_1.setVisibility(View.INVISIBLE);
            break;
            case 2:
                iv_2.setVisibility(View.INVISIBLE);
            break;
            case 3:
                iv_3.setVisibility(View.INVISIBLE);
            break;
            case 4:
                iv_4.setVisibility(View.INVISIBLE);
            break;
            case 5:
                iv_5.setVisibility(View.INVISIBLE);
            break;
            case 6:
                iv_6.setVisibility(View.INVISIBLE);
            break;
            case 7:
                iv_7.setVisibility(View.INVISIBLE);
            break;
            case 8:
                iv_8.setVisibility(View.INVISIBLE);
            break;
            case 9:
                iv_9.setVisibility(View.INVISIBLE);
            break;
            case 10:
                iv_10.setVisibility(View.INVISIBLE);
            break;
            case 12:
                iv_12.setVisibility(View.INVISIBLE);
            break;
            case 11:
                iv_11.setVisibility(View.INVISIBLE);
            break;


        }
    }

    //pos---1
private void checkCheck(int pos,TextView tv,ImageView iv){
    String posStr = String.valueOf(pos);
    if (isWolfKillTime) {
        if (isDied(pos))return;
        showWolfKillDialog(card[pos-1], tv,posStr);
    } else if (isWitchHealTime) {
        if (!isDied(pos))return;
        showWitchHealDialog(card[pos-1], tv,posStr);
    } else if (isWitchKillTime) {
        if (isDied(pos))return;
        showWitchKillDialog(card[pos-1], tv, posStr);
    } else if (isChosePolice&&!HasPolice) {
        showPoliceDialog(card[pos-1], iv, pos);
    } else if (isSeerTime) {
        doSeer(pos);
    } else if (isGuardTime) {
        showGrandDialog(card[pos-1], tv, pos);
    }  else if (isHunterTime) {
        showHunterDialog(card[pos-1],tv,posStr);
    }else {
        return;
    }


}


    private boolean checkPolice(){
        if (!HasPolice&&!isNight) {
            SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE);
            dialog.setTitleText("提示");
            dialog.setContentText("请先选警长");
            dialog.show();
            return false;
        }
        return true;
    }

private void savePeople(int index,String role){
    if (!role.equals("狼人")){
        villageCount++;
        setDataChanged(2,villageCount,0);
    }else{
        wolfCount++;
        setDataChanged(1,wolfCount,0);
    }
    if (killList.size()>0){
        for (int i=0;i<killList.size();i++){
                if (killList.get(i).equals(String.valueOf(index))){
                    killList.remove(i);
                }
        }
    }
}

    private boolean isDied(int pos){
        if (killList.size()>0){
            for (int i=0;i<killList.size();i++){
                if (killList.get(i).equals(String.valueOf(pos))){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isDied(String role){
        for (int i = 0;i<card.length;i++){
            if (card[i].equals(role)) {
                return true;
            }
        }
        return false;
    }


    private void setDataChanged(int type,int count1,int count2){
        if (checkWinner()){
            return;
        }
        switch (type){
            case 1:
             log("count1="+count1);
                tv_wolfCount.setText("狼："+count1);
                break;
            case 2:
                tv_village.setText("其他："+count1);
                break;
            case 3:
                tv_witchUsage.setText("女巫药"+count1+"---毒"+count2);
                break;

        }
    }

    private boolean checkWinner() {
        if (wolfCount==0){
            Win(true);
            return true;
        }
        if (villageCount==0){
            Win(false);
            return true;
        }
        return false;
    }

    private void Win(boolean isGood){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("恭喜");
        String content = "";
        if (isGood){
            content = "恭喜好人获得胜利!";
        }else{
            content = "恭喜狼人获得胜利！";
        }
        builder.setMessage(content);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                JudgeActivity.this.finish();
            }
        });
        builder.create().show();
    }


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()){
            case R.id.tv_judge_player1:
                    showDiedDialog(1);

                break;
            case R.id.tv_judge_player2:
                    showDiedDialog(2);
                break;
            case R.id.tv_judge_player3:
                    showDiedDialog(3);
                break;
            case R.id.tv_judge_player4:
                showDiedDialog(4);
                break;
            case R.id.tv_judge_player5:
                    showDiedDialog(5);

                break;
            case R.id.tv_judge_player6:
                    showDiedDialog(6);
                break;
            case R.id.tv_judge_player7:
                    showDiedDialog(7);
                break;
            case R.id.tv_judge_player8:
                showDiedDialog(8);
                break;
            case R.id.tv_judge_player9:
                    showDiedDialog(9);

                break;
            case R.id.tv_judge_player10:
                    showDiedDialog(10);
                break;
            case R.id.tv_judge_player11:
                    showDiedDialog(11);
                break;
            case R.id.tv_judge_player12:
                showDiedDialog(12);
                break;



        }
        return false;
    }
    private void showDiedDialog( int pos) {
        if (isDied(pos)){
            SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE);
            dialog.setTitleText("精彩回放");
            dialog.setContentText(dieContent.get(pos));
            dialog.show();
        }
        }

}
