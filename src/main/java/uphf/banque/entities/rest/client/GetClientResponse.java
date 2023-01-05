package uphf.banque.entities.rest.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.beans.Client;

import java.util.List;

@Getter
@Setter
@Builder
public class GetClientResponse {

    private List<Client> clients;

    public GetClientResponse(List<Client> clients) {
        this.clients = clients;
    }

}
