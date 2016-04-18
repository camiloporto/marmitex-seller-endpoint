package br.com.camiloporto.marmitex.microservice.seller.service;

import br.com.camiloporto.marmitex.microservice.seller.model.Seller;
import br.com.camiloporto.marmitex.microservice.seller.repository.SellerRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by ur42 on 18/04/2016.
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired @Setter
    private SellerRepository sellerRepository;

    @Autowired @Setter
    private Validator validator;

    @Override
    public Seller save(Seller s) {
        //FIXME refactor validation process. create a component?
        Set<ConstraintViolation<SellerForm>> violations = validator.validate(new SellerForm(s), CreateRules.class);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        return sellerRepository.save(s);
    }

    public interface CreateRules{}

    @Getter
    @Setter
    class SellerForm {

        @Valid
        private Seller seller;

        public SellerForm(Seller seller) {
            this.seller = seller;
        }
    }
}
