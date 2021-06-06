package ncku.pd2final.Final.websocket;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;

@ServerEndpoint(value = "/websocket/renew")
@Component
public class Renew {   //for start new game

    private Session session;
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
        sendMessage(JSON.toJSONString(message));
    }


    public void sendMessage(String message)  {
        for(Session session: sessions) {
            session.getAsyncRemote().sendText(message);
        }
    }
}
