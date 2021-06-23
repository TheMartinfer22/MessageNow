package team.martin.uplist.controller;

import org.springframework.web.bind.annotation.*;
import team.martin.uplist.entity.MessagesEntity;
import team.martin.uplist.service.MessagesService;

import java.util.List;

@RestController
public class MessagesController {

    private MessagesService messagesService;

    public MessagesController(MessagesService messagesService){
        this.messagesService = messagesService;
    }

    // Mensagens

    @PostMapping
    public MessagesEntity createMessage(@RequestBody MessagesEntity message){
        return messagesService.createMessage(message);
    }

    @GetMapping
    List<MessagesEntity> listMessages(){
        return messagesService.listMessages();
    }

    // Reactions

    @GetMapping(value = "/reaction/{id}/add")
    public void addReaction(@PathVariable Long id){
        messagesService.addReaction(id);
    }

    @GetMapping(value = "/reaction/{id}/remove")
    public void removeReaction(@PathVariable Long id){
        messagesService.removeReaction(id);
    }
}
