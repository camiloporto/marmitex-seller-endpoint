package br.com.camiloporto.marmitex.microservice.seller.rest;

import br.com.camiloporto.marmitex.microservice.MarmitexSellerEndpointApplication;
import br.com.camiloporto.marmitex.microservice.seller.model.Menu;
import br.com.camiloporto.marmitex.microservice.seller.model.MenuCategory;
import br.com.camiloporto.marmitex.microservice.seller.model.MenuOption;
import br.com.camiloporto.marmitex.microservice.seller.model.Seller;
import br.com.camiloporto.marmitex.microservice.seller.repository.SellerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ur42 on 19/04/2016.
 */
@SpringApplicationConfiguration(classes = MarmitexSellerEndpointApplication.class)
@WebAppConfiguration
public class SellerRestTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private SellerRepository sellerRepository;


    private MockMvc mvc;
    private ObjectMapper objectMapper;

    @BeforeClass
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @BeforeMethod
    public void clearData() {
        sellerRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewSeller() throws Exception {
        Seller s = new Seller();
        s.setName("Espaco e Sabor");
        s.setAddress("Jaguarari, 1056, lagoa nova");
        s.setProfileId("camiloporto@email.com");

        MenuOption rice = new MenuOption("Rice");
        MenuOption bean = new MenuOption("Bean");
        MenuOption meat = new MenuOption("Meat");
        MenuOption chicken = new MenuOption("Chicken");
        MenuOption salad = new MenuOption("Green Salad");

        MenuCategory carbo = new MenuCategory("Carbo");
        carbo.setOptions(Arrays.asList(rice, bean));

        MenuCategory protein = new MenuCategory("Protein");
        protein.setOptions(Arrays.asList(meat, chicken));

        MenuCategory veggs = new MenuCategory("Veggs");
        veggs.setOptions(Arrays.asList(salad));


        Menu mon = new Menu("Monday");
        mon.setCategories(Arrays.asList(carbo, protein, veggs));

        Menu tue = new Menu("Tuesday");
        tue.setCategories(Arrays.asList(carbo, protein, veggs));

        s.setMenus(Arrays.asList(mon, tue));

        String json = objectMapper.writeValueAsString(s);
        mvc.perform(
            post("/seller")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .content(json)
            )
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").exists());

        List<Seller> result = sellerRepository.findByProfileId("camiloporto@email.com");
        Assert.assertFalse(result.isEmpty());
    }

    //FIXME configura Apps no heoku (mongoDB, URLs, parametros, application.properties, Travis, etc..)

}
