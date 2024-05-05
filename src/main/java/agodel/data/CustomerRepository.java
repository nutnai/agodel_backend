package agodel.data;

import agodel.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, String> {

    public CustomerModel findByCustomerId(String customerId);

}
