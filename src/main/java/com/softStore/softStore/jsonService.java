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

    public void saveShopCar(List<Articles> articles) throws IOException{
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

    public List<Articles> getShopCar()throws IOException{
        File car = new File(shopCarRoute);
        if(!car.exists()){
            return new ArrayList<>();
        }
        return objectMap.readValue(car, new TypeReference<List<Articles>>() {});
    }
    // ----------------------------------------------------------------------------------------


    public void addToShopCar(Articles newArticle) throws IOException {
        List<Articles> articlesList = getShopCar();
        articlesList.add(newArticle);
        saveShopCar(articlesList);
    }

    public boolean deleteToShopCar(String name) throws  IOException{
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

/*
    public Articles searchArticle(String name) throws IOException {
        List<Articles> list = getArticles();
        for(Articles temp : list){
            if(temp.getName().equals(name)){
                return temp;
            }
        }
        return new Articles("Default",20,-1);
    }

 */

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
    public Articles searchArticleForId(int id)throws IOException{
        List<Articles> dataBase = getArticles();
        for( var temp : dataBase){
            if(temp.id == id){
                return temp;
            }
        }
        return null;
    }
}