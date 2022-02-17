package com.exmple.common.constant;

public enum ErrorMsgEnum {
    //1:正常 2:邮箱已存在 3:用户名已存在 4:用户名或密码错误 5:邮箱未激活 6:未知错误
    NORMAL(1,"正常"),
    USERNAME_EXIT(2,"用户名已存在"),
    EMAIL_EXIT(3,"邮箱已存在"),
    U_OR_P_ERROR(4,"用户名或密码错误"),
    EMAIL_NOT_ACTIVE(5,"邮箱未激活"),
    ERROR(6,"未知错误");
    private Integer code;
    private String value;
    ErrorMsgEnum(Integer code, String value){
        this.code=code;
        this.value=value;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByCode(Integer code){
        for (ErrorMsgEnum msg : ErrorMsgEnum.values()) {
            if (msg.getCode().equals(code)) return msg.getValue();
        }
        return null;
    }
}
