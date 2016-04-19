package br.com.camiloporto.marmitex.microservice.seller.rest;

import br.com.camiloporto.marmitex.microservice.seller.model.Seller;
import br.com.camiloporto.marmitex.microservice.seller.service.SellerService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    public Map<String, Object> save(@RequestBody Seller seller) {
        Seller saved = sellerService.save(seller);
        Map<String, Object> result = new HashMap<>();
        result.put("id", saved.getId());
        return result;
    }
}
