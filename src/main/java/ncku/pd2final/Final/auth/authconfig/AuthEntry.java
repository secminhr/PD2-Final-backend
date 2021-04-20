package ncku.pd2final.Final.auth.authconfig;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The entry point used when a login request failed or when receiving a unauthorized request. <br>
 * One can modify this class to change the response for above situation. <br>
 * This is automatically detected and used in {@link AuthConfiguration}.
 * @author secminhr
 */
@Component
public class AuthEntry implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String responseMessage = "{\"success\": false, \"auth\":\"/login\"";
        if (request.getServletPath().equals("/error")) {
            //login failed
            responseMessage += ", \"message\":\"login failed\"";
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(responseMessage + "}");
        response.flushBuffer();
    }
}
