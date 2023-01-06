package uphf.banque.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String iban;
    private String solde;
    private String intituleCompte;
    private TypeCompte typeCompte;
    private String dateCreation;

    @ManyToMany
    List<Client> titulairesCompte;

    @OneToMany
    List<Carte> cartes;

    @OneToMany
    private List<Transaction> transactions;


}
