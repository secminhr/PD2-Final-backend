package ncku.pd2final.Final.websocket;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@ServerEndpoint(value = "/websocket/updateBlood")
@Component
public class UpdateBlood {
    private Session session;
    public double lat,lng;
    public int hp;

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
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        //intentionally empty
    }

    public void sendMessage(int hp, double lat, double lng)  {
        this.hp = hp;
        this.lat = lat;
        this.lng = lng;
        sendMessage(data());
    }

    public void sendMessage(String message){
        for(Session session: sessions) {
            session.getAsyncRemote().sendText(message);
        }
    }

    public String data(){
        Map<String, Object> sendData =  new HashMap<>();
        sendData.put("hp", hp);
        sendData.put("lat", lat);
        sendData.put("lng", lng);

        return JSON.toJSONString(sendData);
    }
}
