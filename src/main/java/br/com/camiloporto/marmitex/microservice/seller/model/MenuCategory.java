package br.com.camiloporto.marmitex.microservice.seller.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by ur42 on 18/04/2016.
 */
@Getter
@Setter
public class MenuCategory {

    private List<MenuOption> options;
    private String name;
    private String comments;

    public MenuCategory() {
    }

    public MenuCategory(String name) {
        this.name = name;
    }
}
