package com.sadatmalik.optima.organisation.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * POJO class that contains all the specific data we want to store in the UserContextHolder.
 *
 * The UserContext class holds the HTTP header values for an individual service client
 * request that is processed by our microservice.
 *
 * Defining our variables as ThreadLocal lets us store data individually for the current thread.
 * The information set here can only be read by the thread that set the value.
 *
 * @author sadatmalik
 */
@Getter
@Setter
@Component
public class UserContext {
    public static final String CORRELATION_ID  = "tmx-correlation-id";
    public static final String AUTH_TOKEN      = "tmx-auth-token";
    public static final String USER_ID         = "tmx-user-id";
    public static final String ORGANISATION_ID = "tmx-organisation-id";

    private static final ThreadLocal<String> correlationId= new ThreadLocal<>();
    private static final ThreadLocal<String> authToken= new ThreadLocal<>();
    private static final ThreadLocal<String> userId = new ThreadLocal<>();
    private static final ThreadLocal<String> orgId = new ThreadLocal<>();

    public static String getCorrelationId() {
        return correlationId.get();
    }

    public static void setCorrelationId(String cid) {
        correlationId.set(cid);
    }

    public static String getAuthToken() {
        return authToken.get();
    }

    public static void setAuthToken(String aToken) {
        authToken.set(aToken);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void setUserId(String aUser) {
        userId.set(aUser);
    }

    public static String getOrgId() {
        return orgId.get();
    }

    public static void setOrgId(String aOrg) {
        orgId.set(aOrg);
    }

    public static HttpHeaders getHttpHeaders(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(CORRELATION_ID, getCorrelationId());

        return httpHeaders;
    }
}