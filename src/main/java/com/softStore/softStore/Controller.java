package com.softStore.softStore;

import com.softStore.softStore.Class.Articles;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    // Variable para manejar la interaccion con el archivo json
    private final jsonService jsonConnect = new jsonService();

    @GetMapping("/Articles")
    public  String showArticles(Model mod){
        try {
            // Traer archivos
            List<Articles> articulos = jsonConnect.getArticles();
            mod.addAttribute("listaArticulos",articulos);
        } catch (Exception e) {
            e.printStackTrace();
            mod.addAttribute("error","File not loaded");
        }
        return "pages/ArticlesPage";
    }

    @GetMapping("/search/{name}")
    public String searchArticles(@PathVariable String name, Model model) throws IOException{
        List<Articles> articles = new ArrayList<>();
        articles = jsonConnect.searchArticles(name);

        model.addAttribute("listaArticulos",articles);

        return "pages/ArticlesPage";
    }

    @GetMapping("/article/{id}")
    public String articleInfo(@PathVariable int id, Model model) throws IOException {
        Articles article = jsonConnect.searchArticleForId(id);
        if(article == null){
            return "pages/ArticlesPage";
        }
        model.addAttribute("articulo",article);

        return "pages/ArticleInfo";
    }

    @GetMapping("/add/{name}/{price}/{id}")
    public String addArticle(@PathVariable String name, @PathVariable double price, @PathVariable int id, Model model)throws IOException {
        Articles temp = new Articles(name,price,id);
        jsonConnect.addToShopCar(temp);
        return "pages/done";
    }

    @GetMapping("/delete/{name}")
    public String deleteArticle(@PathVariable String name, Model model)throws IOException {
        jsonConnect.deleteToShopCar(name);
        return "pages/done";
    }

    @GetMapping("/car")
    public String shopCar(Model model){
        try {
            List<Articles> lista = jsonConnect.getShopCar();
            model.addAttribute("listaArticulos",lista);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "pages/car";
    }

    @GetMapping("/chat")
    public String goToChat(Model model){
        return "pages/chat";
    }

}
