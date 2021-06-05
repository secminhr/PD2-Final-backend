package ncku.pd2final.Final.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.*;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/websocket/updateBlood")
@Component
public class UpdateBlood {
    private Session session;
    public double lat,lng;
    public int hp;

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        System.out.println("連線已經開啟");
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("連線已經關閉");
    }

    @OnError
    public void onError(Throwable error, Session session) {
        System.out.println("連線發生錯誤");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        JSONObject jsonObject = JSON.parseObject(message);

        double lat = jsonObject.getDouble("lat"); //緯度
        double lng = jsonObject.getDouble("lng"); //經度
        int hp = jsonObject.getInteger("hp"); //血量

        Map<String, Object> sendData =  new HashMap<>();
        sendData.put("lat", lat);
        sendData.put("lng", lng);
        sendData.put("hp", hp);


        sendMessage(JSON.toJSONString(sendData));
    }

    public void sendMessage(double lat, double lng, int hp)  {
        this.lat = lat;
        this.lng = lng;
        this.hp = hp;
    }
    public void sendMessage(String message){
        this.session.getAsyncRemote().sendText(message);
    }

}
