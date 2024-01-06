package projekt.book.store.controllers;

import org.springframework.ui.Model;
import projekt.book.store.session.SessionData;

public class ModelUtils {


    public static void addCommonDataToModel(Model model, SessionData sessionData) {
        model.addAttribute("logged", sessionData.isLogged());
        model.addAttribute("admin", sessionData.isAdmin());
    }

}
