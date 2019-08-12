package vn.topica.itlab4.exercises.hieu_trainer.filter_pattern;

public class Drink {
    private int price;
    private String name;
    private Type type;

    protected enum Type{
        COCA,
        PEPSI,
        BOHUC
    }

    public Drink() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Drink(int price, String name, Type type) {
        this.price = price;
        this.name = name;
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
