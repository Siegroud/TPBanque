package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.services.ClientService;
import uphf.banque.entities.rest.GetClientResponse;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public GetClientResponse getClientByNomAndPrenom(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) throws ProcessException {
        return clientService.getClientByNomAndPrenom(nom,prenom);
    }



}
