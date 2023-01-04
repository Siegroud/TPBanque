package uphf.banque.services.dto.client;

import uphf.banque.entities.TypeCompte;

import java.util.List;

public class CreationCompteRequest {
    private String intituleCompte;

    private TypeCompte typeCompte;

    private List<GetTitulairesCompteResponse> titulairesCompte;



}
