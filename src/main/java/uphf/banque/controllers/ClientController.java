package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uphf.banque.entities.beans.Compte;
import uphf.banque.entities.rest.PostClientResponse;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.services.ClientService;
import uphf.banque.entities.rest.GetClientResponse;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
            (
                produces = {MediaType.APPLICATION_JSON_VALUE}
            )
    public GetClientResponse getClientsByNomAndPrenom(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) throws ProcessException {
        return clientService.getClientsByNomAndPrenom(nom,prenom);
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public PostClientResponse createClient(@RequestParam("prenom") String prenom, @RequestParam("nom") String nom, @RequestParam("dateNaissance") String dateNaissance,
                                           @RequestParam("telephone") String telephone, @RequestParam("adressePostale") String adressePostale) throws ProcessException {
        return clientService.createClient(prenom,nom,dateNaissance,telephone,adressePostale, new ArrayList<>());
    }

    @PutMapping
    public PutClientResponse updateClientsByNomAndPrenom(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) throws ProcessException {
        return clientService.updateClientsByNomAndPrenom(nom,prenom);
    }

}
