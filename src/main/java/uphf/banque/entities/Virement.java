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
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVirement;
    private String dateCreation;

    private String libelleVirement;
    private float montant;
    private String ibanCompteEmetteur;
    private String ibanCompteBeneficiaire;

    @OneToMany
    private List<Transaction> transactions;

}
