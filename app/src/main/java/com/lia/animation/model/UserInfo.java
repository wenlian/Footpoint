//package com.lia.animation.model;
//
//import com.lia.animation.util.UserInfoUtil;
//
//public class UserInfo {
//   public static enum SEX {
//        MALE ,
//        FEMALE,
//        UNKNOWN
//    };
//
//    private String birthday = "";
//    private int height = -1;
//    private int weight = -1;
//    private SEX sex = SEX.UNKNOWN;
//    private int age = -1;
//
//    public UserInfo(String birthday,int height, int weight,SEX sex) {
//        this.birthday = birthday;
//        this.height = height;
//        this.weight = weight;
//        this.sex = sex;
//        this.age = UserInfoUtil.caculateAge(birthday);
//    }
//
//    public String getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(String birthday) {
//        this.birthday = birthday;
//        this.age = UserInfoUtil.caculateAge(birthday);
//    }
//
//    public int getHeight() {
//        return height;
//    }
//
//    public void setHeight(int height) {
//        this.height = height;
//    }
//
//    public int getWeight() {
//        return weight;
//    }
//
//    public void setWeight(int weight) {
//        this.weight = weight;
//    }
//
//    public SEX getSex() {
//        return sex;
//    }
//
//    public void setSex(SEX sex) {
//        this.sex = sex;
//    }
//
//    public void setAge(int age){
//        this.age = age;
//    }
//
//    public int getAge(){
//        return age;
//    }
//
//}