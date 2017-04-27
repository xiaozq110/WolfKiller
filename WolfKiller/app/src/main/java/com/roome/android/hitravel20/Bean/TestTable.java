package com.roome.android.hitravel20.Bean;

import java.lang.reflect.Array;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2017/4/24.
 */

public class TestTable extends BmobObject {
    public String[] getArray() {
        return text1;
    }

    public void setArray(String[] array) {
        this.text1 = array;
    }

    private String[] text1;
    public TestTable() {
            this.setTableName("Test");
    }


}
