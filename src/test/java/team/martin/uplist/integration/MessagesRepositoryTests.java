package team.martin.uplist.integration;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.martin.uplist.entity.MessagesEntity;
import team.martin.uplist.repository.MessagesRepository;
import org.junit.Test;

/*
 * Testes de integração com a aplicação.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MessagesRepositoryTests {

    @Autowired
    private MessagesRepository messagesRepository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void mensagemNaoPodeSerEmBranco(){

        // Erro Esperado
        exception.expectMessage("O campo não pode estar vazio.");

        // Inclusão e Salvando dados
        messagesRepository.save(new MessagesEntity(""));
    }

    @Test
    public void mensagemNaoPodePassarDe200Caracteres(){

        // Erro Esperado
        exception.expectMessage("A mensagem não pode passar de 200 caracteres.");

        // Inclusão
        MessagesEntity messagesEntity = new MessagesEntity("a7DCWvZeRZrHT76XfsGSQamYm1Su3oux379AJkaVzz4fknv4jWm8sBrKRXpB7yVV7Ii50jiZZDZxUJRDOIveMqm2RaqWoAwZvRee7MOpUQ72CX1BWNeZuBDuMxXaKBxpvwhib158BZOz2xzuUezA8xEvXW6pLn3wvsHghcpf3tW5K0R8vXsr5YoIpTwYCzUsiHA648DCO");

        // Salvando dados
        messagesRepository.save(messagesEntity);
    }

    @Test
    public void mensagemDeveSerPassadaAoBancoDeDadosEConterUmaID(){

        // Inclusão
        String mensagem = "Uma mensagem qualquer";
        MessagesEntity messagesEntity = new MessagesEntity(mensagem);

        // Buscando dados e comparando
        Assertions.assertEquals(messagesEntity.getMessage(), mensagem);
    }

    @Test
    public void mensagemDeveSerIgualAQueFoiPassadaAoBancoDeDados(){
        // Erro Esperado
        exception.expect(AssertionFailedError.class);

        // Inclusão
        String mensagem = "Mensagem que irá ser passada";
        MessagesEntity messagesEntity = new MessagesEntity(mensagem);

        // Buscando dados e comparando
        Assertions.assertEquals(messagesEntity.getMessage(), "Mensagem que não é igual a variável mensagem");
    }
}

