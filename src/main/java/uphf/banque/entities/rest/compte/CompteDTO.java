package uphf.banque.entities.rest.compte;

import uphf.banque.entities.TypeCompte;

import java.util.List;

public class CompteDTO {
    private String iban;

    private String solde;

    private String intituleCompte;

    private TypeCompte typecompte;

    private List<String> titulairesCompte;
}
