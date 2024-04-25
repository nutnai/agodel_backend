package agodel.controller;

import java.util.Map;

import agodel.data.PlaceRepository;
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

    @PostMapping("/create")
    public String create(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> body){
        return placeService.create(token, body);
    }

}