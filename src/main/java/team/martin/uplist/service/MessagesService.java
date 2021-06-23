package team.martin.uplist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.martin.uplist.entity.MessagesEntity;
import team.martin.uplist.repository.MessagesRepository;

import java.util.List;

@Service
public class MessagesService {
    private MessagesRepository messagesRepository;

    @Autowired
    public MessagesService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    // Messages (Service)

    public MessagesEntity createMessage(MessagesEntity message) {
        return messagesRepository.save(message);
    }

    public List<MessagesEntity> listMessages() {
        return messagesRepository.findAll();
    }

    // Reactions (Service)

    public void addReaction(Long id) {
        MessagesEntity messagesEntity = messagesRepository.getById(id);
        Long total = messagesEntity.getTotal_ups() + 1;
        messagesEntity.setTotal_ups(total);
        messagesRepository.save(messagesEntity);
    }

    public void removeReaction(Long id) {
        MessagesEntity messagesEntity = messagesRepository.getById(id);
        Long total = messagesEntity.getTotal_ups() - 1;
        messagesEntity.setTotal_ups(total);
        messagesRepository.save(messagesEntity);
    }
}