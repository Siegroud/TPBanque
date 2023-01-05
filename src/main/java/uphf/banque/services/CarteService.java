package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uphf.banque.entities.beans.Carte;
import uphf.banque.entities.beans.Client;
import uphf.banque.entities.beans.Compte;
import uphf.banque.entities.rest.carte.PostCarteRequest;
import uphf.banque.entities.rest.carte.PostCarteResponse;
import uphf.banque.entities.rest.compte.GetComptesResponse;
import uphf.banque.exceptions.ProcessException;
import uphf.banque.repositories.CarteRepository;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.entities.rest.carte.GetCarteResponse;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


@Service
public class CarteService {

    @Autowired
    CompteService compteService;
    @Autowired
    CarteRepository carteRepository;

    @Autowired
    CompteRepository compteRepository;

    public static final String CARTE_NON_TROUVEE = "Carte non trouvée.";

    public GetCarteResponse getCarteByIban(String iban) throws ProcessException{

        Compte compte = compteRepository.findCompteByIban(iban);

        Carte carte = carteRepository.findCarteByCompte(compte);

        String idClient = carte.getTitulaireCarte();

        if (carte == null) throw new ProcessException(String.format((CARTE_NON_TROUVEE + "%s"), iban));
        GetCarteResponse getCarteResponse = GetCarteResponse.builder()
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .titulaireCarte(idClient)
                .build();

        return getCarteResponse;
    }

    public String genererNumCarte(){
        Long leftLimit = 100000000000L;
        Long rightLimit = 999999999999L;
        long generatedLong = (long) (Math.random() * (rightLimit - leftLimit));
        return "4973"+Long.toString(generatedLong);
    }

    public String genererDateExpiration(){
        GregorianCalendar calcStr1 = new GregorianCalendar();

        calcStr1.setTime(new Date());
        calcStr1.add(GregorianCalendar.YEAR,+2);
        SimpleDateFormat sdf = new SimpleDateFormat("mm/yy");
        return sdf.format(calcStr1.getTime());
    }
    public PostCarteResponse createCarte(String iban, PostCarteRequest postCarteRequest){
        Compte compte = compteRepository.findCompteByIban(iban);

        String numCarte = genererNumCarte();

        String dateExp = genererDateExpiration();
        Carte carte = Carte.builder()
                .titulaireCarte(postCarteRequest.getTitulaireCarte())
                .numeroCarte(numCarte)
                .dateExpiration(dateExp)
                .code(postCarteRequest.getCode())
                .build();

        return PostCarteResponse.builder()
                .titulaireCarte(carte.getTitulaireCarte())
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .build();



    }
}
