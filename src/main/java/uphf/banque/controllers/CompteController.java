package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.services.CompteService;
import uphf.banque.services.ExceptionService;
import uphf.banque.entities.rest.GetComptesResponse;

@RestController
@RequestMapping("comptes")
public class CompteController extends ExceptionService {
    @Autowired
    CompteService compteService;

    @GetMapping("/{id}")
    public GetComptesResponse getComptesByIdClient(@RequestParam("id") int id) {
        return compteService.getComptesByIdClient(id);
    }



}
