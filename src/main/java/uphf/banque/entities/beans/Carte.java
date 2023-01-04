package uphf.banque.entities.beans;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numeroCarte;
    private String dateExpiration;

    @OneToOne
    private Client titulaireCarte;

    @ManyToOne
    private Compte compte;
    private int code;
}
