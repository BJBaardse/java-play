package models;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(query = "SELECT s FROM Orders s", name = "findAllOrders"),
        @NamedQuery(query = "SELECT s FROM Orders s WHERE s.id = :id", name = "findOneOrder")
})
public class Orders implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_id", unique = true)
    private int id;

    @Column(name = "createDate", nullable = false)
    private String createdDate;

    @Column(name = "plannedDate", nullable = false)
    private String plannedDate;

    @ManyToOne
    private User owner;

    @ManyToMany(mappedBy = "orderslist")
    private List<Product> productslist;


    public List<Product> getProductslist() {
        return productslist;
    }

    public void setProductslist(List<Product> productslist) {
        this.productslist = productslist;
    }

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

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(String plannedDate) {
        this.plannedDate = plannedDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
