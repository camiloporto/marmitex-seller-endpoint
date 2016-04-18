package br.com.camiloporto.marmitex.microservice.seller.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ur42 on 18/04/2016.
 */
@Getter
@Setter
public class MenuOption {

    private String name;
    private String comments;

    public MenuOption() {
    }
    public MenuOption(String name) {
        this.name = name;
    }


}
