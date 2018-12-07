package com.digou.common;

public enum LoginCheckEnum {
    SUCCESS(101), USERNAME_ERROR(102), PASSWORD_ERROR(103), IS_ONLINE(104);
	
	private int value = 0;
	
	private LoginCheckEnum(int value) { 
        this.value = value;
    }
	
    public int value() {
        return this.value;
    }
	
	public static LoginCheckEnum valueOf(int value) {
        switch (value) {
        case 101:
            return SUCCESS;
        case 102:
            return USERNAME_ERROR;
        case 103:
            return PASSWORD_ERROR;
        case 104:
            return IS_ONLINE;
        default:
            return null;
        }
    }

}
