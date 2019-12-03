package com.dfbz.controller;


import com.dfbz.Utils.EncryptUtils;
import com.dfbz.entity.Result;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

//替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/Login")
public class LoginController {

    @Autowired
    SysUserService sysUserService;

    @RequestMapping(value = "check",produces = "html/text;charset=utf-8")
    public Result checkAccount(@RequestBody Map<String, Object> map, HttpSession session) {
        Result result = new Result();
        String msg = "";
        if (!StringUtils.isEmpty(map.get("code"))) {
            Object code = map.get("code");
            Object checkCode = session.getAttribute("checkCode");
            if (checkCode.equals(code)) {
                SysUser sysUser = new SysUser();
                String account = (String) map.get("account");
                String password = (String) map.get("password");
                sysUser.setUsername(account);
                sysUser.setPassword(EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(password) + account));
                SysUser loginUser = sysUserService.selectOne(sysUser);
                if (loginUser != null) {
                    msg = "登录成功";
                    result.setSysuser(loginUser);
                } else {
                    msg = "账号或者密码错误";
                }
            } else {
                msg = "验证码错误";
            }
        } else {
            msg = "验证码不能为空";
        }
        result.setMsg(msg);
        return result;
    }


}
