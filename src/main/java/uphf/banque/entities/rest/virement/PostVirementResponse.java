package uphf.banque.entities.rest.virement;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.rest.compte.TransactionDTO;

import java.util.List;

@Getter
@Setter
@Builder
public class PostVirementResponse {
    private String idVirement;

    private String dateCreation;

    private List<TransactionDTO> transactions;
}
