package br.com.camiloporto.marmitex.microservice.seller.rest;

import br.com.camiloporto.marmitex.microservice.seller.model.Seller;
import br.com.camiloporto.marmitex.microservice.seller.service.SellerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ur42 on 19/04/2016.
 */
@RestController
@RequestMapping("/seller")
public class SellerRest {

    @Autowired @Setter
    private SellerService sellerService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Object> save(@RequestBody Seller seller,
                                    Principal principal) {
        seller.setProfileId(principal.getName());
        Seller saved = sellerService.save(seller);
        Map<String, Object> result = new HashMap<>();
        result.put("id", saved.getId());
        return result;
    }


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public @ResponseBody Seller get(
            Principal principal) {

        String profileId = principal.getName();
        List<Seller> result = sellerService.findByProfile(profileId);
        if(!result.isEmpty()) {
            //FIXME should a profile have more than one Seller?
            return result.get(0);
        }
        return null;
    }
}
