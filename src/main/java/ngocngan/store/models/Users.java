package ngocngan.store.models;

import javax.persistence.*;

/**
 * @author ngan nnh on 5/16/2019
 * @project sweet
 */
@Entity
@Table(name = "users")
public class Users {
    @Id @GeneratedValue @Column(name = "id") private Integer id;
    private String uuid;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private String city;
    private String address;
    private String image;

    public Users() {
    }

    public Users(String uuid, String email, String password, String firstName, String lastName, String phone,
            String city, String address, String image) {
        this.uuid = uuid;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.city = city;
        this.address = address;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
