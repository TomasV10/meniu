package lt.bit.meniu.entities;



import lt.bit.meniu.dto.ClientDto;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "klientai")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_visited")
    private LocalDate dateVisited;
    @OneToMany(mappedBy = "client",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Product> products;


    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateVisited() {
        return dateVisited;
    }

    public void setDateVisited(LocalDate dateVisited) {
        this.dateVisited = dateVisited;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public ClientDto clientDto(){
        return new ClientDto(
            id,
            firstName,
            lastName,
            dateVisited,
            products
        );
    }
}
