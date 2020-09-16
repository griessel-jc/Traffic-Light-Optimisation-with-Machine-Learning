/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebSocketEndpoint;

import com.aegis.aegis.controller.UserController; 
import com.google.gson.Gson;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.util.HashSet;
import java.util.Set;

public class WebsocketServer extends WebSocketServer {

    private static int TCP_PORT = 4444;
    private static UserController userController;
    private static Gson gson = new Gson();
    private static Set<WebSocket> conns = new HashSet<>();
    public WebsocketServer() {
       // super(new InetSocketAddress(TCP_PORT));
        
    }

    @Override
    public void onOpen(WebSocket ws, ClientHandshake handshake) {
       
        //this.onWebsocketOpen(conn,handshake);
        conns.add(ws);
        ws.send("");
        System.out.println("New connection from " + ws.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        conns.remove(conn);
        //this.onWebsocketClose(conn, code, reason, remote);
        System.out.println("Closed connection to " + conn.getRemoteSocketAddress().getAddress().getHostAddress());
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        
        System.out.println("Message from client: " + message);
        for (WebSocket sock : conns) {
            sock.send(message);
        }
    }
    
    
     
    
    @Override
    public void onError(WebSocket conn, Exception ex) {
        //ex.printStackTrace();
        if (conn != null) {
            conns.remove(conn);
            // do some thing if required
        }
       
    }
    
    
    
    @Override
    public void onStart() {
        
    }
}