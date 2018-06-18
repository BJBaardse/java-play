package controllers;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jwt.JwtControllerHelper;
import jwt.VerifiedJwt;
import models.*;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import jwt.JwtControllerHelper;
import jwt.JwtValidator;
import jwt.VerifiedJwt;
import play.mvc.*;
import services.HibernateTest;
import services.UserService;
import views.html.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import play.libs.Json;
import play.mvc.Http.RequestBody;
import javax.persistence.criteria.Order;
import javax.inject.Inject;

import play.Logger;
import play.libs.Json;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import services.UserService;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class BerendsController extends Controller {
    @Inject
    private JwtControllerHelper jwtControllerHelper;



    public Result generateSignedToken() throws UnsupportedEncodingException {
        return ok("signed token: " + getSignedToken(5L));
    }
    private String getSignedToken(Long userId) throws UnsupportedEncodingException {
        String secret = "Berendsgrotegeheim";

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("ThePlayApp")
                .withClaim("user_id", userId)
                .withExpiresAt(Date.from(ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(10).toInstant()))
                .sign(algorithm);
    }
    public Result requiresJwt() {
        return jwtControllerHelper.verify(request(), res -> {
            if (res.left.isPresent()) {
                return forbidden(res.left.get().toString());
            }

            VerifiedJwt verifiedJwt = res.right.get();
            Logger.debug("{}", verifiedJwt);

            ObjectNode result = Json.newObject();
           result.put("ja", verifiedJwt.getUser());
            result.put("access", "granted");
            result.put("secret_data", "birds fly");
            return ok(result);
        });
    }

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


        Categorie cat = new Categorie();
        cat.setCategorienaam("brood");
        cat.setOmschrijving("vers gebakken brood!");


        Product item = new Product();
        item.setNaam("Tijgerbrood");
        item.setPrijs(12.11);
        test.create(item);
        ProductCustom item2 = new ProductCustom();
        item2.setNaam("Custom Tijgerbrood");
        item2.setPrijs(12.15);
        item2.setExtras("lmao extra is so extra");
        test.create(item2);


        Orders order = new Orders();
        order.setOwner(newuser);
        order.setCreatedDate("vandaag");
        order.setPlannedDate("morgen");
        List<Product> productslist = new ArrayList<>();
        productslist.add(item);
        order.setProductslist(productslist);
        test.create(order);


        List<Product> products = new ArrayList<>();
        products.add(item);
        cat.setProductsInCat(products);
        test.create(cat);




        return ok("ok");
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
