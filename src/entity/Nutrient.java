package entity;

public class Nutrient {
    private String name;
    private Float amount;
    private String type;
    public Nutrient(String name, Float amount, String type) {
        this.name = name;
        this.amount = amount;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Float getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

}
