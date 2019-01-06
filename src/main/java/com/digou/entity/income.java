package com.digou.entity;

public class income extends BaseEntity {
    public float income_1;
    public float income_7;
    public float income_30;
    public float income_365;
    public float income_all;

    public float[] this_day_income=new float[30];
    public float[] this_week_income=new float[20];
    public float[] this_month_income=new float[13];
    public float[] this_year_income=new float[5];


    public String[] day_Date=new String[30];
    //public String[] week_Date=new String[20];
    public int[] month_Date=new int[13];
    public int[] year_Date=new int[5];
}
