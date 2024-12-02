package com.softStore.softStore;

import com.softStore.softStore.Class.Articles;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class jsonService {
    // Json - > Java && Java - > Json
    private final ObjectMapper objectMap = new ObjectMapper();

    private final String dataBaseRoute = "src/main/resources/articles.json";
    private final String shopCarRoute = "src/main/resources/carrito.json";


    // ----------------------------------------------------------------------------------------
    public void saveJson(List<Articles> articles) throws IOException{
        objectMap.writerWithDefaultPrettyPrinter().writeValue(new File(dataBaseRoute),articles);
    }

    public void saveCart(List<Articles> articles) throws IOException{
        objectMap.writerWithDefaultPrettyPrinter().writeValue(new File(shopCarRoute),articles);
    }
    // ----------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------
    public List<Articles> getArticles() throws IOException{
        File bd = new File(dataBaseRoute);
        if(!bd.exists()){
            return new ArrayList<>();
        }
        return objectMap.readValue(bd, new TypeReference<List<Articles>>() {});
    }

    public List<Articles> getCart()throws IOException{
        File car = new File(shopCarRoute);
        if(!car.exists()){
            return new ArrayList<>();
        }
        return objectMap.readValue(car, new TypeReference<List<Articles>>() {});
    }
    // ----------------------------------------------------------------------------------------


    public void addToCart(Articles newArticle) throws IOException {
        List<Articles> articlesList = getCart();
        articlesList.add(newArticle);
        saveCart(articlesList);
    }

    public boolean deleteFromCart(int id) throws  IOException{
        List<Articles> articlesList = getCart();
        boolean done = false;
        for( Articles ar : articlesList){
          if(ar.id == id){
              articlesList.remove(ar);
              saveCart(articlesList);
              done=true;
              break;
          }
        }
        return done;
    }

    public List<Articles> searchArticles(String name) throws IOException {
        List<Articles> list = getArticles();
        List<Articles> output = new ArrayList<>();

        for(Articles temp : list){
            if(temp.getName().equals(name)){
                output.add(temp);
            }
        }
        return output;
    }
    public Articles searchArticleById(int id)throws IOException{
        List<Articles> dataBase = getArticles();
        for( var temp : dataBase){
            if(temp.id == id){
                return temp;
            }
        }
        return null;
    }
}