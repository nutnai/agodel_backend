package agodel.controller;

import java.util.List;
import java.util.Map;

import agodel.data.RoomRepository;
import agodel.model.Receipt;
import agodel.model.RoomModel;
import agodel.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import agodel.service.RoomService;
import agodel.service.ReceiptService;

@RestController
@Controller
@RequestMapping("/room")
public class RoomController{
    private RoomRepository roomRepository;
    private RoomService roomService;

    private ReceiptService receiptService;

    private CustomerService customerService;

    public RoomController(RoomRepository roomRepository, RoomService roomService, ReceiptService receiptService, CustomerService customerService){
        this.roomRepository = roomRepository;
        this.roomService = roomService;
        this.receiptService = receiptService;
        this.customerService = customerService;
    }

    @PostMapping("/create")
    public String create(@RequestBody Map<String, Object> body){
        return roomService.create(body);
    }

    @PostMapping("/detail")
    public RoomModel showDetail(@RequestBody Map<String, Object> body){
        return roomService.showDetail(body);
    }

    @PostMapping("/cancle")
    public String cancle(@RequestBody Map<String, Object> body){
        return receiptService.customerCancleRent(body);
    }

    @PostMapping("/paid")
    public String paid(@RequestBody Map<String, Object> body){
        return receiptService.paidRent(body);
    }

    @PostMapping("/notApprove")
    public String notApprove(@RequestBody Map<String, Object> body){
        return receiptService.notApprove(body);
    }

    @PostMapping("/approve")
    public String approveRent(@RequestBody Map<String, Object> body){
        return receiptService.approveRent(body);
    }

    @PostMapping("/rentDetail")
    public List<Receipt> rentDetail(@RequestBody Map<String, Object> body){
        return customerService.rentDetail(body);
    }

}