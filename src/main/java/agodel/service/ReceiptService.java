package agodel.service;

import agodel.data.CustomerRepository;
import agodel.data.RoomRepository;
import agodel.model.CustomerModel;
import agodel.model.Receipt;
import org.springframework.stereotype.Service;
import agodel.data.ReceiptRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ReceiptService {
    private ReceiptRepository receiptRepository;

    private RoomRepository roomRepository;

    private CustomerRepository customerRepository;


    @PersistenceContext
    private EntityManager entityManager;

    public ReceiptService(ReceiptRepository receiptRepository, RoomRepository roomRepository, CustomerRepository customerRepository){
        this.receiptRepository = receiptRepository;
        this.roomRepository = roomRepository;
        this.customerRepository = customerRepository;

    }

    public Receipt create(Map<String, Object> body,int price, String roomId, String customerId){
        Receipt receipt = new Receipt();
        Receipt lastRec = receiptRepository.findTopByOrderByReceiptIdDesc();
        String currentId = String.valueOf(Integer.parseInt(lastRec.getReceiptId())+1);
        receipt.setReceiptId(currentId);
        receipt.setDateCreate((String) body.get("dateCreate"));
        receipt.setCustomerId(customerRepository.getReferenceById(customerId));
        receipt.setPrice((Integer) price);
        receipt.setDateCheckIn((String) body.get("dateCheckIn"));
        receipt.setDateCheckOut((String) body.get("dateCheckOut"));
        receipt.setRoomModelId(roomRepository.getReferenceById(roomId));
        receipt.setStatus("Not Paid!");
        receiptRepository.save(receipt);
        return receipt;
    }

    public String customerCancleRent(Map<String, Object> body){
        Receipt receipt = receiptRepository.findByReceiptId((String) body.get("receiptId"));
        receipt.setStatus("Cancle!!");
        entityManager.merge(receipt);
        return "Cancled!!";
    }

    public String paidRent(Map<String, Object> body){
        Receipt receipt = receiptRepository.findByReceiptId((String) body.get("receiptId"));
        receipt.setStatus("Paid");
        receipt.setDatePay((String) body.get("datePay"));
        entityManager.merge(receipt);
        return "Not approve";
    }

    public String notApprove(Map<String, Object> body){
        Receipt receipt = receiptRepository.findByReceiptId((String) body.get("receiptId"));
        receipt.setStatus("Chargeback");
        entityManager.merge(receipt);
        return "Chargeback";
    }

    public String approveRent(Map<String, Object> body){
        Receipt receipt = receiptRepository.findByReceiptId((String) body.get("receiptId"));
        receipt.setStatus("Rented");
        entityManager.merge(receipt);
        return "Rented";
    }

    public List<Receipt> showDetail(Map<String, Object> body, CustomerModel customerModel){
        return receiptRepository.findByCustomerId(customerModel);
    }
}
