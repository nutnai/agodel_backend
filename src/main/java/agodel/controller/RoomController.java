package agodel.controller;

import java.util.Map;

import agodel.data.RoomRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import agodel.service.RoomService;

@RestController
@Controller
@RequestMapping("/room")
public class RoomController{
    private RoomRepository roomRepository;
    private RoomService roomService;

    public RoomController(RoomRepository roomRepository, RoomService roomService){
        this.roomRepository = roomRepository;
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public String create(@RequestBody Map<String, Object> body){
        return roomService.create(body);
    }

}