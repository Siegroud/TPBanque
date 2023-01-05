package uphf.banque.entities.rest.carte;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.rest.GetTitulairesCompteResponse;

@Getter
@Setter
@Builder
public class GetCarteResponse {

    private String numeroCarte;

    private String dateExpiration;

    private GetTitulairesCompteResponse titulaireCarte;

}
