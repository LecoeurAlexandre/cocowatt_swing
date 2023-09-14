package org.example.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class TokenStorage {
    private static String token;

    public TokenStorage() {
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        TokenStorage.token = token;
    }
}
