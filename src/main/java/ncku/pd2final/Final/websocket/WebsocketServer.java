package ncku.pd2final.Final.websocket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.*;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;




@ServerEndpoint(value = "/websocket")
@Component
public class WebsocketServer {
    private Session session;
    private String lat,lng,hp = "";

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
        System.out.println("從使用者端收到的訊息：" + message);
        JSONObject jsonObject = JSON.parseObject(message);
        String hp = jsonObject.getString("hp");
        String lat = jsonObject.getString("lat");
        String lng = jsonObject.getString("lng");

        Map<String, Object> sendData =  new HashMap<>();
        sendData.put("hp", hp);
        sendData.put("lat", lat);
        sendData.put("lng", lng);

        sendMessage(JSON.toJSONString(sendData));
    }

    public void sendMessage(String message)  {
        this.session.getAsyncRemote().sendText(message);
    }
}
