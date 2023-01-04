package uphf.banque.entities.rest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uphf.banque.entities.beans.Client;

import javax.persistence.Entity;
import java.util.Date;
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

public class PostClientResponse {

    private Client client;

}