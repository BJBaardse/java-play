package controllers;

import models.Categorie;
import models.Orders;
import models.Product;
import models.User;
import play.mvc.*;
import services.HibernateTest;
import services.UserService;
import views.html.*;

import java.util.List;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import javax.inject.Inject;
import javax.persistence.criteria.Order;

import play.libs.Json;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import services.UserService;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class BerendsController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result persoon(String id) {
        return ok(id);
    }
    public Result persoonint(int id) {
        return ok("Hallo: " + Integer.toString(id));
    }
    public Result addup(String firstname, String lastname){
        StringBuilder a = new StringBuilder();
        a.append(firstname).append(lastname);
        String fullname = a.toString();
        return ok("Volledige naam: " + fullname);
    }
    public Result test(){
        User newuser = new User();
        newuser.setWoonplaats("Geldermalsen");
        newuser.setAdres("Sterappel 28");
        newuser.setNaam("Berend Baardse");
        newuser.setTelefoon("0610234376");
        HibernateTest test = new HibernateTest();
        test.create(newuser);

        Orders order = new Orders();
        order.setOwner(newuser);
        order.setCreatedDate("vandaag");
        order.setPlannedDate("morgen");
        test.create(order);

        Categorie cat = new Categorie();
        cat.setCategorienaam("brood");
        cat.setOmschrijving("vers gebakken brood!");


        Product item = new Product();
        item.setNaam("Tijgerbrood");
        item.setPrijs(12.11);
        test.create(item);
        cat.setProductsInCat(cat.getProductsInCat().add(item));
        test.create(cat);

        return ok("user created?");
    }
    public Result adduser(String naam){
        User newuser = new User();
        newuser.setWoonplaats("Geldermalsen");
        newuser.setAdres("Sterappel 28");
        newuser.setNaam(naam);
        newuser.setTelefoon("0610234376");
        HibernateTest test = new HibernateTest();
        test.create(newuser);
        return ok(Json.toJson(newuser));
    }
    public Result deleteUserID(){
        com.fasterxml.jackson.databind.JsonNode json = request().body().asJson();
        int userid = json.get("id").asInt();
        HibernateTest test = new HibernateTest();
        test.deleteUser(json.get("id").asInt());
        return ok(String.valueOf(userid));
    }
    public Result main() {
        return ok("nice");
    }

}
