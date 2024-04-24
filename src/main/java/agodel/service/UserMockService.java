package agodel.service;

import java.util.List;

import agodel.data.UserMockRepository;
import agodel.model.UserMock;
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
