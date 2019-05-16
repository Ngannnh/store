package ngocngan.store.models;

import javax.persistence.*;

/**
 * @author ngan nnh on 5/13/2019
 * @project sweet
 */
@Entity
@Table(name = "role")
public class Role {
    @Id @GeneratedValue @Column(name = "id") private Integer id;
    private String role;

    public Role() {
    }

    public Role(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
