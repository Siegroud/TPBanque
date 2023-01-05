package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uphf.banque.entities.rest.carte.GetCarteResponse;
import uphf.banque.entities.rest.carte.PostCarteRequest;
import uphf.banque.entities.rest.carte.PostCarteResponse;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.repositories.CarteRepository;
import uphf.banque.services.CarteService;

@RestController
@RequestMapping("cartes")
public class CarteController {

    @Autowired
    private CarteService carteService;

    @GetMapping("/comptes/{iban}/cartes")
    public GetCarteResponse getCarteByIban(@RequestParam("iban") String iban) throws ProcessException {
        return carteService.getCarteByIban(iban);
    }

    @PostMapping("/comptes/{iban}/cartes")
    public PostCarteResponse createCarte(@RequestParam("iban") String iban, @RequestBody PostCarteRequest postCarteRequest){
        return carteService.createCarte(iban,postCarteRequest);
    }
}
