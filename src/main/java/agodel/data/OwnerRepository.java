package agodel.data;

import agodel.model.OwnerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<OwnerModel, String> {

    public OwnerModel findByOwnerId(String ownerId);

}
