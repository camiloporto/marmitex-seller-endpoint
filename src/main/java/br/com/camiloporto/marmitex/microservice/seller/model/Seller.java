package br.com.camiloporto.marmitex.microservice.seller.model;

import br.com.camiloporto.marmitex.microservice.seller.service.SellerServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by ur42 on 15/04/2016.
 */
@Getter @Setter
@Document
public class Seller {

    @Id
    private String id;
    @NotEmpty(message= "{br.com.camiloporto.marmitex.microservice.seller.NAME_REQUIRED}",
            groups= SellerServiceImpl.CreateRules.class)
    private String name;

    @NotEmpty(message= "{br.com.camiloporto.marmitex.microservice.seller.ADDRESS_REQUIRED}",
            groups= SellerServiceImpl.CreateRules.class)
    private String address;

    @NotEmpty(message= "{br.com.camiloporto.marmitex.microservice.seller.PROFILE_REQUIRED}",
            groups= SellerServiceImpl.CreateRules.class)
    private String profileId;

    private List<Menu> menus;

    private String commentsForClients;
}
