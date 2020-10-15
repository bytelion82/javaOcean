package com.bytelion.jwt.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bytelion.jwt.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt 工具类
 * 加密、解密
 */
@Slf4j
public class JwtUtil {

    /**
     * 密钥
     */
    private static final String SECRET = "my_secret";

    /**
     * 过期时间//单位为秒
     **/
    private static final long EXPIRATION = 1000L;

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(User user) {
        //过期时间
        Date expireDate = new Date(System.currentTimeMillis() + EXPIRATION * 1000);
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = JWT.create()
                // 添加头部
                .withHeader(map)
                //可以将基本信息放到claims中 userId
                .withClaim("id", user.getId())
                //userName
                .withClaim("userName", user.getUsername())
                //超时设置,设置过期的日期
                .withExpiresAt(expireDate)
                //签发时间
                .withIssuedAt(new Date())
                //SECRET加密
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 校验token并解析token
     */
    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        if (null==token){
            throw new RuntimeException("无token参数值");

        }
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("token解码异常");
            //解码异常则抛出异常
            throw new RuntimeException("token解码异常:"+e.getMessage());

        }
        System.out.println(jwt.getClaims().toString() );
        return jwt.getClaims();
    }

}
