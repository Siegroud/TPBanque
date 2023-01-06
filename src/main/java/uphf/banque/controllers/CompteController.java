package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;
import uphf.banque.controllers.errors.HttpErrorResponse;
import uphf.banque.entities.Transaction;
import uphf.banque.services.CompteService;
import uphf.banque.services.dto.carte.GetCarteResponse;
import uphf.banque.services.dto.carte.PostCarteRequest;
import uphf.banque.services.dto.carte.PostCarteResponse;
import uphf.banque.services.dto.compte.*;

import java.util.List;

@RestController
public class CompteController {

    @Autowired
    private CompteService compteService;


    @GetMapping("/comptes")
    public ResponseEntity getComptesByIdClient(@RequestParam("id") int id) {    // GetComptesResponse
        if (id < 0){
            return ResponseEntity.badRequest().body(new HttpErrorResponse("Les donnéees sont incorrectes"));
        } else {
            try {
                GetComptesResponse getComptesResponse = this.compteService.getComptesByClient(id);
                return ResponseEntity.ok().body(getComptesResponse);
            } catch (ResponseStatusException e){
                if(e.getStatus().equals(HttpStatus.NOT_FOUND)){
                    return ResponseEntity.notFound().build();
                }else{
                    return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée.");
                }
            } catch (Exception e){
                return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée.");
            }
        }
    }

    @PostMapping("/comptes")
    public ResponseEntity createCompte(@RequestBody PostCompteRequest postCompteRequest) {
        if (postCompteRequest.getIntituleCompte() == null || postCompteRequest.getTypeCompte()==null || postCompteRequest.getTitulairesCompte()==null){
            return ResponseEntity.badRequest().body(new HttpErrorResponse("Les données en entrées du service sont non renseignées ou incorrectes."));
        }
        try {
            PostCompteResponse postCompteResponse =this.compteService.createCompte(postCompteRequest);
            return ResponseEntity.created(null).body(postCompteResponse);
        } catch (ResponseStatusException e){
            if (e.getStatus().equals(HttpStatus.NOT_FOUND)){
                return ResponseEntity.badRequest().body("Client(s) non trouvé(s).");
            } else {
                e.printStackTrace();
                return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée.");
            }
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée.");
        }
    }


    @GetMapping("/comptes/{iban}/cartes")
    private ResponseEntity getCartesByIban(@RequestParam("iban") String iban){
        try{
            List<GetCarteResponse> getCarteResponses = this.compteService.getCartesByIban(iban);
            if(getCarteResponses.isEmpty()){
                return ResponseEntity.badRequest().body("Aucune carte n'est rattachée au compte fourni en paramètres."); // 404
            }
            return ResponseEntity.ok().body(getCarteResponses);
        } catch (ResponseStatusException e){
            if (e.getStatus().equals(HttpStatus.NOT_FOUND)){
                return ResponseEntity.badRequest().body("Aucune carte n'est rattachée au compte fourni en paramètres.");   // 500
            }else{
                return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée");
            }
        }
    }

    @PostMapping("/comptes/{iban}/cartes")
    public ResponseEntity createCarte(@RequestParam String iban, @RequestBody PostCarteRequest postCarteRequest){
        if(postCarteRequest.getTitulaireCarte() == null){
            return ResponseEntity.badRequest().body("Les données en entrées du service sont non renseignées ou incorrectes.");
        }else{
            try{
                PostCarteResponse postCarteResponse = this.compteService.createCarte(iban,postCarteRequest);
                return ResponseEntity.created(null).body(postCarteResponse);
            }catch(ResponseStatusException e){
                if (e.getStatus().equals(HttpStatus.NOT_FOUND) || e.getStatus().equals(HttpStatus.BAD_REQUEST)){
                    return ResponseEntity.badRequest().body("Les données en entrée du service sont non renseignées ou incorrectes.");
                }else{
                    return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée.");
                }
            }catch (Exception e){
                return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée.");
            }
        }

    }

    @PostMapping("/comptes/{iban}/cartes/{numeroCarte}/paiement")
    public ResponseEntity createPaiement(@RequestParam String iban, @RequestParam String numeroCarte, @RequestBody PostPaiementRequest postPaiementRequest){
        if(postPaiementRequest.getMontant()<0){
            return ResponseEntity.badRequest().body("Les données en entrée sont incorrectes, le montant doit être positif.");
        }
        try{
            PostPaiementResponse postPaiementResponse = this.compteService.createPaiement(iban,numeroCarte,postPaiementRequest);
            return ResponseEntity.created(null).body(postPaiementResponse);
        }catch (ResponseStatusException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Aucune carte ou compte fournie en paramètres");
        }catch (Exception e){

            return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée.");
        }


    }

    @GetMapping
    public TransactionDTO getTransactionDTO(@RequestBody Transaction transaction){
        return compteService.getTransactionDTO(transaction);
    }


}
