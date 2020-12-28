package com.xx.bootblog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.xx.bootblog.domain.po.AdminPo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {


    private  static final int keepTime = 1000 * 60 * 60 * 24;
    private static final String id = "id";


    /**
     * 获取请求头中的token值
     * @param request
     * @return token值
     */
    public static String createToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return token;
    }

    /**
     * 创建token值
     * @param adminPo 管理者对象
     * @return token值
     */
    public static String createToken(AdminPo adminPo){
        long now=System.currentTimeMillis();
        Date date = new Date(now);
        long exp=now+keepTime;
        Date expdate = new Date(exp);
        Map<String,Object> headerMap = new HashMap<String, Object>();
        headerMap.put("typ","jwt");
        headerMap.put("alg","HS256");
        String token = JWT.create().withHeader(headerMap)
                .withClaim(id, adminPo.getId())
                .withIssuedAt(date)
                .withExpiresAt(expdate)
                .sign(Algorithm.HMAC256(adminPo.getPassword()));
        return token;
    }

    /**
     * 解析token
     * @param token token值
     * @return 解析后的对象，可以获取token中的各种信息
     */
    public static DecodedJWT getClaims(String token){
        DecodedJWT decode = JWT.decode(token);
        return decode;
    }

    /**
     * 验证token
     * @param token token值
     * @return token是否过期 false为过期，true为未过期
     */
    public static boolean verifyToken(String token){
        DecodedJWT claims = getClaims(token);
        Date exp = claims.getExpiresAt();
        if (exp.after(new Date())){
            return true;
        }
        return false;
    }

}
