package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.*;


public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static Result main(String any) {
        return ok(main.render());
    }

    public static Result fieldsList() {
        return ok(fieldsList.render());
    }

    public static Result fieldEdit() {
        return ok(fieldEdit.render());
    }

    public static Result fieldCreate() {
        return ok(fieldCreate.render());
    }
}
