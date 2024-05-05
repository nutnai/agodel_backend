package agodel.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import agodel.exception.ResponseEntityException;

import org.springframework.http.HttpStatus;

@Component
public class AuthenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    // all roles
    public static class Role {
        public static final String ADMIN = "admin";
        public static final String CUSTOMER = "customer";
        public static final String OWNER = "owner";
        public static final String ALL = "all";
    }

    //authen function
    public static String authen(List<String> roles, Map<String, Object> header) throws ResponseEntityException {
        if (roles.contains(Role.ALL)) {
            return "1";
        }
        String token = (String) header.get("authorization");
        if (token == null) {
            throw new ResponseEntityException("token is require", HttpStatus.BAD_REQUEST);
        }
        //remove bearer
        token = token.substring(7);
        Map<String, Object> result = (Map<String, Object>) JwtUtil.validateToken(token);
        if (!(boolean) result.get("isValid")) {
            throw new ResponseEntityException("token is invalid", HttpStatus.UNAUTHORIZED);
        }
        String id = (String) result.get("id");
        boolean isAuthen = false;
        for (String role : roles) {
            if (id.equals("00000000")) {
                isAuthen = true;
                break;
            }
            if (role.equals(Role.CUSTOMER)) {
                if (id.startsWith("1")) {
                    isAuthen = true;
                    break;
                }
                continue;
            }
            if (role.equals(Role.OWNER)) {
                if (id.startsWith("2")) {
                    isAuthen = true;
                    break;
                }
                continue;
            }
            throw new ResponseEntityException("Role is invalid", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!isAuthen) {
            // throw new RuntimeException("permission denied");
            throw new ResponseEntityException("permission denied", HttpStatus.FORBIDDEN);
        }
        return id;
    }
}