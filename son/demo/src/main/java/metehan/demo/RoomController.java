package metehan.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping
public class RoomController {

    @Autowired
    private RoomService roomService;
    
    private SimpMessagingTemplate messagingTemplate;

    public RoomController(RoomService roomService, SimpMessagingTemplate messagingTemplate) {
        this.roomService = roomService;
        this.messagingTemplate = messagingTemplate;
    }
    
    @PostMapping("/rooms")
    public Room createRoom(@RequestBody RoomRequest request) {
        Room room = roomService.createRoom(request.getName(), request.getDescription());
        messagingTemplate.convertAndSend("/topic/rooms", room);
        return room;
    }


    @GetMapping("/rooms/{name}")
    public Room getRoomByName(@PathVariable String name) {
        return roomService.getRoomByName(name);
    }
    
    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

} 