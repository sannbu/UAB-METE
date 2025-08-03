package metehan.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;


    public Room createRoom(String name, String description) {
        Room room = new Room(name, description);
        return roomRepository.save(room);
    }

    public Room getRoomByName(String name) {
        return roomRepository.findByName(name);
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

   

  
} 