package uphf.banque.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue
    private int idVirement;
    private Date dateCreation;

    private String ibanCompteEmetteur;
    private String ibanCompteBeneficiaire;

}
