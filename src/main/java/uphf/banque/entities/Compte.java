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

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<Client> titulairesCompte;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Carte> cartes;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Transaction> transactions;


}
