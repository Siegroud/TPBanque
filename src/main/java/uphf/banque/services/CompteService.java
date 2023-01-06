package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uphf.banque.entities.TypeSource;
import uphf.banque.entities.TypeTransaction;
import uphf.banque.entities.Carte;
import uphf.banque.entities.Client;
import uphf.banque.entities.Compte;
import uphf.banque.entities.Transaction;
import uphf.banque.repositories.CarteRepository;
import uphf.banque.repositories.ClientRepository;
import uphf.banque.repositories.CompteRepository;
import uphf.banque.repositories.TransactionRepository;
import uphf.banque.services.dto.carte.GetCarteResponse;
import uphf.banque.services.dto.carte.PostCarteRequest;
import uphf.banque.services.dto.carte.PostCarteResponse;
import uphf.banque.services.dto.compte.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CarteRepository carteRepository;
    @Autowired
    private TransactionRepository transactionRepository;




    private static final String COMPTE_NON_TROUVE = "Le compte n'a pas été trouvé.";

    public GetComptesResponse getComptesByClient(int idClient) {
        Client cli = clientRepository.findClientById(idClient);

        List<Compte> listcom = compteRepository.findComptesByClient(cli);

        if(listcom.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aucun compte trouvé");
        }

        List<CompteDTO> listcomDTO = new ArrayList<>();

        for (Compte com : listcom) {
            List<String> listId = new ArrayList<>();

            List<TransactionDTO> listDTO = new ArrayList<>();

            List<Transaction> listtran = transactionRepository.findTransactionsByCompte(com);

            for (Transaction transaction: listtran) {
                listDTO.add(getTransactionDTO(transaction));
            }

            for(Client titulaire : com.getTitulairesCompte()){
                listId.add(Integer.toString(titulaire.getId()));
            }


            CompteDTO comDTO = CompteDTO.builder()
                    .iban(com.getIban())
                    .solde(com.getSolde())
                    .intituleCompte(com.getIntituleCompte())
                    .typecompte(com.getTypeCompte())
                    .titulairesCompte(listId)
                    .transactions(listDTO)
                    .build();

            listcomDTO.add(comDTO);
        }


        return GetComptesResponse.builder().comptes(listcomDTO).build();
    }

    public String calculNumeroCompte(){
        Long leftLimit = 10000000000L;
        Long rightLimit = 99999999999L;
        long generatedLong = (long) (Math.random() * (rightLimit - leftLimit));
        return Long.toString(generatedLong);
    }

    public PostCompteResponse createCompte(PostCompteRequest postCompteRequest) {
        int codeBanque = postCompteRequest.getTitulairesCompte().get(0).getCodeBanque();
        int codeGuichet = postCompteRequest.getTitulairesCompte().get(0).getCodeGuichet();



        int numeroCompte = Integer.parseInt(calculNumeroCompte());
        int calcIban = 97 - (89 * codeBanque + 15 * codeGuichet + 3*numeroCompte)%97;
        String iban = "FR76"+ Integer.toString(codeBanque)+Integer.toString(codeGuichet) + Integer.toString(numeroCompte)+ Integer.toString(calcIban);
        Compte com = Compte.builder()
                .intituleCompte(postCompteRequest.getIntituleCompte())
                .typeCompte(postCompteRequest.getTypeCompte())
                .titulairesCompte(postCompteRequest.getTitulairesCompte())
                .iban(iban)
                .dateCreation((LocalDateTime.now()).toString())
                .solde("0")
                .build();
        compteRepository.save(com);

        return PostCompteResponse.builder()
                .intituleCompte(postCompteRequest.getIntituleCompte())
                .typeCompte(postCompteRequest.getTypeCompte())
                .titulairesCompte(postCompteRequest.getTitulairesCompte())
                .iban(iban)
                .dateCreation(com.getDateCreation())
                .build();
    }

    public PostPaiementResponse createPaiement(String iban, String numeroCarte, PostPaiementRequest postPaiementRequest){

        Compte compte = compteRepository.findCompteByIban(iban);

        List<Transaction> listTransaction = compte.getTransactions();
        Carte carte = carteRepository.findCarteByNumeroCarte(numeroCarte);

        if(compte == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Compte non trouvé");
        }
        if(carte == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Carte non trouvée");
        }

        if(carte.getCompte().getIban().equals(iban)){
            if(Float.parseFloat(compte.getSolde()) >= postPaiementRequest.getMontant()){
                compte.setSolde(Float.toString(Float.parseFloat(compte.getSolde()) - postPaiementRequest.getMontant()).toString());
                compteRepository.save(compte);
                Transaction transaction = Transaction.builder()
                        .montant(postPaiementRequest.getMontant())
                        .typeTransaction(TypeTransaction.DEBIT)
                        .typeSource(TypeSource.CARTE)
                        .dateCreation((LocalDateTime.now()).toString())
                        .idSource(Integer.toString(carte.getId()))
                        .build();
                transactionRepository.save(transaction);
                listTransaction.add(transaction);
                compte.setTransactions(listTransaction);
                compteRepository.save(compte);

                PostPaiementResponse postPaiementResponse = PostPaiementResponse.builder()
                        .idTransaction(transaction.getId())
                        .montant(transaction.getMontant())
                        .typeTransaction(transaction.getTypeTransaction())
                        .dateCreation(transaction.getDateCreation())
                        .build();
                return postPaiementResponse;
            }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Solde insuffisant");
            }
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Carte non associée au compte");
        }

    }


    public List<GetCarteResponse> getCartesByIban(String iban) {

        List<GetCarteResponse> getCarteResponses = new ArrayList<>();
        Compte compte = compteRepository.findCompteByIban(iban);

        List<Carte> cartes = carteRepository.findCartesByCompte(compte);

        for (Carte carte: cartes) {
            GetCarteResponse getCarteResponse = GetCarteResponse.builder()
                    .numeroCarte(carte.getNumeroCarte())
                    .dateExpiration(carte.getDateExpiration())
                    .titulaireCarte(carte.getTitulaireCarte())
                    .build();

            getCarteResponses.add(getCarteResponse);
        }


        return getCarteResponses;
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

        carteRepository.save(carte);

        PostCarteResponse postCarteResponse = PostCarteResponse.builder()
                .titulaireCarte(carte.getTitulaireCarte())
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .build();

        return postCarteResponse;



    }

    public List<Transaction> getTransactionsByCompte(Compte compte){
        List<Transaction> list = transactionRepository.findTransactionsByCompte(compte);
        return list;
    }
    public TransactionDTO getTransactionDTO(Transaction transaction){

        TransactionDTO tran = TransactionDTO.builder()
                .id(Integer.toString(transaction.getId()))
                .montant(transaction.getMontant())
                .typeTransaction(transaction.getTypeTransaction())
                .typeSource(transaction.getTypeSource())
                .idSource(transaction.getIdSource())
                .build();

        return tran;
    }


}
