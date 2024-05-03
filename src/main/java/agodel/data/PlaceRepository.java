package agodel.data;

import agodel.model.OwnerModel;
import agodel.model.PlaceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<PlaceModel, String> {
    public PlaceModel findTopByOrderByPlaceIdDesc();

    public List<PlaceModel> findByAddressContains(String temp);

    public PlaceModel findByPlaceId(String placeId);

    @Query("SELECT placeModel FROM PlaceModel placeModel JOIN RoomModel roomModel ON placeModel = roomModel.place WHERE placeModel.address LIKE %:searchString% AND roomModel.numberPeople > :yourNumber AND roomModel.price BETWEEN :lowerPrice AND :upperPrice")
    List<PlaceModel> findPlaceBySearchCriteria(@Param("searchString") String searchString, @Param("yourNumber") int yourNumber, @Param("lowerPrice") int lowerPrice, @Param("upperPrice") int upperPrice);

    public PlaceModel findByOwnerOwnerId(OwnerModel owner);
}
