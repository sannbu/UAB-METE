package metehan.demo;

public class MessageRequest {
    private String content;
    private Long replayId;
    private Long id;
    private String imagePath;
    private int roomId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReplayId() { 
        return replayId; 
    }
    
    public void setReplayId(Long replayId) { 
        this.replayId = replayId; 
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
} 