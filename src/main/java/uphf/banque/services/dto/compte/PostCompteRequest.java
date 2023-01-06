package uphf.banque.services.dto.compte;

import lombok.*;
import uphf.banque.entities.TypeCompte;
import uphf.banque.entities.Client;
import uphf.banque.services.dto.client.ClientDTO;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostCompteRequest {
    private String intituleCompte;

    private TypeCompte typeCompte;

    private List<ClientDTO> titulairesCompte;

}
