package com.smileduster.vsboard.web.shiro;

import com.smileduster.vsboard.api.model.po.Role;
import com.smileduster.vsboard.api.model.po.User;
import com.smileduster.vsboard.service.data.AuthMapper;
import com.smileduster.vsboard.service.data.IdMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserPwdRealm extends AuthorizingRealm {

    @Autowired
    private AuthMapper authMapper;

    @Autowired
    private IdMapper idMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        int userId = (int) principalCollection.getPrimaryPrincipal();

        Set<Role> roles = authMapper.selectRolesByUserId(userId);
        Set<String> roleStrings = new HashSet<>();
        for (Role r : roles) {
            roleStrings.add(r.getRoleName());
        }
        return new SimpleAuthorizationInfo(roleStrings);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken
    ) throws AuthenticationException {
        String raw = (String) authenticationToken.getPrincipal();
        long userNo = Long.parseLong(raw);
        int userId = idMapper.getUserId(userNo);
        User user = authMapper.selectUserById(userId);
        AuthenticationInfo info = new SimpleAuthenticationInfo(
                userId,
                user.getUserPwd(),
                ByteSource.Util.bytes(user.getUserPwdSalt()),
                getName()
        );
        if (getCredentialsMatcher().doCredentialsMatch(authenticationToken, info)) {
            return info;
        } else {
            throw new AuthenticationException();
        }
    }

}
