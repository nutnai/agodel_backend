package agodel.data;

import agodel.model.PlaceModel;
import agodel.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, String> {

    public Receipt findTopByOrderByReceiptIdDesc();

}
