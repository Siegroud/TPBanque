package uphf.banque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uphf.banque.entities.rest.virement.PostVirementRequest;
import uphf.banque.entities.rest.virement.PostVirementResponse;
import uphf.banque.services.VirementService;

@RestController
@RequestMapping("virements")
public class VirementController {

    @Autowired
    private VirementService virementService;

    public PostVirementResponse createVirement(@RequestBody PostVirementRequest postVirementRequest){
        return virementService.createVirement(postVirementRequest);
    }

}
