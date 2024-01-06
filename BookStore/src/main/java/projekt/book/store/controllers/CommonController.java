package projekt.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import projekt.book.store.servives.IBookService;
import projekt.book.store.session.SessionData;

@Controller
public class CommonController {

    @Autowired
    private IBookService bookService;

    @Resource
    SessionData sessionData;

    //private List<Book> filteredBooks = null; Stosowane w przypadku gdy formularz wysyłany jest metodą POST

    @RequestMapping(path = {"/main", "/"}, method = RequestMethod.GET)
    public String main(Model model, @RequestParam(required = false) String pattern){
        //if(this.filteredBooks == null)  Stosowane w przypadku gdy formularz wysyłany jest metodą POST
          /* if(!this.sessionData.isLogged()){
               model.addAttribute("books", new ArrayList<>());
           }*/if(pattern == null) {
            model.addAttribute("books", this.bookService.getAllBooks());
        } else {
            model.addAttribute("books", this.bookService.getFilteredBook(pattern));
           // model.addAttribute("books", this.filteredBooks); Stosowane w przypadku gdy formularz wysyłany jest metodą POST
           // filteredBooks = null; Stosowane w przypadku gdy formularz wysyłany jest metodą POST
        }
       ModelUtils.addCommonDataToModel(model, this.sessionData);
           return "index";
    }

    /*@RequestMapping(path = "/main", method = RequestMethod.POST)
    public String main(
                       @RequestParam String pattern){
        this.filteredBooks = this.bookService.getFilteredBook(pattern);
        return "redirect:/main";
    }*/ //Stosowane w przypadku gdy formularz wysyłany jest metodą POST

    @RequestMapping(path = "/contact", method = RequestMethod.GET)
    public String contact(Model model){
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        return "contact";
    }

}
