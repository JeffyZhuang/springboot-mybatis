package com.zzh.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @Author: zzh
 * @Description:JWT加密校验工具类
 * @Date: 2018/12/8
 */

@Slf4j
public class JWTUtil {

    private final static long EXPIRE_TIME = 5 * 60 * 1000;

    /**
     * 校验token是否正确
     *
     * @param token    密匙
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    public static boolean verify(String token, String userName, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userName", userName).build();
            DecodedJWT jwt = verifier.verify(token);
            log.info("——————jwt————————", jwt);
            return true;
        } catch (Exception exception) {
            log.error("执行JWT验证失败");
            return false;
        }
    }

    /**
     * 通过token无需密码解密
     *
     * @param token
     * @return
     */
    public static String getUserName(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("userName").asString();
        } catch (JWTDecodeException e) {
            log.error("获取失败");
            return null;
        }
    }

    /**
     * 生成签名,5min后过期
     *
     * @param userName
     * @param password
     * @return 加密token
     */
    public static String sign(String userName, String password) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(password);
            return JWT.create().withClaim("userName", userName).withExpiresAt(date).sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error("加签失败");
            return null;
        }
    }

}
