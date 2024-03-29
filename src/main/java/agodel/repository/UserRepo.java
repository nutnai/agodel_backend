package agodel.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import agodel.model.UserModel;

public interface UserRepo extends CrudRepository<UserModel, Integer> {
    
}

