package agodel.service;

import org.aspectj.weaver.ast.Test;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import agodel.util.JwtUtil;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TestService {

    private JwtUtil jwtUtil;

    public TestService(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public String get(Map<String, Object> body) {
        String id = (String) body.get("id");
        return jwtUtil.generateToken(id);
    }

    public Object validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}
