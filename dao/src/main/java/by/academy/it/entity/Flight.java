package by.academy.it.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@GenericGenerator(name = "PK", strategy = "increment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "lowcost")
public class Flight extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "flight_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "PK")
    private Integer id;
    @Column(name = "date")
    private String date;
    @Column(name = "seats")
    private int seats;
    @Column(name = "cost")
    private int cost;
    @Column(name = "up_cost")
    private byte upCost;
    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    private Set<Ticket> tickets;

    public Flight() {
    }

    public Flight(Integer id, String date, int seats, int cost, byte upCost) {

        this.id = id;
        this.date = date;
        this.seats = seats;
        this.cost = cost;
        this.upCost = upCost;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public int getSeats() {
        return seats;
    }

    public int getCost() {
        return cost;
    }

    public byte getUpCost() {
        return upCost;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setUpCost(byte upCost) {
        this.upCost = upCost;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", seats=" + seats +
                ", cost=" + cost +
                ", upCost=" + upCost +
                '}';
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + seats;
        result = 31 * result + cost;
        result = 31 * result + (int) upCost;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (seats != flight.seats) return false;
        if (cost != flight.cost) return false;
        if (upCost != flight.upCost) return false;
        return date.equals(flight.date);
    }
}

