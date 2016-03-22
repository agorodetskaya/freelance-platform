package application.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission implements GrantedAuthority {

    @Id
    @Column(name = "permission_id")
    @GeneratedValue
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Permission(String name) {
        this.name = name;
    }

    public Permission() {
    }
}
