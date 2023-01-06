package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import uphf.banque.controllers.errors.HttpErrorResponse;
import uphf.banque.services.dto.virement.PostVirementRequest;
import uphf.banque.services.dto.virement.PostVirementResponse;
import uphf.banque.services.VirementService;

@RestController
@RequestMapping("virements")
public class VirementController {

    @Autowired
    private VirementService virementService;

    @PostMapping
    public ResponseEntity createVirement(@RequestBody PostVirementRequest postVirementRequest){
        if(postVirementRequest.getMontant()<=0 || postVirementRequest.getLibelleVirement()==null || postVirementRequest.getIbanCompteEmetteur()==null
                || postVirementRequest.getIbanCompteBeneficiaire() == null ){
            return ResponseEntity.badRequest().body(new HttpErrorResponse("Les données en entrée du service sont non renseignées ou incorrectes.")); // 400
        }
        try{
            PostVirementResponse postVirementResponse = this.virementService.createVirement(postVirementRequest);
            return ResponseEntity.created(null).body(postVirementResponse);
        }catch(ResponseStatusException e){
            if(e.getStatus().equals(HttpStatus.NOT_FOUND)){
                return ResponseEntity.badRequest().body(new HttpErrorResponse("Compte(s) non trouvé(s)"));
            }
            if(e.getStatus().equals(HttpStatus.BAD_REQUEST)){
                return ResponseEntity.badRequest().body(new HttpErrorResponse("Solde insuffisant."));
            }
            else return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée.");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée.");
        }
    }

}
