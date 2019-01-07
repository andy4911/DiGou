package com.digou.entity;



public class CUser extends BaseEntity {
	
    public int userID;
    public int isBlack;
    //isBlack =-1时 被删除
    public String username;
    public String nickname;
    public String password;
    public String portraitURL;
    public String address;
}
