package agodel.util;

import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;

import agodel.exception.ResponseEntityException;

import java.io.Serializable;
import java.util.Map;

@Component
public class ValidateType implements Serializable {
    private static final long serialVersionUID = -2550185165626007488L;

    public static String validateString(Map<String, Object> body, String key) throws ResponseEntityException {
        if (body == null) {
            return null;
        }
        if (body.get(key) == null) {
            return null;
        }
        if (!(body.get(key) instanceof String)) {
            throw new ResponseEntityException(key + " must be string", HttpStatus.BAD_REQUEST);
        }
        return (String) body.get(key);
    }

    public static Integer validateInteger(Map<String, Object> body, String key) throws ResponseEntityException {
        if (body == null) {
            return null;
        }
        if (body.get(key) == null) {
            return null;
        }
        if (!(body.get(key) instanceof Integer)) {
            throw new ResponseEntityException(key + " must be integer", HttpStatus.BAD_REQUEST);
        }
        return (Integer) body.get(key);
    }

    public static Double validateDouble(Map<String, Object> body, String key) throws ResponseEntityException {
        if (body == null) {
            return null;
        }
        if (body.get(key) == null) {
            return null;
        }
        try {
            return Double.parseDouble(body.get(key).toString());
        } catch (Exception e) {
            throw new ResponseEntityException(key + " must be number", HttpStatus.BAD_REQUEST);
        }
    }
}
