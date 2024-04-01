package com.example.demooracle11.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {
    public static String encode(String password) {
        try {
            // Tạo một đối tượng MessageDigest với thuật toán SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Mã hóa mật khẩu thành chuỗi bytes
            byte[] encodedHash = digest.digest(password.getBytes());

            // Chuyển đổi bytes thành dạng hex (hexadecimal) để tạo chuỗi mã hóa
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Xử lý nếu thuật toán không được hỗ trợ
            e.printStackTrace();
            return null;
        }
    }
}
