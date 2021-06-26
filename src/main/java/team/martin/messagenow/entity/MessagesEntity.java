package team.martin.messagenow.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MessagesEntity {

    public MessagesEntity(String message) {
        this.message = message;
    }

    public MessagesEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "O campo não pode estar vazio.")
    @Size(max = 200, message = "A mensagem não pode passar de 200 caracteres.")
    private String message;

    @Column
    private long reactions;

}
