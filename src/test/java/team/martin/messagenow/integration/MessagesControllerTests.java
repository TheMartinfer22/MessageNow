package team.martin.messagenow.integration;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

/*
 * Testes de integração com a aplicação.
 */

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest // Teste EndToEnd
public class MessagesControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void quandoAMensagemForCriadaDeveRetornarCodigo201() throws Exception {

        // URL e criação da mensagem
        URI uri = new URI("/");
        String criarMensagem = "{\"message\":\"Hello World\"}";

        // Criação da requisição post e aguardo da resposta 201 para verificar se foi criado.
        mockMvc.perform(MockMvcRequestBuilders.post((uri))
                .content(criarMensagem)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(201))
                .andExpect(MockMvcResultMatchers.jsonPath("message").value("Hello World"));

    }

    @Test
    public void quandoAMensagemForEnviadaDeveSerCapazDeReceberReacaoERemover() throws Exception {

        // URL e criação da mensagem
        URI uri = new URI("/");
        String criarMensagem = "{\"message\":\"hello world\"}";

        // Criação de entidades para o banco por requisição
        mockMvc.perform(MockMvcRequestBuilders.post((uri)).content(criarMensagem).contentType(MediaType.APPLICATION_JSON));

        // Adicionar e Remover reaçções por requisição get no endpoint reaction/{id}/add e remove.
        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/1/add"));
        mockMvc.perform(MockMvcRequestBuilders.get("/reaction/1/remove"));
    }

    @Test
    public void quandoEntrarNoEndPointRootEleDeveraRetornarTodasAsMensagensComIdMensagemEReacao() throws Exception {

        // URL e criação da mensagem
        URI uri = new URI("/");
        String criarMensagem = "{\"message\":\"hello world\"}";

        // Criação de entidades para o banco por requisição
        mockMvc.perform(MockMvcRequestBuilders.post((uri)).content(criarMensagem).contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.post((uri)).content(criarMensagem).contentType(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders.post((uri)).content(criarMensagem).contentType(MediaType.APPLICATION_JSON));

        // Teste final e definitivo para receber o corpo da requisição no endpoint master(root)
        mockMvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.content()
                        .json("[{\"id\":1," +
                                "\"message\":\"hello world\"," +
                                "\"reactions\":0},{\"id\":2," +
                                "\"message\":\"hello world\"," +
                                "\"reactions\":0}," +
                                "{\"id\":3," +
                                "\"message\":\"hello world\"," +
                                "\"reactions\":0}]"));
    }

    @Test
    public void mensagensPodemSerRemovidas() throws Exception {

        // URL e criação da mensagem
        URI uri = new URI("/");
        String criarMensagem = "{\"message\":\"hello world\"}";

        // Criação de entidades para o banco por requisição
        mockMvc.perform(MockMvcRequestBuilders.post((uri)).content(criarMensagem).contentType(MediaType.APPLICATION_JSON));

        // Teste no endpoint root /{id}/delete para deletar uma mensagem e ver se é possível
        mockMvc.perform(MockMvcRequestBuilders.delete((uri + "1/delete")));
    }
}
