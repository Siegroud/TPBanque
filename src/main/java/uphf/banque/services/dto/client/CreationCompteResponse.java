package uphf.banque.services.dto.client;

import uphf.banque.entities.TypeCompte;

import java.util.Date;
import java.util.List;

public class CreationCompteResponse {
    private String intituleCompte;

    private TypeCompte typeCompte;

    private List<GetTitulairesCompteResponse> titulairesCompte;

    private String iban;

    private Date dateCreation;
}
