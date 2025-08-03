package metehan.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class MessageController {

   
    @Autowired
    private MessageService messageService;
    private SimpMessagingTemplate messagingTemplate;

    public MessageController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }
    
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
       
        String uploadDir = "uploads/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
                
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            return "error";
        }

                
        return fileName;
    }
    
    @GetMapping("/uploads/{fileName}")
    public byte[] getImage(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get("uploads/" + fileName);
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            return new byte[0];
        }
    }

    @PostMapping("/mess")
    public Message createMessage(@RequestBody MessageRequest request) {
        Message saved = messageService.createMessage(request.getContent(), request.getReplayId(), request.getImagePath(), request.getRoomId());
        messagingTemplate.convertAndSend("/topic/messages", saved);
        return saved;
    }

    @PostMapping("/mess/{roomId}")
    public Message createMessageInRoom(@PathVariable int roomId, @RequestBody MessageRequest request) {
        request.setRoomId(roomId);
        Message saved = messageService.createMessage(request.getContent(), request.getReplayId(), request.getImagePath(), roomId);
        messagingTemplate.convertAndSend("/topic/messages", saved);
        messagingTemplate.convertAndSend("/topic/messages/" + roomId, saved);
        return saved;
    }

    @GetMapping("/mess/{roomId}")
    public List<Message> getMessagesByRoom(@PathVariable int roomId) {
        return messageService.getMessagesByRoomId(roomId);
    }

    @GetMapping("/mess")
    public List<Message> getMessages() {
        return messageService.getAllMessages();
    }
  
    @PostMapping("/mess/like")
    public void likeMessage(@RequestBody MessageRequest request) { 
        messageService.likeMessage(request.getId());
        Message updatedMessage = messageService.getMessageById(request.getId());
        messagingTemplate.convertAndSend("/topic/messages", updatedMessage);
    }
    
    @PostMapping("/mess/dislike")
    public void dislikeMessage(@RequestBody MessageRequest request) {
        messageService.dislikeMessage(request.getId());
        Message updatedMessage = messageService.getMessageById(request.getId());
        messagingTemplate.convertAndSend("/topic/messages", updatedMessage);
    }

} 