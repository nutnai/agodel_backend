package agodel.controller;

import java.util.Map;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agodel.DTO.ReceiptDTO.*;
import agodel.service.ReceiptService;
import agodel.util.AuthenUtil;
import agodel.util.AuthenUtil.Role;
import agodel.exception.ResponseEntityException;

@RestController
@Controller
@RequestMapping("/receipt")
public class ReceiptController {
    ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/approve")
    public ResponseEntity<Map<String, Object>> approveRent(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            String id = AuthenUtil.authen(List.of(Role.OWNER), header, body);
            GetReceiptDTO getReceiptDTO = new GetReceiptDTO(body);
            return ResponseEntity.ok(receiptService.approveReserve(getReceiptDTO, id));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/cancle")
    public ResponseEntity<Map<String, Object>> cancle(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            String id = AuthenUtil.authen(List.of(Role.OWNER, Role.CUSTOMER), header, body);
            GetReceiptDTO getReceiptDTO = new GetReceiptDTO(body);
            return ResponseEntity.ok(receiptService.cancleReserve(getReceiptDTO, id));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/paid")
    public ResponseEntity<Map<String, Object>> paid(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            String id = AuthenUtil.authen(List.of(Role.CUSTOMER), header, body);
            GetReceiptDTO getReceiptDTO = new GetReceiptDTO(body);
            return ResponseEntity.ok(receiptService.paidReserve(getReceiptDTO, id));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/notApprove")
    public ResponseEntity<Map<String, Object>> notApprove(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            String id = AuthenUtil.authen(List.of(Role.OWNER), header, body);
            GetReceiptDTO getReceiptDTO = new GetReceiptDTO(body);
            return ResponseEntity.ok(receiptService.notApproveReserve(getReceiptDTO, id));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }
}
