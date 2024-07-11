package com.shopping.ecomart.config;

import com.shopping.ecomart.entity.MyUser;
import com.shopping.ecomart.entity.Role;
import com.shopping.ecomart.util.ApplicationConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class JwtService {

    //private static final String SECRET = "638CBE3A90E0303BF3808F40F95A7F02A24B4B5D029C954CF553F79E9EF1DC0384BE681C249F1223F6B55AA21DC070914834CA22C8DD98E14A872CA010091ACC";
   // private static final long VALIDITY = TimeUnit.MINUTES.toMillis(ApplicationConstant.JWT_TOKEN_EXPIRY_TIME);

    public String generateToken(MyUser user) {
        UserDetails userDetails=getUserDetails(user);
        Map<String, String> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusMillis(ApplicationConstant.JWT_VALIDITY)))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {
        byte[] decodedKey = Base64.getDecoder().decode(ApplicationConstant.JWT_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUsername(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(ApplicationConstant.JWT_KEY)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            System.out.println("Could not get all claims Token from passed token");
            claims = null;
        }
        return claims;
    }

    public boolean isTokenExpired(String jwt) {
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
    public Date getExpDate(String jwt){
        return getClaims(jwt).getExpiration();
    }
    public boolean isTokenValid(String jwt,String username) {
        String jwtUsername = extractUsername(jwt);
        return username.equals(jwtUsername) && !isTokenExpired(jwt);
    }
    private UserDetails getUserDetails(MyUser user) {
        Optional<Role> first = user.getRoles().stream().findFirst();
        List<GrantedAuthority> authority=new ArrayList<>();
        first.ifPresent(role -> {
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role.getRoleName());
            authority.add(simpleGrantedAuthority);
        });
        return new User(user.getUserName(), user.getPassword(),authority);
    }

}