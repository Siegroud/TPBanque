package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import uphf.banque.entities.rest.compte.PostCompteRequest;
import uphf.banque.entities.rest.compte.PostCompteResponse;

import uphf.banque.services.CompteService;
import uphf.banque.services.ExceptionService;
import uphf.banque.entities.rest.compte.GetComptesResponse;

@RestController
@RequestMapping("comptes")
public class CompteController extends ExceptionService {
    @Autowired
    CompteService compteService;

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

}
