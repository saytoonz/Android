package com.nsromapa.android.tab2pakage;

/**
 * Created by SAY on 20/08/2018 at Nsromapa Goaso.
 */

public class Product {
    private int id,image;
    private String title, shortdesc;
    private double rating;
    private double price;


    public Product(int id, String title, String shortdesc, double rating, double price, int image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.rating = rating;
        this.price = price;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public double getRating() {
        return rating;
    }

    public double getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }
}
