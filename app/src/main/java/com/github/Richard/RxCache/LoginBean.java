package com.github.Richard.RxCache;

/**
 * @Author Charlie
 * @Date 2022/7/27 18:13
 */
public class LoginBean {

    private String userId;
    private String accesstoken;
    private long expiresIn;

    public String getUserId() {
        return userId;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
