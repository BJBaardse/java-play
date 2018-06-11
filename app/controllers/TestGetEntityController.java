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


public class TestGetEntityController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result getUser(int id){
        User a = HibernateTest.getUser(id);
        return ok(Json.toJson(a));
    }
    public Result getCategorie(int id){
        Categorie a = HibernateTest.getCategorie(id);
        return ok(Json.toJson(a));
    }
    public Result getOrder(int id){
        Orders a = HibernateTest.getOrder(id);
        return ok(Json.toJson(a));
    }
    public Result getProduct(int id){
        Product a = HibernateTest.getProduct(id);
        return ok(Json.toJson(a));
    }


}
