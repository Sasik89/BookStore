package projekt.book.store.controllers.rest;

import jakarta.annotation.Resource;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import projekt.book.store.servives.ICartService;
import projekt.book.store.session.SessionData;

@RestController
@RequestMapping(path = "/api/v1/cart")
public class CartRestControler {

    @Resource
    SessionData sessionData;
    @Autowired
    ICartService cartService;

    @RequestMapping(path = "/add/{id}", method = RequestMethod.GET)
        public void addToCart(@PathVariable int id) {
            if(!this.sessionData.isLogged()) {
                return;
            }
            this.cartService.addProductToCart(id);
    }
}
