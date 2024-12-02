package com.softStore.softStore.Class;

public class Articles {
    public String name;
    public double price;
    public int id;

    public Articles(String name, double price, int id) {
        this.name = name;
        this.price = price;
        this.id = id;
    }

    public Articles() {
        this.name = "";
        this.price = 0.0;
        this.id = 0;
    }

    public int getId() { return id;}

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
