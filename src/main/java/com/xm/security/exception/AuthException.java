package com.xm.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author xiaoming
 * @date 2023-12-18 21:29
 **/
public class AuthException extends AuthenticationException {

    public AuthException(String message) {
        super(message);
    }
}
