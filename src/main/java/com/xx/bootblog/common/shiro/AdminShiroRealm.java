package com.xx.bootblog.common.shiro;


import com.auth0.jwt.interfaces.Claim;
import com.xx.bootblog.common.exception.CustomException;
import com.xx.bootblog.common.exception.ExceptionType;
import com.xx.bootblog.domain.dto.UserInfo;
import com.xx.bootblog.mapper.AdminMapper;
import com.xx.bootblog.mapper.AuthorityMapper;
import com.xx.bootblog.mapper.RoleMapper;
import com.xx.bootblog.service.AdminService;
import com.xx.bootblog.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;

public class AdminShiroRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    AdminMapper adminMapper;

    @Lazy
    @Autowired
    AuthorityMapper authorityMapper;

    @Lazy
    @Autowired
    RoleMapper roleMapper;

    @Lazy
    @Autowired
    AdminService adminService;

    @Lazy
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

//    用户授权添加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        UserInfo userInfo = (UserInfo)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        添加角色
        for (String role : userInfo.getRoles()) {
            simpleAuthorizationInfo.addRole(role);
        }
//        添加权限
        for (String authority: userInfo.getAuthorities()) {
            simpleAuthorizationInfo.addStringPermission(authority);
        }
        return simpleAuthorizationInfo;
    }

//    用户登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getPrincipal();
        Claim adminId = JwtUtils.getClaims(token).getClaim("id");
        System.out.println("JWTid:"+adminId.asInt());
        UserInfo currentUser = adminService.getUserInfoByid(adminId.asInt());
        if (null == currentUser){
            throw new CustomException(ExceptionType.USER_INPUT_ERROR,"账户不存在");
        }
        String saveToken = (String) redisTemplate.opsForValue().get(currentUser.getId().toString());
        System.out.println("查询id:"+currentUser.getId().toString());
        System.out.println("token:"+saveToken);
        if (null == saveToken || !JwtUtils.verifyToken(token)){
            throw new CustomException(ExceptionType.USER_INPUT_ERROR,"token失效，请重新登录");
        }
//        第一个参数可以是admin对象，第二个参数是token，第三个参数是加密用的盐不加，这里我们用的自己的过滤器，第四个参数是该realm的名字
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(currentUser,saveToken, getName());
        return simpleAuthenticationInfo;
    }
}
