package agodel.data;

import agodel.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, String> {
    public List<UserModel> findByUsername(String username);

}
