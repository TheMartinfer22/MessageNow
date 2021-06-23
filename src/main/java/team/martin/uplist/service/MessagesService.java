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

    // MENSAGEM (Service)

    /*
     * Criará as mensagens recebidas para o banco de dados.
     */
    public MessagesEntity createMessage(MessagesEntity message) {
        return messagesRepository.save(message);
    }

    public List<MessagesEntity> listMessages() {
        return messagesRepository.findAll();
    }

    // REACTIONS (Service)

    /*
     * Adiciona as reações esperando a ID fornecida pelo controller e incrementando + 1,
     * Após, salvando o mesmo para atualizar a mudança.
     */
    public void addReaction(Long id) {
        MessagesEntity messagesEntity = messagesRepository.getById(id);
        Long total = messagesEntity.getTotal_ups() + 1;
        messagesEntity.setTotal_ups(total);
        messagesRepository.save(messagesEntity);
    }

    /*
     * Remove as reações esperando a ID fornecida pelo controller e diminuindo - 1,
     * Após, salvando o mesmo para atualizar a mudança.
     */
    public void removeReaction(Long id) {
        MessagesEntity messagesEntity = messagesRepository.getById(id);
        Long total = messagesEntity.getTotal_ups() - 1;
        messagesEntity.setTotal_ups(total);
        messagesRepository.save(messagesEntity);
    }
}