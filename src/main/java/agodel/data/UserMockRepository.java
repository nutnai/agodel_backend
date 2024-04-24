package agodel.data;

import agodel.model.UserMock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMockRepository extends JpaRepository<UserMock, String> {

}
