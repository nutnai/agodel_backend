package agodel.service;

import agodel.data.CustomerRepository;
import agodel.data.RoomRepository;
import agodel.model.Receipt;
import org.springframework.stereotype.Service;
import agodel.data.ReceiptRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
public class ReceiptService {
    private ReceiptRepository receiptRepository;

    private RoomRepository roomRepository;

    private CustomerRepository customerRepository;


    public ReceiptService(ReceiptRepository receiptRepository, RoomRepository roomRepository, CustomerRepository customerRepository) {
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

}
