package by.academy.it.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@GenericGenerator(name = "PK", strategy = "increment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "lowcost")
public class User extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PK")
    private Integer id;
    @Column(name = "login")
    private String login;
    @Column(name = "pass")
    private String password;
    @Column(name = "role")
    private String userRole;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        userRole = "user";
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}

