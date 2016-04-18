package br.com.camiloporto.marmitex.microservice.seller.service;

import br.com.camiloporto.marmitex.microservice.seller.model.Seller;

import java.util.List;

/**
 * Created by ur42 on 18/04/2016.
 */
public interface SellerService {
    Seller save(Seller s);

    List<Seller> findByProfile(String profileId);

    Seller findById(String id);
}
