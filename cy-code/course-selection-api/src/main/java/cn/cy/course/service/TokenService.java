package cn.cy.course.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author eddieVim
 * @微信公众号 埃迪的Code日记 / PositiveEddie
 * @blog https://blog.csdn.net/weixin_44129784
 * @create 2020/10/8 2:16 下午
 */
@Service
public class TokenService {

    @Autowired
    private RedisTemplate redisTemplate;

    public final String TOKEN_HEADER = "Authorization";
    public final String TOKEN_PREFIX = "Bearer ";

    private final String SECRET = "jwtsecretdemo";
    private final String ISS = "echisan";

    // 过期时间是3600秒，既是1个小时
    private final long EXPIRATION = 3600L;

    // 选择了记住我之后的过期时间为7天
    private final long EXPIRATION_REMEMBER = 604800L;

    private final String ROLE_CLAIMS = "rol";

    public String createToken(String username, String role, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;

        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, role);

        redisTemplate.opsForValue().set(username, username, expiration, TimeUnit.SECONDS);


        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET)
                // 这里要早set一点，放到后面会覆盖别的字段
                .setClaims(map)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    /**
     * 使得Redis中的登录信息失效
     *
     * @param token
     */
    public void delToken(String token) {
        String username = getUsername(token);
        redisTemplate.delete(username);
    }

    // 从token中获取用户名
    public String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    // 从token中获取角色
    public String getUserRole(String token) {
        return (String) getTokenBody(token).get(ROLE_CLAIMS);
    }

    // token是否已过期
    public boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    // 是否已经登录，通过删除Redis数据可以起到强制下线的效果
    public boolean isLogin(String token) {
        String username = getUsername(token);
        String val = (String) redisTemplate.opsForValue().get(username);
        return val != null && val.equals(username);
    }

    // 得到token体
    private Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
