package team.martin.messagenow.unit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.martin.messagenow.entity.MessagesEntity;
import team.martin.messagenow.repository.MessagesRepository;
import team.martin.messagenow.service.MessagesService;

import java.util.List;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest
public class MessagesServiceTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private MessagesService messagesService;

    @Mock
    MessagesRepository messagesRepository;

    @Test
    public void criarMensagemDeveSerPossivel() {
        messagesService.createMessage(new MessagesEntity("Hello World"));
    }

    @Test
    public void criarMensagemNaoPodePassarDe200Caracteres() {
        exception.expectMessage("A mensagem não pode passar de 200 caracteres.");
        messagesService.createMessage(new MessagesEntity("a7DCWvZeRZrHT76XfsGSQamYm1Su3oux379AJkaVzz4fknv4jWm8sBrKRXpB7yVV7Ii50jiZZDZxUJRDOIveMqm2RaqWoAwZvRee7MOpUQ72CX1BWNeZuBDuMxXaKBxpvwhib158BZOz2xzuUezA8xEvXW6pLn3wvsHghcpf3tW5K0R8vXsr5YoIpTwYCzUsiHA648DCO"));
    }

    @Test
    public void criarMensagemPodeReceberEmotions() {
        messagesService.createMessage(new MessagesEntity("💜❗✨\uD83E\uDD7A"));
    }

    @Test
    public void listarMensagensParaCadaDeveSerUmElemento() {
        messagesService.createMessage(new MessagesEntity("Olá mundo"));
        messagesService.createMessage(new MessagesEntity("Olá mundo 2"));
        List<MessagesEntity> messagesEntityList = messagesService.listMessages();
        Assertions.assertEquals(messagesEntityList.size(), 2);
    }
    @Test
    public void reacaoPodeSerAdicionada() {
        messagesService.createMessage(new MessagesEntity("Olá mundo"));
        MessagesEntity messagesEntity = messagesRepository.getById(1L);
//        long total = messagesEntity.getReactions() + 1; TODO Resolver problema
//        messagesEntity.setReactions(total);
        messagesRepository.save(messagesEntity);
    }

//    @Test // TODO Resolver problema o valor está retornando nulo
//    public void mensagemDeveConterUmaIdentificaoPadrao(){
//        messagesService.createMessage(new MessagesEntity("Olá mundo teste de identifição"));
//        System.out.println(messagesRepository.getById(1L));
////        MessagesEntity messagesEntity = messagesRepository.getById(1L);
////        messagesRepository.save(messagesEntity);
////        System.out.println(messagesEntity.getMessage());
////        Assertions.assertEquals(messagesEntity.getMessage(), "Olá mundo teste de identifição");
//    }
}
