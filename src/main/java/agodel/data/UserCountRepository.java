package agodel.data;

import agodel.model.UserCountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCountRepository extends JpaRepository<UserCountModel, String> {

}
