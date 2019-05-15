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
    private float priceIn;
    private float priceOut;
    private String details;
    private String imagePath;

    public Product() {
    }

    public Product(String name, float priceIn, float priceOut, String details, String imagePath) {
        this.name = name;
        this.priceIn = priceIn;
        this.priceOut = priceOut;
        this.details = details;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(float priceIn) {
        this.priceIn = priceIn;
    }

    public float getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(float priceOut) {
        this.priceOut = priceOut;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
