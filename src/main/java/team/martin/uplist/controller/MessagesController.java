package team.martin.uplist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team.martin.uplist.entity.MessagesEntity;
import team.martin.uplist.service.MessagesService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class MessagesController {

    private MessagesService messagesService;

    public MessagesController(MessagesService messagesService){
        this.messagesService = messagesService;
    }

    // MENSAGEM

    /*
     * Quando a mensagem ser passada no método POST em JSON,
     * irá apontar para a classe que facilitará a edição de novas regras de negócio.
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MessagesEntity createMessage(@RequestBody MessagesEntity message) throws Exception {
        return messagesService.createMessage(message);
    }

    /*
     * Quando o método GET for executado irá retornar a lista de mensagens que foram adicionadas
     * ao banco de dados.
     */
    @GetMapping
    List<MessagesEntity> listMessages(){
        return messagesService.listMessages();
    }

    @DeleteMapping("/{id}/delete")
    public void removeMessage(@PathVariable Long id){
        messagesService.removeMessage(id);
    }

    // REACTIONS

    /*
     * Irá adicionar um voto na mensagem quando for passado o path com a ID da mesma.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(value = "/reaction/{id}/add")
    public void addReaction(@PathVariable Long id){
        messagesService.addReaction(id);
    }

    /*
     * Irá remover um voto na mensagem quando for passado o path com a ID da mesma.
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping(value = "/reaction/{id}/remove")
    public void removeReaction(@PathVariable Long id){
        messagesService.removeReaction(id);
    }
}
