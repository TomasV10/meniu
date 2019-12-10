package lt.bit.meniu.dto;

public class ProductDto {
    private int id;
    private String name;
    private double price;
    private String description;
    private String preparation;
    private String type;
    private long clientId;

    public ProductDto() {
    }

    public ProductDto(int id, String name, double price, String description, String preparation, String type, long clientId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.preparation = preparation;
        this.type = type;
        this.clientId = clientId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }
}
