package metehan.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByOrderByCreatedAtDesc();
    List<Message> findByRoomIdOrderByCreatedAtDesc(int roomId);
    List<Message> findByRoomIdAndIsReplayFalseOrderByCreatedAtDesc(int roomId);
} 