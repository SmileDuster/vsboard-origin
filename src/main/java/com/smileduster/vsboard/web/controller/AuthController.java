package com.smileduster.vsboard.web.controller;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import com.smileduster.vsboard.api.model.dto.UserDTO;
import com.smileduster.vsboard.api.tools.Generator;
import com.smileduster.vsboard.web.form.LoginForm;
import com.smileduster.vsboard.web.form.RegisterForm;
import com.smileduster.vsboard.web.form.UpdatePwdForm;
import com.smileduster.vsboard.api.service.IAuthService;
import com.smileduster.vsboard.api.tools.DefaultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IAuthService authService;
    private final Generator generator;

    public AuthController(IAuthService authService, Generator generator) {
        this.authService = authService;
        this.generator = generator;
    }

    @PostMapping("/login")
    public Response<?> login(@RequestBody LoginForm form){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(form.getUserNo(), form.getPwd()));
        } catch (AuthenticationException e) {
            return Response.create(ResponseCode.loginFail);
        }
        Session session = subject.getSession(true);
        session.setAttribute("userNo", form.getUserNo());
        return Response.create();
    }

    @PostMapping("/register")
    public Response<?> register(@RequestBody RegisterForm form){
        UserDTO dto = new UserDTO();
        dto.setUserName(form.getUserName());
        dto.setUserEmail(form.getUserEmail());
        ByteSource salt = generator.getSalt();
        String hashedPwd = new Sha256Hash(form.getUserPwd(), salt, 1024)
                .toBase64();
        dto.setUserPwd(hashedPwd);
        dto.setUserPwdSalt(salt.getBytes());
//        log.error(String.valueOf(salt.getBytes().length));
        dto.setUserLastIP("0:0:0:0");
        return authService.createUser(dto);
    }

    @PostMapping("/updatePwd")
    public Response<?> updatePwd(@RequestBody UpdatePwdForm form){
        return Response.todo();
    }

}
