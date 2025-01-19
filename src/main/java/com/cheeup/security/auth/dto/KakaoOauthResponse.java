package com.cheeup.security.auth.dto;

import lombok.ToString;

import java.util.Map;

@ToString
public class KakaoOauthResponse implements Oauth2Response {

    private final Map<String, Object> attribute;

    public KakaoOauthResponse(Map<String, Object> attribute) {
        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("id").toString();
    }

    @Override
    public String getEmail() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
        Object email = kakaoAccount.get("email");
        if (email == null) {
            throw new RuntimeException("email not found"); //oauth에서 이메일을 제공해주지 않음
        }
        return (String) email;
    }

    @Override
    public String getName() {
        Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
        Object name = kakaoAccount.get("name");
        if (name == null) {
            throw new RuntimeException("name not found");
        }
        return (String) name;
    }
}
