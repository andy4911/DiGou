package com.digou.entity;

public class SellerUser {

    /**
     * url : http://lc-tp28gntm.cn-n1.lcfile.com/d2477ef0b23ec61b963f.jpg
     * telephone : 18392128500
     * shopName : Yi DianDian
     * nickname : YiDianDian
     * description : We sell drinks, various drinks, milk tea, lemonade, orange juice, etc.
     * major : Drink
     */

    private int id;
    private int isPass;
    private int isBlack;
    private int num;
    private String url;
    private String telephone;
    private String shopName;
    private String nickname;
    private String description;
    private String major;
    private String password;

    /**
     * url : http://lc-tp28gntm.cn-n1.lcfile.com/d2477ef0b23ec61b963f.jpg
     * telephone : 18392128500
     * shopName : Yi DianDian
     * nickname : YiDianDian
     * description : We sell drinks, various drinks, milk tea, lemonade, orange juice, etc.
     * major : Drink
     */

    public SellerUser(){

    }


    public SellerUser(String url, String telephone, String shopName, String nickname, String description, String major, String password) {
        this.url = url;
        this.telephone = telephone;
        this.shopName = shopName;
        this.nickname = nickname;
        this.description = description;
        this.major = major;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIsBlack(int isBlack) {
        this.isBlack = isBlack;
    }

    public int getIsBlack() {
        return isBlack;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    public int getIsPass() {
        return isPass;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
