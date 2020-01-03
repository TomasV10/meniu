package lt.bit.meniu.dto;

import lt.bit.meniu.entities.Product;

import java.time.LocalDate;
import java.util.List;

public class ClientDto {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate dateVisited;
    private List<Product> products;

    public ClientDto() {
    }

    public ClientDto(int id, String firstName, String lastName, LocalDate dateVisited, List<Product> products) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateVisited = dateVisited;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
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


}
