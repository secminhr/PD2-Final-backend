package ncku.pd2final.Final.auth.authconfig;

import org.springframework.security.web.RedirectStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Used in {@link AuthConfiguration} and {@link JsonUsernamePasswordFilter} to provide the redirect behaviour of "No Redirection"
 * @author secminhr
 */
public class NoRedirect implements RedirectStrategy {

    private final Handler handler;

    /**
     * A convenient constructor for not using handler
     */
    public NoRedirect() {
        this(null);
    }

    /**
     * Constructor with a handler.
     * The handler is called when this strategy received a sendRedirect call.
     * @param handler the handler to use
     */
    public NoRedirect(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        //don't redirect
        if (handler != null) {
            handler.onReceive(request, response);
        }
    }

    /**
     * Delegate the behaviour to outside when receiving sendRedirect
     *
     * @author secminhr
     */
    @FunctionalInterface
    public interface Handler {
        void onReceive(HttpServletRequest request, HttpServletResponse response) throws IOException;
    }
}
