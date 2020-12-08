package com.leet.CountryEnum;

public enum CountryEnum {
    one(1,"林冲"),two(2,"宋江"),three(3,"武松"),four(4,"李逵"),five(5,"吴用"),six(6,"杨志");

    private int retCode;
    private String retName;

    CountryEnum(int retCode, String retName) {
        this.retCode = retCode;
        this.retName = retName;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetName() {
        return retName;
    }

    public void setRetName(String retName) {
        this.retName = retName;
    }

    public static CountryEnum findCountry(int code){

        CountryEnum[] countries = CountryEnum.values();

        for(CountryEnum c : countries){
            if(code==c.getRetCode())
                return c;
        }

        return null;
    }
}
