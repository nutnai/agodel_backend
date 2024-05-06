package agodel.service;

import agodel.DTO.PlaceDTO.ReserveDTO;
import agodel.DTO.ReceiptDTO.*;
import agodel.data.CustomerRepository;
import agodel.data.RoomRepository;
import agodel.model.CustomerModel;
import agodel.model.ReceiptModel;
import agodel.model.RoomModel;
import agodel.data.ReceiptRepository;
import agodel.exception.ResponseEntityException;
import agodel.util.ValidateType;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Map;

@Service
@Transactional
public class ReceiptService {
    private ReceiptRepository receiptRepository;

    private RoomRepository roomRepository;

    private CustomerRepository customerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ReceiptService(ReceiptRepository receiptRepository, RoomRepository roomRepository,
            CustomerRepository customerRepository) {
        this.receiptRepository = receiptRepository;
        this.roomRepository = roomRepository;
        this.customerRepository = customerRepository;

    }

    public Map<String, Object> create(ReserveDTO reserveDTO, Map<String, Object> payload)
            throws ResponseEntityException {
        try {
            ReceiptModel receipt = new ReceiptModel();
            ReceiptModel lastRec = receiptRepository.findTopByOrderByReceiptIdDesc();
            String currentId = "1";
            if (lastRec != null) {
                currentId = String.valueOf(Integer.parseInt(lastRec.getReceiptId()) + 1);
            }
            RoomModel room = roomRepository.findByRoomId(reserveDTO.getRoomId());
            if (room == null) {
                throw new ResponseEntityException("Room not found", HttpStatus.NOT_FOUND);
            }
            CustomerModel customer = customerRepository.getReferenceById(payload.get("customerId").toString());
            if (customer == null) {
                throw new ResponseEntityException("Customer not found", HttpStatus.NOT_FOUND);
            }
            receipt.setReceiptId(currentId);
            receipt.setDateCreate(LocalDate.now());
            receipt.setCustomerId(customer);
            receipt.setPrice(ValidateType.validateDouble(payload, "price"));
            receipt.setDateCheckIn(reserveDTO.getDateCheckIn());
            receipt.setDateCheckOut(reserveDTO.getDateCheckOut());
            receipt.setRoomModelId(room);
            receipt.setStatus("CREATED");
            receiptRepository.save(receipt);
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("receipt", receipt);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("can't create receipt: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> approveReserve(GetReceiptDTO getReceiptDTO, String id) throws ResponseEntityException {
        ReceiptModel receipt = receiptRepository.findByReceiptId(getReceiptDTO.getReceiptId());
        if (receipt == null) {
            throw new ResponseEntityException("Receipt not found", HttpStatus.NOT_FOUND);
        }
        if (id.charAt(0) != '0' && !receipt.getRoomModelId().getOwner().getOwnerId().equals(id)) {
            throw new ResponseEntityException("You are not owner of this room", HttpStatus.FORBIDDEN);
        }
        if (!receipt.equalsStatus("CREATED")) {
            throw new ResponseEntityException("Receipt can't approve, it's in status " + receipt.getStatus(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            receipt.setStatus("APPROVED");
            entityManager.merge(receipt);
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("receipt", receipt);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("can't approve receipt: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> cancleReserve(GetReceiptDTO getReceiptDTO, String id) throws ResponseEntityException {
        ReceiptModel receipt = receiptRepository.findByReceiptId(getReceiptDTO.getReceiptId());
        if (receipt == null) {
            throw new ResponseEntityException("Receipt not found", HttpStatus.NOT_FOUND);
        }
        if (id.charAt(0) != '0') {
            if (id.charAt(0) == '1') {
                if (!receipt.getCustomerId().getCustomerId().equals(id)) {
                    throw new ResponseEntityException("You are not customer of this receipt", HttpStatus.FORBIDDEN);
                }
            } else {
                if (!receipt.getRoomModelId().getOwner().getOwnerId().equals(id)) {
                    throw new ResponseEntityException("You are not owner of this room", HttpStatus.FORBIDDEN);
                }
            }
        }
        if (receipt.equalsStatus("CANCLED") || receipt.equalsStatus("COMPLETED")) {
            throw new ResponseEntityException("Receipt can't cancle, it's in status " + receipt.getStatus(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            receipt.setStatus("CANCLED");
            entityManager.merge(receipt);
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("receipt", receipt);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("can't cancle receipt: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> paidReserve(GetReceiptDTO getReceiptDTO, String id) throws ResponseEntityException {
        ReceiptModel receiptModel = receiptRepository.findByReceiptId(getReceiptDTO.getReceiptId());
        if (receiptModel == null) {
            throw new ResponseEntityException("Receipt not found", HttpStatus.NOT_FOUND);
        }
        if (id.charAt(0) != '0' && !receiptModel.getCustomerId().getCustomerId().equals(id)) {
            throw new ResponseEntityException("You are not customer of this receipt", HttpStatus.FORBIDDEN);
        }
        if (!receiptModel.equalsStatus("APPROVED")) {
            throw new ResponseEntityException("Receipt can't paid, it's in status " + receiptModel.getStatus(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            receiptModel.setStatus("PAID");
            entityManager.merge(receiptModel);
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("receipt", receiptModel);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("can't paid receipt: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Map<String, Object> notApproveReserve(GetReceiptDTO getReceiptDTO, String id) throws ResponseEntityException {
        ReceiptModel receiptModel = receiptRepository.findByReceiptId(getReceiptDTO.getReceiptId());
        if (receiptModel == null) {
            throw new ResponseEntityException("Receipt not found", HttpStatus.NOT_FOUND);
        }
        if (id.charAt(0) != '0' && !receiptModel.getRoomModelId().getOwner().getOwnerId().equals(id)) {
            throw new ResponseEntityException("You are not owner of this room", HttpStatus.FORBIDDEN);
        }
        if (!receiptModel.equalsStatus("CREATED")) {
            throw new ResponseEntityException("Receipt can't not approve, it's in status " + receiptModel.getStatus(),
                    HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            receiptModel.setStatus("CANCLED");
            entityManager.merge(receiptModel);
            Map<String, Object> response = new java.util.HashMap<>();
            response.put("receipt", receiptModel);
            return response;
        } catch (Exception e) {
            throw new ResponseEntityException("can't not approve receipt: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // public List<Receipt> showDetail(Map<String, Object> body, CustomerModel customerModel) {
    //     return receiptRepository.findByCustomerId(customerModel);
    // }
}
