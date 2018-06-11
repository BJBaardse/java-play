package models;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(query = "SELECT s FROM Product s", name = "findAllProducts"),
        @NamedQuery(query = "SELECT s FROM Product s WHERE s.id = :id", name = "findOneProduct")
})
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "product_id", unique = true)
    private int id;

    @Column(name = "product_naam", nullable = false)
    private String naam;

    @Column(name = "product_prijs", nullable = false)
    private double prijs;

    @Override
    public String toString() {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

}
