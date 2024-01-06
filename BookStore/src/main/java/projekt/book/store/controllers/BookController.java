package projekt.book.store.controllers;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projekt.book.store.exception.BookValidationExpection;
import projekt.book.store.model.Book;
import projekt.book.store.servives.IBookService;
import projekt.book.store.session.SessionData;
import projekt.book.store.validators.BookValidator;

import java.util.Optional;

@Controller
@RequestMapping(path = "/book")
public class BookController {

    @Resource
    SessionData sessionData;

    @Autowired
    IBookService bookService;

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String addBook(Model model){
        if(!this.sessionData.isAdmin()) {
            return "redirect:/main";
        }
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("bookModel", new Book());
        return "add-book";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute Book book){
        if(!this.sessionData.isAdmin()) {
            return "redirect:/main";
        }
        try {
            BookValidator.validateBook(book);
            this.bookService.persistBook(book);
        } catch (BookValidationExpection e) {
            return "redirect:/book/add";
        }
        return "redirect:/main";
    }



    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable int id, Model model){
        Optional<Book> bookBox = this.bookService.getBookById(id);
        if(!this.sessionData.isAdmin() || bookBox.isEmpty()) {
            return "redirect:/main";
        }
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("bookModel", bookBox.get());
        return "add-book";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String updateBook(@PathVariable int id,
                             @ModelAttribute Book book){
        if(!this.sessionData.isAdmin()) {
            return "redirect:/main";
        }
        try{
            BookValidator.validateBook(book);
            book.setId(id);
            this.bookService.updateBook(book);
        } catch (BookValidationExpection e){
            return "redirect:/update/"+id;
        }
        return "redirect:/main";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
        public String deleteBook(@PathVariable int id){
        if(!this.sessionData.isAdmin()) {
            return "redirect:/main";
        }
        this.bookService.deleteBook(id);
        return "redirect:/main";
    }
}
