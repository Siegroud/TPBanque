package uphf.banque.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.transaction.Transaction;
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
    private String iban;

    private float solde;
    private String intituleCompte;
    private String typeCompte;
    private Date dateCreation;

    @ManyToMany
    List<Client> titulaireCompte;

    @OneToMany
    List<Carte> cartes;

    //  List<Transaction> transactions;

}
