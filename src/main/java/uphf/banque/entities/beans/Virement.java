package uphf.banque.entities.beans;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
    private Date dateCreation;

    private double montant;
    private String ibanCompteEmetteur;
    private String ibanCompteBeneficiaire;

}
