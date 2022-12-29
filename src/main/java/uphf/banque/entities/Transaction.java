package uphf.banque.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue
    private int id;

    private float montant;
    private TypeTransaction typeTransaction;
    private Date dateCreation;
    private String idSource;

    @ManyToOne
    private Compte beneficiaire;

    @ManyToOne
    private Compte emetteur;

}
