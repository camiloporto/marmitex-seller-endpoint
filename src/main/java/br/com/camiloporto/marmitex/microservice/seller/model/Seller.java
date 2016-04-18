package br.com.camiloporto.marmitex.microservice.seller.model;

import br.com.camiloporto.marmitex.microservice.seller.service.SellerServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ur42 on 15/04/2016.
 */
@Getter @Setter
@Document
public class Seller {

    @Id
    private String id;
    private String name;
    private String address;

    @NotEmpty(message= "{br.com.camiloporto.marmitex.microservice.seller.PROFILE_REQUIRED}",
            groups= SellerServiceImpl.CreateRules.class)
    private String profileId;
}
