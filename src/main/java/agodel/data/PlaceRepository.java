package agodel.data;

import agodel.model.PlaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceModel, String> {
    public PlaceModel findTopByOrderByPlaceIdDesc();

}
