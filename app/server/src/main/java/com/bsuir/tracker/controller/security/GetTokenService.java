package com.bsuir.tracker.controller.security;

import com.bsuir.tracker.Service.EmployeeService;
import com.bsuir.tracker.Service.RoleService;
import com.bsuir.tracker.entity.EmployeeEntity;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;

import java.util.*;

@Service
public class GetTokenService {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;

    private String SECRET_KEY = "secret";

    public String getToken(String username, String password) throws Exception {
        if (username == null || password == null)
            return null;
        EmployeeEntity user = employeeService.getEmployeeByMail(username);
        Map<String, Object> tokenData = new HashMap<>();
        if (password.equals(user.getPassword()))
        {
            try
            {
                tokenData.put("id", "" + user.getIdemployee());
                tokenData.put("role", roleService.getRole(user.getRoleIdrole()).getCode());
                tokenData.put("email", user.getEmail());
                tokenData.put("surname", user.getSurname());
                //Calendar calendar = Calendar.getInstance();
                //calendar.add(Calendar.YEAR, 1);
                    //tokenData.put("token_expiration_date", calendar.getTime());
                //JwtBuilder jwtBuilder = Jwts.builder();
                //jwtBuilder.setExpiration(calendar.getTime());
                //jwtBuilder.setClaims(tokenData);
                //String key = "secret";
                //String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
                String token = Jwts.builder()
                        .setHeaderParam("typ", "JWT")
                        .setClaims(tokenData)
                        //.setExpiration(calendar.getTime())
                        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                        .compact();
                return token;
            }
            catch (Exception e)
            {
                throw new Exception("Authentication error");
            }
        }
        else
        {
            return null;
        }
    }

    public String getEmailFromToken(String token)
    {
        Claims claims;
        claims = (Claims) Jwts.parser().setSigningKey(SECRET_KEY).parse(token).getBody();
        String email = claims.get("email", String.class);
        return email;
    }

    public int getIdFromToken(String token)
    {
        Claims claims;
        claims = (Claims) Jwts.parser().setSigningKey(SECRET_KEY).parse(token).getBody();
        String  string_id = claims.get("id", String .class);
        int id = Integer.parseInt(string_id);
        return id;
    }
}