package team.martin.uplist.unit;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import team.martin.uplist.entity.MessagesEntity;
import team.martin.uplist.service.MessagesService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessagesServiceTests {

    @Autowired
    private MessagesService messagesService;


    @Test
    public void teste() {

        int quantidade = messagesService.listMessages().size();
        System.out.println(quantidade);

        // Uma mensage == 1139
        // Duas mensagens == 1141
    }
}
