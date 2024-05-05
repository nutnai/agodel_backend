package agodel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agodel.model.Receipt;
import agodel.service.PlaceService;
import agodel.util.AuthenUtil;
import agodel.util.AuthenUtil.Role;
import agodel.DTO.PlaceDTO.*;
import agodel.DTO.UserDTO.GetOwnerDTO;
import agodel.exception.ResponseEntityException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/place")
public class PlaceController {
    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping("/edit")
    public ResponseEntity<Map<String, Object>> edit(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.OWNER_ID), header, body);
            EditDTO editDTO = new EditDTO(body);
            return ResponseEntity.ok(placeService.edit(editDTO));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    @PostMapping("/detail")
    public ResponseEntity<Map<String, Object>> detail(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.ALL), header);
            GetOwnerDTO getOwnerDTO = new GetOwnerDTO(body);
            return ResponseEntity.ok(placeService.showDetail(getOwnerDTO));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

    // ! todo: see sequence diagram
    @PostMapping("/rent")
    public ResponseEntity<Map<String, Object>> rent(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            Receipt receipt = placeService.rentRoom(body);
            Map<String, Object> response = new HashMap<>();
            response.put("receipt", receipt);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<Map<String, Object>> search(
            @RequestHeader(required = false) Map<String, Object> header,
            @RequestBody(required = false) Map<String, Object> body) {
        try {
            AuthenUtil.authen(List.of(Role.ALL), header);
            SearchDTO searchDTO = new SearchDTO(body);
            return ResponseEntity.ok(placeService.search(searchDTO));
        } catch (ResponseEntityException e) {
            return e.getResponseEntity();
        }
    }

}