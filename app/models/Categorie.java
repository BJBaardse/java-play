package models;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(query = "SELECT s FROM Categorie s", name = "findAllCategories"),
        @NamedQuery(query = "SELECT s FROM Categorie s WHERE s.id = :id", name = "findOneCategorie")
})
public class Categorie implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "categorie_id", unique = true)
    private int id;
    @Column(name = "categorie_naam", nullable = false)
    private String categorienaam;
    @Column(name = "categorie_omschrijving", nullable = false)
    private String Omschrijving;
    @OneToMany
    private List<Product> productsInCat;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategorienaam() {
        return categorienaam;
    }

    public void setCategorienaam(String categorienaam) {
        this.categorienaam = categorienaam;
    }

    public String getOmschrijving() {
        return Omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        Omschrijving = omschrijving;
    }

    public List<Product> getProductsInCat() {
        return productsInCat;
    }

    public void setProductsInCat(List<Product> productsInCat) {
        this.productsInCat = productsInCat;
    }
}
