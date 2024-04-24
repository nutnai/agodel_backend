package com.agodel.agodel.service;

import java.util.List;

import com.agodel.agodel.database.UserMockRepository;
import com.agodel.agodel.model.UserMock;
import org.springframework.stereotype.Service;

@Service
public class UserMockService {
    private UserMockRepository userMockRepository;

    public UserMockService(UserMockRepository userMockRepository) {
        this.userMockRepository = userMockRepository;
    }

    public List<UserMock> getUserMock() {
        return userMockRepository.findAll();
    }
}
