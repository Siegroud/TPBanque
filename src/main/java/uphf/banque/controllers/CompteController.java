package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uphf.banque.services.CompteService;
import uphf.banque.services.ExceptionService;

@RestController
@RequestMapping("comptes")
public class CompteController extends ExceptionService {
    @Autowired
    CompteService compteService;
}
