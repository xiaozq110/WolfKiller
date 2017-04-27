package com.roome.android.hitravel20.wereWolf;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.roome.android.hitravel20.R;

import java.util.ArrayList;

public class WolfHistoryActivity extends Activity {

    ArrayList<String> mList;
    private ListView lsv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wolf_history);
        mList = getIntent().getExtras().getStringArrayList("list");
        if (mList.size()==0){
            mList .add("暂无信息");
        }
        lsv = (ListView) findViewById(R.id.lsv);
        lsv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,mList));
    }
}
