package uphf.banque.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRange;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import uphf.banque.entities.*;
import uphf.banque.repositories.*;
import uphf.banque.services.dto.carte.GetCarteResponse;
import uphf.banque.services.dto.carte.PostCarteRequest;
import uphf.banque.services.dto.carte.PostCarteResponse;
import uphf.banque.services.dto.client.ClientDTO;
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
    @Autowired
    private VirementRepository virementRepository;


    public GetComptesResponse getComptesByClient(int idClient) {
        Client cli = clientRepository.findClientById(idClient);

        List<Compte> listcom = compteRepository.findComptesByTitulairesCompteContaining(cli);

        if(listcom.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Aucun compte trouvé");
        }

        List<CompteDTO> listcomDTO = new ArrayList<>();

        for (Compte com : listcom) {
            List<String> listId = new ArrayList<>();

            List<TransactionDTO> listDTO = new ArrayList<>();

            List<Carte> carteList = carteRepository.findCartesByCompte(com);

            List<Virement> virementList = virementRepository.findVirementsByIbanCompteBeneficiaire(com.getIban());

            List<Transaction> listtran = new ArrayList<>(); //Notre liste de transactions à alimenter au fur et à mesure
            for (Carte carte:
                 carteList) {
                listtran.addAll(transactionRepository.findTransactionsByCarteSource(carte.getNumeroCarte()));
            }
            for (Virement virement:
                    virementList) {
                listtran.addAll(transactionRepository.findTransactionsByVirementSource(virement.getIbanCompteEmetteur()));
            }


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

    public Long calculNumeroCompte(){
        Long leftLimit = 10000000000L;
        Long rightLimit = 99999999999L;
        long generatedLong = (long) (Math.random() * (rightLimit - leftLimit));
        return generatedLong;
    }

    public PostCompteResponse createCompte(PostCompteRequest postCompteRequest) {

        List<Client> clientList = new ArrayList<>();
        for (ClientDTO clientDTO:
                postCompteRequest.getTitulairesCompte()) {
            Client client = clientRepository.findClientById(Integer.parseInt(clientDTO.getIdClient()));
            clientList.add(client);
        }

        int codeBanque = clientList.get(0).getCodeBanque();
        int codeGuichet = clientList.get(0).getCodeGuichet();


        long numeroCompte = calculNumeroCompte();
        long calcIban = 97 - (89 * Long.parseLong(Integer.toString(codeBanque)) + 15 * Long.parseLong(Integer.toString(codeGuichet)) + 3*numeroCompte)%97;
        String iban = "FR76"+ codeBanque + codeGuichet + numeroCompte + calcIban;
        Compte com = Compte.builder()
                .intituleCompte(postCompteRequest.getIntituleCompte())
                .typeCompte(postCompteRequest.getTypeCompte())
                .titulairesCompte(clientList)
                .iban(iban)
                .dateCreation((LocalDateTime.now()).toString())
                .solde("1000")
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
        System.out.println(iban);
        if(compte == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Compte non trouvé");
        }
        Carte carte = carteRepository.findCarteByNumeroCarte(numeroCarte);
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
                        .carteSource(Integer.toString(carte.getId()))
                        .build();
                transactionRepository.save(transaction);
                compte.getTransactions().add(transaction);
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
                .compte(compte)
                .build();

        carteRepository.save(carte);

        PostCarteResponse postCarteResponse = PostCarteResponse.builder()
                .titulaireCarte(carte.getTitulaireCarte())
                .numeroCarte(carte.getNumeroCarte())
                .dateExpiration(carte.getDateExpiration())
                .build();

        return postCarteResponse;



    }
    public TransactionDTO getTransactionDTO(Transaction transaction){

        String idsource;
        if(transaction.getId() != null){
            if(transaction.getCarteSource() == null){
                idsource = transaction.getVirementSource();
                TransactionDTO tran = TransactionDTO.builder()
                        .id(Integer.toString(transaction.getId()))
                        .montant(transaction.getMontant())
                        .typeTransaction(transaction.getTypeTransaction())
                        .typeSource(transaction.getTypeSource())
                        .idSource(idsource)
                        .build();
                return tran;
            }else{
                idsource = transaction.getCarteSource();
                TransactionDTO tran = TransactionDTO.builder()
                        .id(Integer.toString(transaction.getId()))
                        .montant(transaction.getMontant())
                        .typeTransaction(transaction.getTypeTransaction())
                        .typeSource(transaction.getTypeSource())
                        .idSource(idsource)
                        .build();
                return tran;
            }
        }else return null;

    }


}
