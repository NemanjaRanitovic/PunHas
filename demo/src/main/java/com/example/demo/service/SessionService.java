package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
@Service
public class SessionService {
    public boolean validateRole(HttpSession session, String roleName){
        Object role = session.getAttribute("role");
        if (role == null) return false;
        if (!role.toString().endsWith(roleName)) return false;
        return true;
    }

    public String getUsername(HttpSession session){
        Object username = session.getAttribute("username");
        if (username == null) return "";
        if (username.toString().isEmpty()) return "";
        return username.toString();
    } // Client currentClient = clientRepository.findClientByUsername(sessionService.getUsername(session));

    public String getRole(HttpSession session){
        Object role = session.getAttribute("role");
        if (role == null) return "";
        if (role.toString().isEmpty()) return "";
        return role.toString();
    }

    public boolean validateSession(HttpSession session){
        if (session == null) {
            return false;
        }

        String username = getUsername(session);
        String role = getRole(session);

        if (username == null || username.isEmpty())
            return false;
        if (role == null || role.isEmpty())
            return false;
        return true;
    }
}
