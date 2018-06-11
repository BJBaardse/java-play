package models;

import org.codehaus.jackson.annotate.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(query = "SELECT s FROM User s", name = "findAllUsers"),
        @NamedQuery(query = "SELECT s FROM User s WHERE s.id = :id", name = "findOneUser"),
        @NamedQuery(query = "DELETE from User WHERE id = :id", name = "deleteUserID" )
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private int id;

    @Column(name = "user_naam", nullable = false)
    private String naam;

    @Column(name = "user_telfoonnummer", nullable = false)
    private String telefoon;

    @Column(name = "user_adres", nullable = false)
    private String adres;

    @Column(name = "user_woonplaats", nullable = false)
    private String woonplaats;

    @OneToMany(mappedBy = "owner")
    private List<Orders> orders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(String phone) {
        this.telefoon = phone;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    @Override
    public String toString() {
        return id + "\t" + naam + "\t" + woonplaats;
    }
}