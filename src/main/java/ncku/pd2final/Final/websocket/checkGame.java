package ncku.pd2final.Final.websocket;

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
public class checkGame {

    private Session session;
    public String message ; //回傳 -1 -1 -1


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
    this.message = message;
        System.out.println("遊戲已結束");
        sendMessage(JSON.toJSONString(message));
    }

    public void sendMessage(String message)  {
        this.session.getAsyncRemote().sendText(message);

    }

}
