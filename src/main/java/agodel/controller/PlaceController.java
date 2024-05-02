package agodel.controller;

import java.util.List;
import java.util.Map;

import agodel.data.PlaceRepository;
import agodel.model.PlaceModel;
import agodel.model.Receipt;
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
    public String create(@RequestBody Map<String, Object> body){
        return placeService.create(body);
    }

    @PostMapping("/edit")
    public String edit(@RequestBody Map<String, Object> body){
        return placeService.edit(body);
    }

    @PostMapping("/detail")
    public PlaceModel showDetail(@RequestBody Map<String, Object> body){
        return placeService.showDetail(body);
    }

    @PostMapping("/rent")
    public Receipt rent(@RequestBody Map<String, Object> body){
        return placeService.rentRoom(body);
    }

    @PostMapping("/search")
    public List<PlaceModel> search(@RequestBody Map<String, Object> body){
        return placeService.search(body);
    }

    @PostMapping("/testSearch")
    public List<PlaceModel> testSearch(@RequestBody Map<String, Object> body){
        return placeService.testSearch(body);
    }



}