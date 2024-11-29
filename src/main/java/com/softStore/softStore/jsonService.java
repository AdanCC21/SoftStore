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
    private final ObjectMapper obj = new ObjectMapper();

    private final String dataBase = "src/main/resources/articles.json";
    private final String shopCar = "src/main/resources/carrito.json";


    public void saveJson(List<Articles> articles) throws IOException{
        obj.writerWithDefaultPrettyPrinter().writeValue(new File(dataBase),articles);
    }

    public void saveShopCar(List<Articles> articles) throws IOException{
        obj.writerWithDefaultPrettyPrinter().writeValue(new File(shopCar),articles);
    }

    public List<com.softStore.softStore.Class.Articles> getArticles() throws IOException{
        File bd = new File(dataBase);
        if(!bd.exists()){
            return new ArrayList<>();
        }
        return obj.readValue(bd, new TypeReference<List<Articles>>() {});
    }

    public List<com.softStore.softStore.Class.Articles> getShopCar()throws IOException{
        File car = new File(shopCar);
        if(!car.exists()){
            return new ArrayList<>();
        }
        return obj.readValue(car, new TypeReference<List<Articles>>() {});
    }

    public void addValue(Articles newArticle) throws IOException {
        List<Articles> articlesList = getShopCar();
        articlesList.add(newArticle);
        saveShopCar(articlesList);
    }

    public boolean deleteValue(String name) throws  IOException{
        List<Articles> articlesList = getShopCar();
        boolean done = false;
        for( Articles ar : articlesList){
          if(ar.getName().equals(name)){
              articlesList.remove(ar);
              saveShopCar(articlesList);
              done=true;
              break;
          }
        }
        return done;
    }

    public com.softStore.softStore.Class.Articles searchArticle(String name) throws IOException {
        List<Articles> list = getArticles();
        for(Articles temp : list){
            if(temp.getName().equals(name)){
                return temp;
            }
        }
        return new Articles("ADAA",20);
    }
}