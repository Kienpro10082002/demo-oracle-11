package com.example.demooracle11.utils;

import com.example.demooracle11.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    public void logRequestInfo(HttpServletRequest request, User user) {
        String url = request.getRequestURI();
        String remoteUser = request.getRemoteUser(); // Đây là user được xác thực, nếu có
        String passwordHash = user != null ? hashPassword(user.getPassword()) : null; // Lấy password từ user và hash nó
        // Log thông tin ra console hoặc file log
        System.out.println("URL: " + url);
        System.out.println("User: " + remoteUser);
        System.out.println("Hashed Password: " + passwordHash);
    }

    public void logUpdateInfo(HttpServletRequest request, User user) {
        String url = request.getRequestURI();
        String remoteUser = request.getRemoteUser(); // Đây là user được xác thực, nếu có
        String password = user != null ? user.getPassword() : null; // Lấy password từ user
        String hashedPassword = hashPassword(url + password); // Kết hợp URL và password rồi hash
        // Log thông tin ra console hoặc file log
        System.out.println("URL: " + url);
        System.out.println("User: " + remoteUser);
        System.out.println("Hashed Password with URL: " + hashedPassword);
    }

    public void logDeleteInfo(HttpServletRequest request, Long userId) {
        String url = request.getRequestURI();
        String remoteUser = request.getRemoteUser(); // Đây là user được xác thực, nếu có
        // Log thông tin ra console hoặc file log
        System.out.println("URL: " + url);
        System.out.println("User: " + remoteUser);
        System.out.println("Deleted User ID: " + userId);
    }

    public void logReadInfo(HttpServletRequest request, Long userId) {
        String url = request.getRequestURI();
        String remoteUser = request.getRemoteUser(); // Đây là user được xác thực, nếu có
        // Log thông tin ra console hoặc file log
        System.out.println("URL: " + url);
        System.out.println("User: " + remoteUser);
        System.out.println("Read User ID: " + userId);
    }

    private String hashPassword(String password) {
        password = PasswordEncoder.encode(password);
        return password;
    }
}
