package agodel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agodel.data.RoomRepository;
import agodel.model.Receipt;
import agodel.model.RoomModel;
import agodel.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> body){
        try {
            String result = roomService.create(body);
            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/detail")
    public ResponseEntity<Map<String, Object>> showDetail(@RequestBody Map<String, Object> body){
        try {
            List<RoomModel> room = roomService.showDetail(body);
            Map<String, Object> response = new HashMap<>();
            response.put("room", room);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/roomDetail")
    public ResponseEntity<Map<String, Object>> showRoomDetail(@RequestBody Map<String, Object> body){
        try {
            RoomModel room = roomService.showRoomDetail(body);
            Map<String, Object> response = new HashMap<>();
            response.put("room", room);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Map<String, Object> body){
        try {
            String room = roomService.edit(body);
            Map<String, Object> response = new HashMap<>();
            response.put("room", room);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/cancle")
    public ResponseEntity<Map<String, Object>> cancle(@RequestBody Map<String, Object> body){
        try {
            String result = receiptService.customerCancleRent(body);
            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/paid")
    public ResponseEntity<Map<String, Object>> paid(@RequestBody Map<String, Object> body){
        try {
            String result = receiptService.paidRent(body);
            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/notApprove")
    public ResponseEntity<Map<String, Object>> notApprove(@RequestBody Map<String, Object> body){
        try {
            String result = receiptService.notApprove(body);
            Map<String, Object> response = new HashMap<>();
            response.put("message", result);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/approve")
    public ResponseEntity<Map<String, Object>> approveRent(@RequestBody Map<String, Object> body){
       try {
              String result = receiptService.approveRent(body);
              Map<String, Object> response = new HashMap<>();
              response.put("message", result);
              return ResponseEntity.ok(response);
         }
         catch (Exception e){
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

       }
    }

    @PostMapping("/rentDetail")
    public ResponseEntity<Map<String, Object>> rentDetail(@RequestBody Map<String, Object> body){
        try {
            List<Receipt> receipts = customerService.rentDetail(body);
            Map<String, Object> response = new HashMap<>();
            response.put("receipts", receipts);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}