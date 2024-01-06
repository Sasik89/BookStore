package projekt.book.store.controllers;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import projekt.book.store.exception.LoginAlreadyExistException;
import projekt.book.store.exception.UserValidationException;
import projekt.book.store.model.User;
import projekt.book.store.servives.IAuthenticationService;
import projekt.book.store.session.SessionData;
import projekt.book.store.validators.UserValidator;

@Controller
public class AuthenticationController {

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionData sessionData;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestParam String login,
                        @RequestParam String password) {
        try {
            UserValidator.validateLogin(login);
            UserValidator.validatePassword(password);
            this.authenticationService.authenticate(login, password);
            if (sessionData.isLogged()) {
                return "redirect:/main";}
            } catch(UserValidationException e){
            }
            this.sessionData.setInfo("Niepoprawny login lub hasło");
            return "redirect:/login";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        this.authenticationService.logout(request);
        return "redirect:/main";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        ModelUtils.addCommonDataToModel(model, this.sessionData);
        model.addAttribute("userModel", new User());
        model.addAttribute("info", this.sessionData.getInfo());
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user,
                           @RequestParam String password2) {
        try {
            UserValidator.validateUser(user);
            UserValidator.validatePasswordEquality(user.getPassword(), password2);
            this.authenticationService.register(user);
        } catch (LoginAlreadyExistException | UserValidationException e) {
            return "redirect:/register";
        }
        return "redirect:/login";
    }
}


