package team.martin.messagenow.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MessagesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotEmpty(message = "O campo não pode estar vazio.")
    @Size(max = 200, message = "A mensagem não pode passar de 200 caracteres.")
    private String message;

    @Column
    private long reactions;

    @Column(name = "created_at")
    @NotEmpty(message = "A data de criação não pode estar vazia.")
    @NotNull(message = "A data de criação não pode ser nula.")
    private LocalDateTime createdAt;

    public MessagesEntity(String message) {
        this.message = message;
    }

    public MessagesEntity(Long id, String messsage){
        this.id = id;
        this.message = messsage;
    }
}
