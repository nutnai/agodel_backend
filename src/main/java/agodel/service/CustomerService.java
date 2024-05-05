package agodel.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import agodel.DTO.UserDTO.*;
import agodel.data.CustomerRepository;
import agodel.exception.ResponseEntityException;
import agodel.model.CustomerModel;
import agodel.model.Receipt;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    public void register(RegisterDTO dto, String id) throws ResponseEntityException{
        try {
            CustomerModel customerModel = new CustomerModel();
            customerModel.setCustomerId(id);
            customerModel.setFirstname(dto.getFirstname());
            customerModel.setLastname(dto.getLastname());
            customerModel.setPhone(dto.getPhone());
            customerModel.setEmail(dto.getEmail());
            entityManager.persist(customerModel);
        } catch (Exception e){
            throw new ResponseEntityException("can't create customer: "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> showDetail(GetCustomerDTO getCustomerDTO) throws ResponseEntityException{
        try {
            CustomerModel customerModel = customerRepository.findByCustomerId(getCustomerDTO.getCustomerId());
            Map<String, Object> response = new HashMap<>();
            response.put("customer", customerModel);
            return response;
        } catch (Exception e){
            throw new ResponseEntityException("Customer not found", HttpStatus.NOT_FOUND);
        }
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
