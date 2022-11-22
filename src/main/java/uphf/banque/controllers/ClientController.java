package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uphf.banque.services.ClientService;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;
}
