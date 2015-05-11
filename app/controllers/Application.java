package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.HeaderWebSocket;
import model.ResponseWebSocket;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.*;

public class Application extends Controller {

    public static Result main(String any) {
        return ok(main.render());
    }

    public static Result responseCreate() {
        return ok(responseCreate.render());
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



    public static Result responses() {
        return ok(responses.render());
    }

    public static WebSocket<String> responseWS() {
        return new WebSocket<String>() {
            @Override
            public void onReady(In<String> in, Out<String> out) {
                ResponseWebSocket.start(in, out);
            }
        };
    }

    public static WebSocket<String> headerWS() {
        return new WebSocket<String>() {
            @Override
            public void onReady(In<String> in, Out<String> out) {
                HeaderWebSocket.start(in, out);
            }
        };
    }

    public static Result success() {
        return ok(success.render());
    }
}
