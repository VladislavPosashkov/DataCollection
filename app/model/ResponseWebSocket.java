package model;


import play.libs.F;
import play.mvc.WebSocket;

import java.util.ArrayList;
import java.util.List;

public class ResponseWebSocket {

    private static List<WebSocket.Out<String>> connections = new ArrayList<WebSocket.Out<String>>();


    public static void start(WebSocket.In<String> in, WebSocket.Out<String> out) {

        connections.add(out);

        in.onClose(new F.Callback0() {
            @Override
            public void invoke() throws Throwable {
                connections.remove(out);
            }
        });
    }

    public static void notifyListeners(String message) {

        for (WebSocket.Out<String> listener : connections) {
            listener.write(message);
        }
    }
}