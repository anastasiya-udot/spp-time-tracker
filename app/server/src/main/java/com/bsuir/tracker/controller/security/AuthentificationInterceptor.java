package com.bsuir.tracker.controller.security;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bsuir.tracker.Service.EmployeeService;
import com.bsuir.tracker.Service.RoleService;
import com.bsuir.tracker.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

public class AuthentificationInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GetTokenService getTokenService;

    private  String ADMINISTRATOR_ROLE_IDENTIFIER = "26";
    private  String DIRECTOR_ROLE_IDENTIFIER = "31";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = (String) request.getSession().getAttribute("token");

        Claims claims;

        if(token != null) {
            try {
                String email = getTokenService.getEmailFromToken(token);

                EmployeeEntity employeeEntity =  employeeService.getEmployeeByMail(email);
                if((employeeEntity == null))
                {
                    response.sendRedirect("/noPermission");
                    return false;
                }
                String tokenToCompare = getTokenService.getToken(employeeEntity.getEmail(), employeeEntity.getPassword());
                if(tokenToCompare.equals(token))
                {
                    String role = roleService.getRole(employeeEntity.getRoleIdrole()).getCode();
                    if(role.equals(ADMINISTRATOR_ROLE_IDENTIFIER)/* || (role.equals(DIRECTOR_ROLE_IDENTIFIER))*/)
                    {
                        return true;
                    }
                    else
                    {
                        response.sendRedirect("/noPermission");
                        return false;
                    }
                }
                else
                {
                    response.sendRedirect("/Authorization");
                    return false;
                }
            }
            catch (Exception ex)
            {
                throw new Exception("Token corrupted");
            }
        }
        else
        {
            response.sendRedirect("/Authorization");
            return false;
        }
    }
}