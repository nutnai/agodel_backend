package agodel.data;

import agodel.model.CustomerModel;
import agodel.model.ReceiptModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReceiptRepository extends JpaRepository<ReceiptModel, String> {

    public ReceiptModel findTopByOrderByReceiptIdDesc();

    public ReceiptModel findByReceiptId(String receiptId);

    public List<ReceiptModel> findByCustomerId(CustomerModel customerId);

}
