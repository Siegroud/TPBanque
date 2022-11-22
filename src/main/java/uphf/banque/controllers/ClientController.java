package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uphf.banque.entities.Client;
import uphf.banque.services.ClientService;

import java.util.ArrayList;

@RestController
@RequestMapping(clients)
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity getClient(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) {
        if(nom == null || prenom == null){
            return ResponseEntity
                    .badRequest();
        }else{
            return ResponseEntity.ok().body(this.clientService.)
        }
    }

}
