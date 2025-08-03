package metehan.demo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "image_path", nullable = true)
    private String imagePath;

    @Column(name = "is_replay", nullable = false)
    private boolean isReplay;

    @Column(name = "likee",nullable = true)
    private int like;
    @Column(name = "dislikee",nullable = true)
    private int dislike;

    @OneToMany
    @JoinColumn(name = "replay_id")
    private List<Message> replay;

    @Column(name = "room_id", nullable = true)
    private int roomId;

    public Message() {
        this.createdAt = LocalDateTime.now();
    }

    public Message(String content) {
        this.content = content;
        if (this.createdAt == null) {
        this.createdAt = LocalDateTime.now();
    }
        this.like = 0;
        this.dislike = 0;

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Message> getReplay() { 
        return replay; 
    }
    
    public void setReplay(List<Message> replay) { 
        this.replay = replay; 
    }

    public boolean getIsReplay() {
        return isReplay;
    }

    public void setIsReplay(boolean isReplay) {
        this.isReplay = isReplay;
    }

    public int getLike() {  
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
   
    
} 