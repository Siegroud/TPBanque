package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uphf.banque.entities.beans.Transaction;
import uphf.banque.entities.rest.compte.*;

import uphf.banque.services.CompteService;
import uphf.banque.services.ExceptionService;
import uphf.banque.services.TransactionService;

@RestController
@RequestMapping("comptes")
public class CompteController extends ExceptionService {
    @Autowired
    private CompteService compteService;



    @GetMapping("/{id}")
    public GetComptesResponse getComptesByIdClient(@RequestParam("id") int id) {
        return compteService.getComptesByIdClient(id);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public PostCompteResponse createCompte(@RequestBody PostCompteRequest postCompteRequest) {

        return compteService.createCompte(postCompteRequest);
    }

    @PostMapping("comptes/{iban}/cartes/{numeroCarte}/paiement")
    public PostPaiementResponse createPaiement(@RequestParam("iban") String iban, @RequestParam("numeroCarte") String numeroCarte,@RequestBody PostPaiementRequest postPaiementRequest){
        return compteService.createPaiement(iban,numeroCarte,postPaiementRequest);
    }


}
