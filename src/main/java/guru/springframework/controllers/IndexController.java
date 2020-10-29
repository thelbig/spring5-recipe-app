package guru.springframework.controllers;

import guru.springframework.services.RecipeServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private RecipeServiceImpl recipeServiceImpl;

    public IndexController(RecipeServiceImpl recipeServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
    }

    @RequestMapping({"","/","/index","/index.html"})
    public String indexAction(Model model) {
        model.addAttribute("recipes", recipeServiceImpl.getRecipes());

        return "index";
    }
}
