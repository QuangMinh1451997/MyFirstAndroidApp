package models;

public class Product {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;
    // Store the name of the movie
    private double price;
    private double promotionPrice;
    private int amount;
    private int salePercent;
    private String description;

    public Product(){}

    public Product(String name, double price, double promotionPrice, int amount, int salePercent, String description) {
        this.name = name;
        this.price = price;
        this.promotionPrice = promotionPrice;
        this.amount = amount;
        this.salePercent = salePercent;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public double getPromotionPrice() {
        return promotionPrice;
    }
    public void setPromotionPrice(double name) {
        this.promotionPrice = promotionPrice;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int name) {
        this.amount = amount;
    }

    public int getSalePercent() {
        return salePercent;
    }
    public void setSalePercent(int salePercent) {
        this.salePercent = salePercent;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}