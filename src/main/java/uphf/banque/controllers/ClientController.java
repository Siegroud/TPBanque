package uphf.banque.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;
import uphf.banque.controllers.errors.HttpErreurFonctionnelle;
import uphf.banque.services.dto.client.*;
import uphf.banque.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity getClientsByNomAndPrenom(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom)  {
        if(nom.isEmpty() || prenom.isEmpty()){
            return ResponseEntity.badRequest().body(new HttpErreurFonctionnelle("Données en entrée manquantes."));  //400
        }else{
            try{
                List<GetClientResponse> getClientResponses = this.clientService.getClientsByNomAndPrenom(nom,prenom);
                if(!getClientResponses.isEmpty()){
                    return ResponseEntity.ok().body(getClientResponses);    //201
                }else{
                    return ResponseEntity.noContent().build();
                }
            }catch (Exception e){
                return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée");  //500
            }
        }

    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody PostClientRequest postClientRequest)  {
        if(postClientRequest == null || postClientRequest.getNom() ==null || postClientRequest.getPrenom() == null || postClientRequest.getDateNaissance() == null
                || postClientRequest.getAdressePostale() == null || postClientRequest.getTelephone()==null){
            return ResponseEntity.badRequest().body(new HttpErreurFonctionnelle("Les données en entrée du service sont non renseignées ou incorrectes.")); // 400
        }else{
            try{
                PostClientResponse postClientResponse = this.clientService.createClient(postClientRequest);
                return ResponseEntity.ok().body(postClientResponse);    // 201
            }catch (Exception e){
                return ResponseEntity.internalServerError().body("Une erreur de traitement interne a été rencontrée."); // 500
            }
        }

    }

    @PutMapping
    public ResponseEntity updateClient(@RequestBody PutClientRequest putClientRequest) {
        if(putClientRequest== null || putClientRequest.getId()<=0 || putClientRequest.getNom()==null || putClientRequest.getPrenom()==null
                || putClientRequest.getAdressePostale()==null || putClientRequest.getTelephone() == null || putClientRequest.getDateNaissance()==null){
            return ResponseEntity.badRequest().body(new HttpErreurFonctionnelle("Les données en entrée du service sont non renseignées ou incorrectes."));
        }else{
            try{
                PutClientResponse putClientResponse = this.clientService.updateClient(putClientRequest);
                return ResponseEntity.ok().body(putClientResponse);
            } catch (ResponseStatusException e){
                if (e.getStatus().value()==404){    // Client non trouvé
                    return ResponseEntity.notFound().build();
                }else{
                    return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée.");
                }
            }
            catch (Exception e){
                return ResponseEntity.internalServerError().body("Une erreur de traitement a été rencontrée");
            }
        }
    }

}
