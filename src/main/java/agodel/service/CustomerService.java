package agodel.service;

import java.util.List;
import java.util.Map;

import agodel.data.CustomerRepository;
import agodel.model.CustomerModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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

    public List<CustomerModel> getUser() {
        return customerRepository.findAll();
    }

}
