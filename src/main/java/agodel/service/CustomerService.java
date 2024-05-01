package agodel.service;

import java.util.List;
import java.util.Map;

import agodel.data.CustomerRepository;
import agodel.model.CustomerModel;
import agodel.model.PlaceModel;
import agodel.service.ReceiptService;

import agodel.model.Receipt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class CustomerService {
    private CustomerRepository customerRepository;

    private ReceiptService receiptService;

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerService(CustomerRepository customerRepository, ReceiptService receiptService) {
        this.customerRepository = customerRepository;
        this.receiptService = receiptService;
    }

    public String register(Map<String, Object> body,String id){
        CustomerModel customers = new CustomerModel();
        customers.setCustomerId(id);
        customers.setFirstname((String) body.get("firstname"));
        customers.setLastname((String) body.get("lastname"));
        customers.setPhone((String) body.get("phone"));
        customers.setEmail((String) body.get("email"));
        customerRepository.save(customers);
        return "good";
    }

    public CustomerModel showDetail(Map<String, Object> body){
        return customerRepository.findByCustomerId((String) body.get("customerId"));
    }

    public String edit(Map<String, Object> body){
        try{
            CustomerModel customerModel = customerRepository.findByCustomerId((String) body.get("customerId"));
            customerModel.setFirstname((String) body.get("newFirstName"));
            customerModel.setLastname((String) body.get("newLastName"));
            customerModel.setPhone((String) body.get("newPhone"));
            customerModel.setEmail((String) body.get("newEmail"));
            entityManager.merge(customerModel);
            return "edit success!";
        } catch (Exception e){
            return "error!!!";
        }
    }

    public List<Receipt> rentDetail(Map<String, Object> body){
        CustomerModel customerModel = customerRepository.findByCustomerId((String) body.get("customerId"));
        return receiptService.showDetail(body,customerModel);
    }

}
