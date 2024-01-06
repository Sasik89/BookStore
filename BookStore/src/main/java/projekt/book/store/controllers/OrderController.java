package projekt.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projekt.book.store.servives.IOrderService;
import projekt.book.store.session.SessionData;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    @Resource
    SessionData sessionData;

    @Autowired
    IOrderService orderService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public String orders(Model model){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("orders", this.orderService.getAllOrdersForCurrentUser());
        return "orders";

    }


}
