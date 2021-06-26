package team.martin.messagenow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team.martin.messagenow.entity.MessagesEntity;

import java.util.List;

@Repository
public interface MessagesRepository extends JpaRepository<MessagesEntity, Long> {
    List<MessagesEntity> findByOrderByReactionsDesc();
}
