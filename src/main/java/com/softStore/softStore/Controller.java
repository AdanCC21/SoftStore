package com.softStore.softStore;

import com.softStore.softStore.Class.Articles;
import com.softStore.softStore.Class.User;
import com.softStore.softStore.Class.Const;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {

    Const ct = new Const();

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
        Articles article = jsonConnect.searchArticleById(id);
        if(article == null){
            return "pages/ArticlesPage";
        }
        model.addAttribute("articulo",article);

        return "pages/ArticleInfo";
    }

    @GetMapping("/add/{name}/{price}/{id}")
    public String addArticle(@PathVariable String name, @PathVariable double price, @PathVariable int id, Model model)throws IOException {
        Articles temp = new Articles(name,price,id);
        jsonConnect.addToCart(temp);
        return "pages/done";
    }

    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable int id, Model model)throws IOException {
        jsonConnect.deleteFromCart(id);
        return "pages/done";
    }

    @GetMapping("/car")
    public String cart(Model model){
        try {
            List<Articles> lista = jsonConnect.getCart();
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

    @MessageMapping("/sendMessage") // Cliente envía aquí los mensajes
    @SendTo("/topic/messages") // Mensajes retransmitidos a todos los suscriptores
    public String handleMessage(String message) {
        return message; // Simplemente retransmite el mensaje recibido
    }

    @GetMapping("/buy")
    public String sendToBuy(Model model){
        User user = ct.getCurrentUser();

        model.addAttribute("currentUser", user);

        return "pages/buy";
    }

    @GetMapping("/login")
    public String sendToLogin(){
        return "pages/login";
    }

    @GetMapping("/validate/{email}/{password}")
    public String validateUser(@PathVariable String email, @PathVariable String password, Model model) throws IOException {
        User user = jsonConnect.searchUser(email,password);
        if(user != null){
            ct.setCurrentUser(user);
            return "redirect:/Articles";
        }
        return "redirect:/login";
    }

    @GetMapping("/pay/{country}/{city}/{zipCode}/{street}/{houseNumber}/{additionalDescription}")
    public String pay(@PathVariable String country, @PathVariable String city, @PathVariable String zipCode, @PathVariable String street,
                      @PathVariable String houseNumber, @PathVariable String additionalDescription) throws IOException {
        User user = ct.getCurrentUser();
        user.setAddress(country,city,zipCode,street,houseNumber,additionalDescription);

        List<User> userList = jsonConnect.getUsers();

        for(int i =0; i < userList.size(); i++){
            if(userList.get(i).email.equals(user.email)){
                userList.set(i, user);
            }
        }

        jsonConnect.saveJson(userList);

        return "redirect:/Articles";
    }
}
