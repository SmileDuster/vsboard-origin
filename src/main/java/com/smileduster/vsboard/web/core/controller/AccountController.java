package com.smileduster.vsboard.web.core.controller;

import com.smileduster.vsboard.api.service.account.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AccountController {

    private final IUserService iuserService;

    public AccountController(IUserService iuserService) {
        this.iuserService = iuserService;
    }

    @PostMapping("login")
    @RequiresGuest
    public ResponseCode login(@RequestBody UserDTO userDTO ){
        Subject loginUser = SecurityUtils.getSubject();
        try {
            loginUser.login(new UsernamePasswordToken(user.getName(), user.getPwd()));
        } catch (AuthenticationException e) {
            return ResponseCode.WrongPwd;
        }
        Session session = loginUser.getSession();
        session.setAttribute(Const.USERNAME, user.getName());
        return ResponseCode.OK;
    }

    @PostMapping("quickLogin")
    @RequiresGuest
    public ResponseCode quickLogin(@RequestBody UserDTO userDTO){

    }

    @PostMapping
    @RequiresUser
    public ResponseCode checkAuth(@RequestBody UserDTO userDTO){

    }

    @PostMapping("register")
    @RequiresGuest
    public ResponseCode register(@RequestBody UserDTO user){
        userService.add(user);
        return ResponseCode.OK;
    }

    @PostMapping("updatePwd")
    @RequiresUser
    public ResponseCode updatePwd(char[] oldPwd, char[] newPwd){
        String name = (String) SecurityUtils.getSubject().getSession().getAttribute(Const.USERNAME);
        userService.updatePwd(name, newPwd);
        return ResponseCode.OK;
    }

}
