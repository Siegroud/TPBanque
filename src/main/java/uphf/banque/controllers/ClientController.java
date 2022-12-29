package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uphf.banque.entities.Client;
import uphf.banque.services.ClientService;
import uphf.banque.services.dto.client.GetClientResponse;

import java.util.ArrayList;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public GetClientResponse getClientByNomAndPrenom(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) {
        return clientService.getClientByNomAndPrenom(nom,prenom);
    }



}
