package uphf.banque.entities.beans;

import lombok.*;
import uphf.banque.entities.TypeCompte;

import javax.persistence.*;
import java.util.Date;
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
    private float solde;
    private String intituleCompte;
    private TypeCompte typeCompte;
    private Date dateCreation;

    @ManyToMany
    List<Client> titulairesCompte;

    @OneToMany
    List<Carte> cartes;

    @OneToMany
    private List<Transaction> transactions;


}
