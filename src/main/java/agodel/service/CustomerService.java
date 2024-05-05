package agodel.service;

import java.util.Map;
import java.util.HashMap;

import agodel.DTO.UserDTO.*;
import agodel.data.CustomerRepository;
import agodel.exception.ResponseEntityException;
import agodel.model.CustomerModel;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
@Transactional
public class CustomerService {
    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void register(RegisterDTO dto, String id) throws ResponseEntityException {
        try {
            CustomerModel customerModel = new CustomerModel();
            customerModel.setCustomerId(id);
            customerModel.setUsername(dto.getUsername());
            customerModel.setFirstname(dto.getFirstname());
            customerModel.setLastname(dto.getLastname());
            customerModel.setPhone(dto.getPhone());
            customerModel.setEmail(dto.getEmail());
            entityManager.persist(customerModel);
        } catch (Exception e) {
            throw new ResponseEntityException("can't create customer: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> showDetail(GetCustomerDTO getCustomerDTO) throws ResponseEntityException {
        try {
            CustomerModel customerModel = customerRepository.findByCustomerId(getCustomerDTO.getCustomerId());
            Map<String, Object> response = new HashMap<>();
            response.put("customer", customerModel);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("Customer not found", HttpStatus.NOT_FOUND);
        }
    }

    public Map<String, Object> edit(EditCustomerDTO editCustomerDTO) throws ResponseEntityException {
        CustomerModel customerModel;
        try {
            customerModel = customerRepository.findByCustomerId(editCustomerDTO.getCustomerId());
        } catch (Exception e) {
            throw new ResponseEntityException("Customer not found", HttpStatus.NOT_FOUND);
        }
        try {
            if (editCustomerDTO.getNewFirstname() != null) {
                customerModel.setFirstname(editCustomerDTO.getNewFirstname());
            }
            if (editCustomerDTO.getNewLastname() != null) {
                customerModel.setLastname(editCustomerDTO.getNewLastname());
            }
            if (editCustomerDTO.getNewPhone() != null) {
                customerModel.setPhone(editCustomerDTO.getNewPhone());
            }
            if (editCustomerDTO.getNewEmail() != null) {
                customerModel.setEmail(editCustomerDTO.getNewEmail());
            }
            entityManager.merge(customerModel);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "edit success!");
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("can't edit customer: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // public List<Receipt> rentDetail(Map<String, Object> body) {
    //     CustomerModel customerModel = customerRepository.findByCustomerId((String) body.get("customerId"));
    //     return receiptService.showDetail(body, customerModel);
    // }

}
