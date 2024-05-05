package agodel.util;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import agodel.exception.ResponseEntityException;
import agodel.DTO.UserDTO.GetCustomerDTO;
import agodel.DTO.UserDTO.GetOwnerDTO;
import agodel.util.ValidateType;

import org.springframework.http.HttpStatus;

@Component
public class AuthenUtil implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    // all roles
    public static class Role {
        public static final String ADMIN = "admin";
        public static final String CUSTOMER = "customer";
        public static final String CUSTOMER_ID = "customer_id";
        public static final String OWNER = "owner";
        public static final String OWNER_ID = "owner_id";
        public static final String ALL = "all";
    }

    //authen function
    public static String authen(List<String> roles, Map<String, Object> header) throws ResponseEntityException {
        return authen(roles, header, null);
    }

    public static String authen(List<String> roles, Map<String, Object> header, Map<String, Object> body) throws ResponseEntityException {
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
        String customerId = ValidateType.validateString(body, "customerId");
        String ownerId = ValidateType.validateString(body, "ownerId");
        String userId = (String) result.get("id");
        boolean isAuthen = false;
        for (String role : roles) {
            if (userId.equals("00000000")) {
                isAuthen = true;
                break;
            }
            if (role.equals(Role.CUSTOMER)) {
                if (userId.startsWith("1")) {
                    isAuthen = true;
                    break;
                }
                continue;
            }
            if (role.equals(Role.CUSTOMER_ID)) {
                if (userId.equals(customerId)) {
                    isAuthen = true;
                    break;
                }
                continue;
            }
            if (role.equals(Role.OWNER)) {
                if (userId.startsWith("2")) {
                    isAuthen = true;
                    break;
                }
                continue;
            }
            if (role.equals(Role.OWNER_ID)) {
                if (userId.equals(ownerId)) {
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
        return userId;
    }
}