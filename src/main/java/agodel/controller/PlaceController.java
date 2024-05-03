package agodel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import agodel.data.PlaceRepository;
import agodel.model.PlaceModel;
import agodel.model.Receipt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import agodel.service.PlaceService;

@RestController
@Controller
@RequestMapping("/place")
public class PlaceController{
    private PlaceRepository placeRepository;
    private PlaceService placeService;

    public PlaceController(PlaceRepository placeRepository, PlaceService placeService){
        this.placeRepository = placeRepository;
        this.placeService = placeService;
    }

//    @PostMapping("/create")
//    public String create(@RequestBody Map<String, Object> body){
//        return placeService.create(body);
//    }

    @PostMapping("/edit")
    public ResponseEntity<Map<String, Object>> edit(@RequestBody Map<String, Object> body){
        try {
            String result = placeService.edit(body);
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
            PlaceModel place = placeService.showDetail(body);
            Map<String, Object> response = new HashMap<>();
            response.put("place", place);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/rent")
    public ResponseEntity<Map<String, Object>> rent(@RequestBody Map<String, Object> body){
        try{
            Receipt receipt = placeService.rentRoom(body);
            Map<String, Object> response = new HashMap<>();
            response.put("receipt", receipt);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestBody Map<String, Object> body){
        try {
            List<PlaceModel> places = placeService.search(body);
            Map<String, Object> response = new HashMap<>();
            response.put("places", places);
            return ResponseEntity.ok(response);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/testSearch")
    public List<PlaceModel> testSearch(@RequestBody Map<String, Object> body){
        return placeService.testSearch(body);
    }



}