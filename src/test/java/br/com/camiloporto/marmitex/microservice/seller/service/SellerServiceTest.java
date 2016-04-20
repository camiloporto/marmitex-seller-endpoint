package br.com.camiloporto.marmitex.microservice.seller.service;

import br.com.camiloporto.marmitex.microservice.MarmitexSellerEndpointApplication;
import br.com.camiloporto.marmitex.microservice.seller.model.Menu;
import br.com.camiloporto.marmitex.microservice.seller.model.MenuCategory;
import br.com.camiloporto.marmitex.microservice.seller.model.MenuOption;
import br.com.camiloporto.marmitex.microservice.seller.model.Seller;
import br.com.camiloporto.marmitex.microservice.seller.repository.SellerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by ur42 on 18/04/2016.
 */
@SpringApplicationConfiguration(classes = MarmitexSellerEndpointApplication.class)
public class SellerServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerService sellerService;

    @BeforeMethod
    public void setUp() {
        sellerRepository.deleteAll();
    }

    @Test
    public void shouldCreateNewSeller() throws JsonProcessingException {
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

        Seller saved = sellerService.save(s);
        String json = new ObjectMapper().writeValueAsString(saved);
        System.out.println(json);
        Assert.assertNotNull(saved.getId());
    }

    @Test
    public void shouldFindByProfileId() {
        Seller s = new Seller();
        s.setName("Espaco e Sabor");
        s.setAddress("Jaguarari, 1056, lagoa nova");
        s.setProfileId("camiloporto@email.com");
        sellerService.save(s);

        Seller s2 = new Seller();
        s2.setName("Sal da Terra");
        s2.setAddress("Amintas, 1056, lagoa nova");
        s2.setProfileId("vendor@email.com");
        sellerService.save(s2);

        List<Seller> result = sellerService.findByProfile("camiloporto@email.com");

        Assert.assertEquals(1, result.size(), "count of sellers not matched");
        Assert.assertEquals(result.get(0).getId(), s.getId(), "returned results did not match expected ones");
    }

    @Test
    public void shouldFindByIdId() {
        Seller s = new Seller();
        s.setName("Espaco e Sabor");
        s.setAddress("Jaguarari, 1056, lagoa nova");
        s.setProfileId("camiloporto@email.com");
        sellerService.save(s);

        Seller s2 = new Seller();
        s2.setName("Sal da Terra");
        s2.setAddress("Amintas, 1056, lagoa nova");
        s2.setProfileId("vendor@email.com");
        sellerService.save(s2);

        Seller result = sellerService.findById(s.getId());

        Assert.assertEquals(result.getId(), s.getId(), "returned results did not match expected ones");
    }

    @Test
    public void profileIsRequired() {
        Seller s = new Seller();
        s.setName("Espaco e Sabor");
        s.setAddress("Jaguarari, 1056, lagoa nova");
        s.setProfileId(null);
        try {
            sellerService.save(s);
            Assert.fail("should raise exception. profile id is required");
        } catch (ConstraintViolationException c) {
            String expectedTemplateMsg = "{br.com.camiloporto.marmitex.microservice.seller.PROFILE_REQUIRED}";
            assertExceptionMessageIsPresent(c, expectedTemplateMsg);
        }
    }

    @Test
    public void nameIsRequired() {
        Seller s = new Seller();
        s.setName(null);
        s.setAddress("Jaguarari, 1056, lagoa nova");
        s.setProfileId("camiloporto@email.com");
        try {
            sellerService.save(s);
            Assert.fail("should raise exception. seller's name is required");
        } catch (ConstraintViolationException c) {
            String expectedTemplateMsg = "{br.com.camiloporto.marmitex.microservice.seller.NAME_REQUIRED}";
            assertExceptionMessageIsPresent(c, expectedTemplateMsg);
        }
    }

    @Test
    public void addressIsRequired() {
        Seller s = new Seller();
        s.setName("Espaco Sabor");
        s.setAddress(null);
        s.setProfileId("camiloporto@email.com");
        try {
            sellerService.save(s);
            Assert.fail("should raise exception. seller's address is required");
        } catch (ConstraintViolationException c) {
            String expectedTemplateMsg = "{br.com.camiloporto.marmitex.microservice.seller.ADDRESS_REQUIRED}";
            assertExceptionMessageIsPresent(c, expectedTemplateMsg);
        }
    }

    void assertExceptionMessageIsPresent(ConstraintViolationException e, String expectedTemplateMsg) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        boolean found = false;
        ConstraintViolation<?> cvFound = null;
        for (ConstraintViolation<?> cv : violations){
            if(cv.getMessageTemplate().equals(expectedTemplateMsg)) {
                cvFound = cv;
                break;
            }
        }
        Assert.assertNotNull(cvFound, "expected template msgs not found");
        Assert.assertNotEquals(
                cvFound.getMessage(),
                cvFound.getMessageTemplate(),
                "message template " + expectedTemplateMsg + " not i18nized. check ValidationMessages.properties");
    }
}
