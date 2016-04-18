package br.com.camiloporto.marmitex.microservice.seller.repository;

import br.com.camiloporto.marmitex.microservice.seller.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ur42 on 15/04/2016.
 */
public interface SellerRepository extends MongoRepository<Seller, String> {

    List<Seller> findByProfileId(String profileId);
}
