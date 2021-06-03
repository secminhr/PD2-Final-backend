package ncku.pd2final.Final.websocket;
import ncku.pd2final.Final.tower.CalculateBlood;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/websocket/checkgame")
@Component
public class checkGame extends CalculateBlood {

    private Session session;
    private double message;


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
    public void onMessage(double message, Session session) {
    this.message = message;
        sendMessage(JSON.toJSONString(message));
    }

    public void sendMessage(String message)  {
        this.session.getAsyncRemote().sendText(message);

    }

}
