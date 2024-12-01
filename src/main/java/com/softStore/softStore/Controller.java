package com.softStore.softStore;

import com.softStore.softStore.Class.Articles;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    // Variable para manejar la interaccion con el archivo json
    private final jsonService jsonConnect = new jsonService();

    // Constructor
//    public Controller(jsonService json){
//        this.jsonConnect = json;
//    }

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
    public String searchArticle(@PathVariable String name, Model model) throws IOException {
        Articles article = jsonConnect.searchArticle(name);
        if(article == null){
            model.addAttribute("articulo",new Articles("error",9.0));
        }else{
            model.addAttribute("articulo",article);
        }
        double pr = article.getPrice();

        String url = "/article/" + article.getName() + "/" + pr;

        // Redirigir a la URL deseada con los parámetros
        return "redirect:"+url;
    }

    @GetMapping("/article/{name}/{price}")
    public String articleInfo(@PathVariable String name,@PathVariable double price,Model model){
        Articles artSelected = new Articles(name,price);
        model.addAttribute("articulo",artSelected);
        return "pages/ArticleInfo";
    }

    @GetMapping("/add/{name}/{price}")
    public String addArticle(@PathVariable String name, @PathVariable double price, Model model)throws IOException {
        Articles temp = new Articles(name,price);
        jsonConnect.addValue(temp);
        return "pages/done";
    }

    @GetMapping("/delete/{name}")
    public String deleteArticle(@PathVariable String name, Model model)throws IOException {
        jsonConnect.deleteValue(name);
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
