package by.academy.it.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@GenericGenerator(name = "PK", strategy = "increment")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "lowcost")
public class Extra extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "extra_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PK")
    Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private Integer cost;
    @ManyToMany(mappedBy = "extras")
    private Set<Ticket> tickets = new HashSet();

    public Extra() {
    }

    public Extra(String name, Integer cost) {

        this.name = name;
        this.cost = cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Extra{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}

