package com.example.mt.orderweed;

public class product_item
{
    int image,rate;
    String name,company,cbd,thc,price;

    public product_item(int image, int rate, String name, String company, String cbd, String thc,String price) {
        this.image = image;
        this.rate = rate;
        this.name = name;
        this.company = company;
        this.cbd = cbd;
        this.thc = thc;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCbd() {
        return cbd;
    }
    public String getPrice() {
        return price;
    }

    public void setCbd(String cbd) {
        this.cbd = cbd;
    }

    public String getThc() {
        return thc;
    }

    public void setThc(String thc) {
        this.thc = thc;
    }
}
