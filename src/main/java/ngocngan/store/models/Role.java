package ngocngan.store.models;

import javax.persistence.*;

/**
 * @author ngan nnh on 5/16/2019
 * @project sweet
 */
@Entity
@Table(name = "role")
public class Role {
    @Id @GeneratedValue @Column(name = "id") private Integer id;
    private String uuid;
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
