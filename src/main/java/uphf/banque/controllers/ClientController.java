package uphf.banque.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import uphf.banque.controllers.errors.HttpErreurFonctionnelle;
import uphf.banque.services.dto.client.*;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity getClientsByNomAndPrenom(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom) throws ProcessException {
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
    public ResponseEntity createClient(@RequestBody PostClientRequest postClientRequest) throws ProcessException {
        if(postClientRequest == null || postClientRequest.getNom() ==null || postClientRequest.getPrenom() == null || postClientRequest.getDateNaissance() == null
                || postClientRequest.getAdressePostale() == null || postClientRequest.getTelephone()==null){
            return ResponseEntity.badRequest().body(new HttpErreurFonctionnelle("Les données en entrée du service sont non renseignées ou incorrectes.")); // 400
        }
        return clientService.createClient(postClientRequest);
    }

    @PutMapping
    public PutClientResponse updateClient(@RequestBody PutClientRequest putClientRequest)  throws ProcessException {

        return clientService.updateClient(putClientRequest);
    }

}
