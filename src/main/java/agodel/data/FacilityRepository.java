package agodel.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import agodel.model.FacilityModel;

@Repository
public interface FacilityRepository extends JpaRepository<FacilityModel, String> {

}
