package team.martin.uplist.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @Column
    private Long total_ups;


    /*
     * Antes da inclusão na tabela se a variável total_ups entrar como nula, ele irá
     * definir pra 0 Long, podendo sim definir na variável diretamente mas apenas aplicando essa função para
     * aprendizado.
     */
    @PrePersist
    void preInsert() {
        if (this.total_ups == null)
            this.total_ups = 0L;
    }
}