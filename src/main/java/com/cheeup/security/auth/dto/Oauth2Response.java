package com.cheeup.security.auth.dto;

/**
 * Oauth 결과 사용자 정보를 가져오는 인터페이스
 */
public interface Oauth2Response {

    String getProvider();

    String getProviderId();

    String getEmail();

    String getName();

}
