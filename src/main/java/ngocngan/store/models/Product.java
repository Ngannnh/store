package ngocngan.store.models;

import javax.persistence.*;

/**
 * @author ngan nnh on 5/13/2019
 * @project sweet
 */
@Entity
@Table(name = "product")
public class Product {
    @Id @GeneratedValue @Column(name = "id") private Integer id;
    private String name;
    private int price;
    private String details;

    public Product() {
    }

    public Product(String name, int price, String details) {
        this.name = name;
        this.price = price;
        this.details = details;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
