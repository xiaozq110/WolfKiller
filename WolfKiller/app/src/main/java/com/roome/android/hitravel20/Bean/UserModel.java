package com.roome.android.hitravel20.Bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/4/19.
 */

public class UserModel extends BmobUser {
    private  String Sex;
    private  String AvatarStr;
    private  int age;
    private  String UserId;
    private  String EmailAddress;
    private  String UserPhoneNum;

    public String getUserPhoneNum() {  return UserPhoneNum;  }

    public void setUserPhoneNum(String userPhoneNum) { UserPhoneNum = userPhoneNum;  }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public void setSex(String sex) {  Sex = sex;  }

    public void setAvatarStr(String avatarStr) {
        AvatarStr = avatarStr;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public String getSex() {
        return Sex;
    }

    public String getAvatarStr() {
        return AvatarStr;
    }

    public int getAge() {
        return age;
    }

    public String getUserId() {
        return UserId;
    }

}
