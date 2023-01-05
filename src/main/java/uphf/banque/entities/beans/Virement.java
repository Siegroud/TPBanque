package uphf.banque.entities.beans;

import lombok.*;
import uphf.banque.entities.rest.compte.TransactionDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVirement;
    private String dateCreation;

    private String libelleVirement;
    private float montant;
    private String ibanCompteEmetteur;
    private String ibanCompteBeneficiaire;

    @OneToMany
    private List<Transaction> transactions;

}
