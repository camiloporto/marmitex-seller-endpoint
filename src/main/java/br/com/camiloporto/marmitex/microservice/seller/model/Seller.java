package br.com.camiloporto.marmitex.microservice.seller.model;

import lombok.Getter;
import lombok.Setter;
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
    private String profileId;
}
