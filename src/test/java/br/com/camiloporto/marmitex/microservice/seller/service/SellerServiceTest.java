package br.com.camiloporto.marmitex.microservice.seller.service;

import br.com.camiloporto.marmitex.microservice.MarmitexSellerEndpointApplication;
import br.com.camiloporto.marmitex.microservice.seller.model.Seller;
import br.com.camiloporto.marmitex.microservice.seller.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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
    public void shouldCreateNewSeller() {
        Seller s = new Seller();
        s.setName("Espaco e Sabor");
        s.setAddress("Jaguarari, 1056, lagoa nova");
        Seller saved = sellerService.save(s);
        Assert.assertNotNull(saved.getId());
    }

    @Test
    public void profileIsRequired() {
        Seller s = new Seller();
        s.setName("Espaco e Sabor");
        s.setAddress("Jaguarari, 1056, lagoa nova");
        s.setProfileId(null);
        try {
            Seller saved = sellerService.save(s);
            Assert.fail("should raise exception. profile id is required");
        } catch (ConstraintViolationException c) {
            //FIXME refactor exception asserts.. create a checker..
            String expectedTemplateMsg = "{br.com.camiloporto.marmitex.microservice.seller.PROFILE_REQUIRED}";
            Set<ConstraintViolation<?>> violations = c.getConstraintViolations();
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
}
