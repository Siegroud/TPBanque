package uphf.banque.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carte {
    @Id
    private String numeroCarte;

    private String dateExpiration;

    @OneToOne
    private Client titulaireCarte;

    @ManyToOne
    private Compte iban;

    private int code;
}
