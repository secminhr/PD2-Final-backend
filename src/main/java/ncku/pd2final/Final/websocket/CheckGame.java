package ncku.pd2final.Final.websocket;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@ServerEndpoint(value = "/websocket/checkgame")
@Component
public class CheckGame {

    private Session session;
    public double[] message = new double[3]; //回傳 -1 -1 -1
    private static final ArrayList<Session> sessions = new ArrayList<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("連線已經開啟");
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("連線已經關閉");
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable error, Session session) {
        System.out.println("連線發生錯誤");
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {

    }

    public void sendMessage(double[] getdata)  {
        Map<String, Object> sendData =  new HashMap<>();
        sendData.put("gameend",getdata);
        sendMessage(JSON.toJSONString(sendData));
    }

    public void sendMessage(String message)  {
        for(Session session: sessions) {
            session.getAsyncRemote().sendText(message);
        }
    }

}
