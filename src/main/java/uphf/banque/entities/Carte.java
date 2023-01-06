package uphf.banque.entities;

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
    private Integer id;

    private String numeroCarte;
    private String dateExpiration;

    private String titulaireCarte;

    @ManyToOne
    private Compte compte;
    private int code;
}
