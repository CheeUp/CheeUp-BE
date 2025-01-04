package com.cheeup.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class CookieUtil {


    public Optional<String> extractCookieValue(HttpServletRequest request, String key) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(key))
                .findFirst()
                .map(Cookie::getValue);
    }


    public Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
//        cookie.setMaxAge(3600);
//        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    public Cookie createCookie(String key, String value, int ttl) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(ttl);
//        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

}
