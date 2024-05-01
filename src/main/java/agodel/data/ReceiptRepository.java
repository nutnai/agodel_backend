package agodel.data;

import agodel.model.CustomerModel;
import agodel.model.PlaceModel;
import agodel.model.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, String> {

    public Receipt findTopByOrderByReceiptIdDesc();

    public Receipt findByReceiptId(String receiptId);

    public List<Receipt> findByCustomerId(CustomerModel customerId);

}
