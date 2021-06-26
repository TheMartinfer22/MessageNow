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

    /**
     * Criará as mensagem que foi recebida para o banco de dados.
     *
     * @param message Recebe a mensagem no formato String.
     * @return MessagesEntity - retorna a entidade criada em JSON com Long Id, String message, long reactions.
     */
    public MessagesEntity createMessage(MessagesEntity message)  {
        return messagesRepository.save(message);
    }


    /**
     * Irá retornar uma lista de MessagesEntity.
     *
     * @return List<MessagesEntity> - retorna TODAS entidades do banco de dados com Long Id, String message, long reactions.
     */
    public List<MessagesEntity> listMessages() {
        return messagesRepository.findByOrderByReactionsDesc();
    }

    /**
     * Deleta a mensagem pelo endpoint ao fornecer a ID da mesma.
     *
     * @param id Long - Irá deletar a mensagem ao ser passada a identicação da mesma.
     */
    public void removeMessage(Long id){
        messagesRepository.deleteById(id);
    }

    // REACTIONS (Service)

    /**
     * Adiciona as reações esperando a ID fornecida pelo controller e incrementando + 1,
     * Após, salvando o mesmo para atualizar a mudança.
     *
     * @param id Long - Adiciona uma reação a mensagem.
     */
    public void addReaction(Long id) {
        MessagesEntity messagesEntity = messagesRepository.getById(id);
        long total = messagesEntity.getReactions() + 1;
        messagesEntity.setReactions(total);
        messagesRepository.save(messagesEntity);
    }

    /**
     * Remove as reações esperando a ID fornecida pelo controller e diminuindo - 1,
     * Após, salvando o mesmo para atualizar a mudança.
     *
     * @param id Long - Remove uma reação da mensagem.
     */
    public void removeReaction(Long id) {
        MessagesEntity messagesEntity = messagesRepository.getById(id);
        long total = messagesEntity.getReactions() - 1;
        messagesEntity.setReactions(total);
        messagesRepository.save(messagesEntity);
    }
}