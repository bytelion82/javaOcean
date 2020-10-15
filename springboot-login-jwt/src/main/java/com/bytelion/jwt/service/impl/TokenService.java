package com.bytelion.jwt.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bytelion.jwt.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author de'l'l
 */
@Service("TokenService")
public class TokenService {
    public String getToken(User user) {
        String token="";
        // 将 user id 保存到 token 里面
        token= JWT.create().withAudience(user.getId())
                // 以 password 作为 token 的密钥
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
