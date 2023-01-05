package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uphf.banque.entities.beans.Compte;
import uphf.banque.entities.rest.client.*;
import uphf.banque.entities.rest.compte.PostCompteRequest;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.services.ClientService;

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
    public PostClientResponse createClient(@RequestBody PostClientRequest postClientRequest) throws ProcessException {
        return clientService.createClient(postClientRequest);
    }

    @PutMapping
    public PutClientResponse updateClient(@RequestBody PutClientRequest putClientRequest)  throws ProcessException {

        return clientService.updateClient(putClientRequest);
    }

}
