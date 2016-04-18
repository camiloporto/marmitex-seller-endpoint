package br.com.camiloporto.marmitex.microservice.seller.repository;

import br.com.camiloporto.marmitex.microservice.MarmitexSellerEndpointApplication;
import br.com.camiloporto.marmitex.microservice.seller.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by ur42 on 15/04/2016.
 */
@SpringApplicationConfiguration(classes = MarmitexSellerEndpointApplication.class)
public class SellerRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SellerRepository sellerRepository;

    @BeforeMethod
    public void setUp() {
        sellerRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewSeller() {
        Seller s = new Seller();
        s.setName("Espaco e Sabor");
        s.setAddress("Jaguarari, 1056, lagoa nova");
        //.. add fields later..
        Seller saved = sellerRepository.save(s);
        Assert.assertNotNull(saved.getId());
    }

    @Test
    public void shouldUpdateSeller() {
        Seller s = new Seller();
        s.setName("Espaco e Sabor");
        s.setAddress("Jaguarari, 1056, lagoa nova");
        Seller saved = sellerRepository.save(s);

        saved.setName("Sal da Terra");
        saved.setAddress("Amintas Barros, 123");

        sellerRepository.save(saved);

        Seller updated = sellerRepository.findOne(saved.getId());

        Assert.assertEquals(updated.getName(), saved.getName());
        Assert.assertEquals(updated.getAddress(), saved.getAddress());
        Assert.assertEquals(sellerRepository.count(), 1);
    }
}
