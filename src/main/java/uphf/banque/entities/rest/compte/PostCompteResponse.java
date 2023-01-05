package uphf.banque.entities.rest.compte;

import lombok.*;
import uphf.banque.entities.TypeCompte;
import uphf.banque.entities.beans.Client;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCompteResponse {
    private String intituleCompte;

    private TypeCompte typeCompte;

    private List<Client> titulairesCompte;

    private String iban;

    private String dateCreation;


}
