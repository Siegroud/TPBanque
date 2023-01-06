package uphf.banque.services.dto.compte;

import lombok.*;
import uphf.banque.entities.TypeCompte;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompteDTO {
    private String iban;

    private String solde;

    private String intituleCompte;

    private TypeCompte typecompte;

    private List<String> titulairesCompte;

    private List<TransactionDTO> transactions;
}
