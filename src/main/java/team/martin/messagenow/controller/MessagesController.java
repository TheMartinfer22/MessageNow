package team.martin.messagenow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.martin.messagenow.entity.MessagesEntity;
import team.martin.messagenow.service.MessagesService;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MessagesController {

    private MessagesService messagesService;

    public MessagesController(MessagesService messagesService){
        this.messagesService = messagesService;
    }

    // MENSAGEM

    /**
     * Quando a mensagem ser passada no método POST em JSON,
     * irá apontar para a classe que facilitará a edição de novas regras de negócio.
     *
     * @param message Recebe a mensagem no formato String.
     * @return MessagesEntity - retorna a entidade criada em JSON com Long Id, String message, long reactions.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MessagesEntity createMessage(@RequestBody @NotEmpty MessagesEntity message) {
        return messagesService.createMessage(message);
    }

    /**
     * Quando o método GET for executado irá retornar a lista de mensagens que foram adicionadas
     * ao banco de dados.
     *
     * @return List<MessagesEntity> - retorna TODAS entidades do banco de dados com Long Id, String message, long reactions.
     */
    @GetMapping
    List<MessagesEntity> listMessages(){
        return messagesService.listMessages();
    }

    /**
     * Deleta a mensagem pelo endpoint ao fornecer a ID da mesma.
     *
     * @param id Long - Irá deletar a mensagem ao ser passada a identicação da mesma.
     */
    @DeleteMapping("/{id}/delete")
    public void removeMessage(@PathVariable Long id){
        messagesService.removeMessage(id);
    }

    // REACTIONS

    /**
     * Irá adicionar uma reação na mensagem quando for acessado o endpoint com a ID da mesma.
     *
     * @param id Long - Adiciona uma reação a mensagem.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(value = "/reaction/{id}/add")
    public void addReaction(@PathVariable Long id){
        messagesService.addReaction(id);
    }

    /**
     * Irá remover uma reação na mensagem quando for acessado o endpoint com a ID da mesma.
     *
     * @param id Long - Remove uma reação da mensagem.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(value = "/reaction/{id}/remove")
    public void removeReaction(@PathVariable Long id){
        messagesService.removeReaction(id);
    }
}
