package team.martin.messagenow.unit;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.martin.messagenow.entity.MessagesEntity;
import team.martin.messagenow.repository.MessagesRepository;
import team.martin.messagenow.service.MessagesService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest
public class MessagesServiceTests {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @InjectMocks
    private MessagesService messagesService;

    @Mock
    MessagesRepository messagesRepository;

    @Test
    public void deveCadastrarUmaMensagemEretornarAMensagemComSeuRespectivoIDEDataDeCadastro() {
        MessagesEntity messagesEntity = new MessagesEntity(1L, "Hello World");
        when(messagesRepository.save(any(MessagesEntity.class))).thenReturn(messagesEntity);
        MessagesEntity retorno =  messagesService.createMessage(messagesEntity);
        Assertions.assertNotNull(retorno);
        Assertions.assertNotNull(retorno.getCreatedAt());
        Assertions.assertNotNull(retorno.getId());
        Assertions.assertEquals(retorno.getId(), 1L);
        Assertions.assertEquals(retorno.getMessage(), "Hello World");
    }

    @Test
    public void reacaoPodeSerAdicionada() {
        MessagesEntity messagesEntity = new MessagesEntity(1L, "Hello World");
        when(messagesRepository.getById(any(Long.class))).thenReturn(messagesEntity);
        when(messagesRepository.save(any(MessagesEntity.class))).thenReturn(messagesEntity);
        messagesService.addReaction(1L);
        Assertions.assertEquals(messagesEntity.getReactions(), 1);
    }

    @Test
    public void reacaoPodeSerRemovida() {
        MessagesEntity messagesEntity = new MessagesEntity(1L, "Hello World", 20, null);
        when(messagesRepository.getById(any(Long.class))).thenReturn(messagesEntity);
        when(messagesRepository.save(any(MessagesEntity.class))).thenReturn(messagesEntity);
        messagesService.removeReaction(1L);
        Assertions.assertEquals(messagesEntity.getReactions(), 19);
    }
}
