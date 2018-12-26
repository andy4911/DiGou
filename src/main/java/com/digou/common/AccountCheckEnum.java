package com.digou.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public enum AccountCheckEnum {
    SUCCESS(101), USERNAME_ERROR(102), PASSWORD_ERROR(103), IS_ONLINE(104),IS_BLACK(105);
	
	private int value = 0;
	
	private AccountCheckEnum(int value) { 
        this.value = value;
    }
	
    public int value() {
        return this.value;
    }
	
	public static AccountCheckEnum valueOf(int value) {
        switch (value) {
        case 101:
            return SUCCESS;
        case 102:
            return USERNAME_ERROR;
        case 103:
            return PASSWORD_ERROR;
        case 104:
            return IS_ONLINE;
        case 105:
            return IS_BLACK;
        default:
            return null;
        }
    }

}
