package uphf.banque.services.dto.client;

import uphf.banque.entities.TypeTransaction;

import java.util.Date;

public class CreatePaiementResponse {
    private int idTransaction;

    private double montant;

    private TypeTransaction typeTransaction;

    private Date dateCreation;


}
