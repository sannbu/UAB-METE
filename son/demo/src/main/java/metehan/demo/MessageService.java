package metehan.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message createMessage(String content, Long replayId, String imagePath, int roomId) {
        Message message = new Message(content);
        message.setIsReplay(false);
        message.setLike(0);
        message.setDislike(0);
        
        if (imagePath != null && !imagePath.isEmpty()) {
            message.setImagePath(imagePath);
        }
        
        // Set room
        if (roomId != 0) {
            message.setRoomId(roomId);
        }
        
        if (replayId != null) {
            Message parentMsg = messageRepository.findById(replayId).orElse(null);
            if (parentMsg != null) {
                message.setIsReplay(true);
                
                if (parentMsg.getReplay() == null) {
                    parentMsg.setReplay(new ArrayList<>());
                }
                parentMsg.getReplay().add(message);
            }   
        }
        
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAllByOrderByCreatedAtDesc();   
    }

    public List<Message> getMessagesByRoomId(int roomId) {
        return messageRepository.findByRoomIdAndIsReplayFalseOrderByCreatedAtDesc(roomId);
    }

    public Message getMessageById(Long id) {
        return messageRepository.findById(id).orElse(null);
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }

    public void likeMessage(Long id) {
        Message message = messageRepository.findById(id).orElse(null);
        if (message != null) {
            message.setLike(message.getLike() + 1);
            messageRepository.save(message);
        }
    }

    public void dislikeMessage(Long id) {
        Message message = messageRepository.findById(id).orElse(null);   
        if (message != null) {
            message.setDislike(message.getDislike() + 1);
            messageRepository.save(message);
        }
    }
    
} 