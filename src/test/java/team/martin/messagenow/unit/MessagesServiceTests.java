package team.martin.messagenow.unit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.martin.messagenow.entity.MessagesEntity;
import team.martin.messagenow.service.MessagesService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagesServiceTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    private MessagesService messagesService;

    @Test
    public void mensagemPodeSerCriada(){
        messagesService.createMessage(new MessagesEntity("Hello World"));
    }

    @Test
    public void aMensagemNaoPodePassarDe200Caracteres() {
        exception.expectMessage("A mensagem não pode passar de 200 caracteres.");
        messagesService.createMessage(new MessagesEntity("a7DCWvZeRZrHT76XfsGSQamYm1Su3oux379AJkaVzz4fknv4jWm8sBrKRXpB7yVV7Ii50jiZZDZxUJRDOIveMqm2RaqWoAwZvRee7MOpUQ72CX1BWNeZuBDuMxXaKBxpvwhib158BZOz2xzuUezA8xEvXW6pLn3wvsHghcpf3tW5K0R8vXsr5YoIpTwYCzUsiHA648DCO"));
    }

    @Test
    public void mensagemPodeReceberEmotions(){
        messagesService.createMessage(new MessagesEntity("💜❗✨\uD83E\uDD7A"));
    }
}
