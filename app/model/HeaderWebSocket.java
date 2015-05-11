package model;

import play.libs.F;
import play.mvc.WebSocket;

import java.util.ArrayList;
import java.util.List;

public class HeaderWebSocket {
    private static List<WebSocket.Out<String>> connections = new ArrayList<WebSocket.Out<String>>();


    public static void start(WebSocket.In<String> in, WebSocket.Out<String> out) {

        connections.add(out);

        in.onMessage(new F.Callback<String>() {
            @Override
            public void invoke(String s) throws Throwable {
                System.out.println("Header: New connection");
            }
        });
    }

    public static void notifyListeners() {

        for (WebSocket.Out<String> listener : connections) {
            listener.write("New responses");
        }
    }
}
