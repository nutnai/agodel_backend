package agodel.controller;

import java.util.List;
import java.util.Map;

import agodel.service.RoomService;
import agodel.util.AuthenUtil;
import agodel.util.AuthenUtil.Role;
import agodel.DTO.RoomDTO.*;
import agodel.DTO.UserDTO.GetOwnerDTO;
import agodel.exception.ResponseEntityException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/room")
public class RoomController {
    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.OWNER_ID), header, body);
            CreateDTO createDTO = new CreateDTO(body);
            return ResponseEntity.ok(roomService.create(createDTO));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/getPlaceRoom")
    public ResponseEntity<Map<String, Object>> showDetail(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.ALL), header, body);
            GetOwnerDTO getOwnerDTO = new GetOwnerDTO(body);
            return ResponseEntity.ok(roomService.showDetail(getOwnerDTO));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/getRoom")
    public ResponseEntity<Map<String, Object>> showRoomDetail(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.ALL), header, body);
            GetRoomDTO getRoomDTO = new GetRoomDTO(body);
            return ResponseEntity.ok(roomService.showRoomDetail(getRoomDTO));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<Map<String, Object>> edit(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            //check authen in roomService
            String id = AuthenUtil.authen(List.of(Role.OWNER), header, body);
            EditDTO editDTO = new EditDTO(body);
            return ResponseEntity.ok(roomService.edit(editDTO, id));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    //! wtf is this
    // @PostMapping("/rentDetail")
    // public ResponseEntity<Map<String, Object>> rentDetail(@RequestBody Map<String, Object> body) {
    //     try {
    //         List<Receipt> receipts = customerService.rentDetail(body);
    //         Map<String, Object> response = new HashMap<>();
    //         response.put("receipts", receipts);
    //         return ResponseEntity.ok(response);
    //     } catch (Exception e) {
    //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    //     }
    // }

}