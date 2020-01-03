package lt.bit.meniu.dto;


import lt.bit.meniu.entities.Product;
import lt.bit.meniu.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductDto {

    private int id;
    private String name;
    private double price;
    private String description;
    private String preparation;
    private String type;
    private int clientId;

    public ProductDto() {
    }

    public ProductDto(int id, String name, double price, String description, String preparation, String type, int clientId) {
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}
