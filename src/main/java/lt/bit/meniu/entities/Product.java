package lt.bit.meniu.entities;

import lt.bit.meniu.dto.ProductDto;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "description")
    private String descriptions;
    @Column(name = "preparation")
    private String preparation;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ProductType type;
    @ManyToOne
    @JoinColumn(name="client_id", nullable=false)
    private Client client;

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public static enum ProductType{
        MEAT,
        POTATOES,
    }
    public ProductDto toDto(){
        return new ProductDto(
                id,
                name,
                price,
                descriptions,
                preparation,
                type.toString(),
                client.getId()
        );
    }
}
