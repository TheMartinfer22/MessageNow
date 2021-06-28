package team.martin.messagenow.unit;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import team.martin.messagenow.entity.MessagesEntity;

@SpringBootTest

public class MessageEntityTest {

    @Test
    public void mensagemPodeSerCriada(){
       new MessagesEntity("Olá mundo");
    }

    @Test
    public void mensagemPodeTerReacoesAdicionadas(){
        MessagesEntity messagesEntity = new MessagesEntity();
        messagesEntity.setMessage("Olá mundo");
        messagesEntity.setReactions(10);
        Assertions.assertEquals(messagesEntity.getReactions(), 10);
    }

    @Test
    public void mensagemPodeTerReacoesRemovidas(){
        MessagesEntity messagesEntity = new MessagesEntity();
        messagesEntity.setMessage("Olá mundo");
        messagesEntity.setReactions(10);
        messagesEntity.setReactions(messagesEntity.getReactions() - 5);
        Assertions.assertEquals(messagesEntity.getReactions(), 5);
    }
}
