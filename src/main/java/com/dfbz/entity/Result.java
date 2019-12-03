package com.dfbz.entity;

public class Result {
    private  String msg;
    private SysUser sysuser;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SysUser getSysuser() {
        return sysuser;
    }

    public void setSysuser(SysUser sysuser) {
        this.sysuser = sysuser;
    }

    public Result(String msg, SysUser sysuser) {
        this.msg = msg;
        this.sysuser = sysuser;
    }

    public Result() {
    }
}
