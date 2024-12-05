package com.softStore.softStore;

import com.softStore.softStore.Class.Articles;
import com.softStore.softStore.Class.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class jsonService {
    // Json - > Java && Java - > Json
    private final ObjectMapper objectMap = new ObjectMapper();

    private final String dataBaseRoute = "src/main/resources/articles.json";
//    private final String shopCarRoute = "src/main/resources/carrito.json";
    private final String usersRoute = "src/main/resources/users.json";
    private final String baseRoute = "src/main/resources/";

    // ----------------------------------------------------------------------------------------
    public List<Articles> getArticles() throws IOException{
        File bd = new File(dataBaseRoute);
        if(!bd.exists()){
            return new ArrayList<>();
        }
        return objectMap.readValue(bd, new TypeReference<List<Articles>>() {});
    }

    public void saveCart(List<Articles> articles, String userEmail) throws IOException{
        String rute = baseRoute + userEmail+".json";
        objectMap.writerWithDefaultPrettyPrinter().writeValue(new File(rute),articles);
    }

    public List<Articles> getCart(String userEmail)throws IOException{
        String rute = baseRoute + userEmail+".json";
        File car = new File(rute);

        if(!car.exists()){
            return new ArrayList<>();
        }
        return objectMap.readValue(car, new TypeReference<List<Articles>>() {});
    }
    // ----------------------------------------------------------------------------------------


    public void addToCart(Articles newArticle, String userEmail) throws IOException {
        List<Articles> articlesList = getCart(userEmail);
        articlesList.add(newArticle);
        saveCart(articlesList, userEmail);
    }

    public boolean deleteFromCart(int id, String userEmail) throws  IOException{
        List<Articles> articlesList = getCart(userEmail);
        boolean done = false;
        for( Articles ar : articlesList){
          if(ar.id == id){
              articlesList.remove(ar);
              saveCart(articlesList,userEmail);
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

    public List<User> getUsers()throws IOException{
        File usersFile = new File(usersRoute);
        if(!usersFile.exists()){
            return null;
        }
        return objectMap.readValue(usersFile, new TypeReference<List<User>>() {});
    }

    public User searchUser(String email, String password) throws IOException{
        List<User> users = getUsers();
        if(users != null){
            for(var temp : users){
                if(temp.email.equals(email)){
                    if(temp.password.equals(password)){
                        return temp;
                    }
                }
            }
        }
        return null;
    }

    public void saveJson(List<User> userList) throws IOException{
        objectMap.writerWithDefaultPrettyPrinter().writeValue(new File(usersRoute),userList);
    }
}