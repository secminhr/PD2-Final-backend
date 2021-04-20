package ncku.pd2final.Final.auth.authconfig;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ncku.pd2final.Final.auth.endpoints.AuthController;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.Filter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A {@link Filter} for our custom json login payload.
 *
 * @author secminhr
 */
public class JsonUsernamePasswordFilter extends UsernamePasswordAuthenticationFilter {

    public JsonUsernamePasswordFilter(AuthenticationManager manager) {
        super();
        setAuthenticationManager(manager);
        //require /login be used with POST
        setRequiresAuthenticationRequestMatcher(
            new AntPathRequestMatcher(AuthController.LOGIN_ENDPOINT, HttpMethod.POST.name())
        );
        //make successful login return HTTP_OK rather than redirection
        SavedRequestAwareAuthenticationSuccessHandler handler = new SavedRequestAwareAuthenticationSuccessHandler();
        handler.setRedirectStrategy(new NoRedirect((request, response) -> {
            response.getWriter().write("{\"success\": true, \"info\":\"" + AuthController.INFO_ENDPOINT + "\"}");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
        }));
        setAuthenticationSuccessHandler(handler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String body = getRequestBody(request);
        Map<String, String> map = convertJsonToMap(body);
        String username, password;
        try {
            username = map.get("username");
            password = map.get("password");
        } catch (ClassCastException | NullPointerException e) {
            throw new AuthenticationException("Incorrect arguments") {};
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        return getAuthenticationManager().authenticate(token);
    }

    private String getRequestBody(HttpServletRequest request) {
        InputStream stream;
        try {
            stream = request.getInputStream();
        } catch (IOException e) {
            throw new AuthenticationException("Cannot open input stream") {};
        }
        BufferedReader buffer = new BufferedReader(new InputStreamReader(stream));
        return buffer.lines().collect(Collectors.joining());
    }

    private Map<String, String> convertJsonToMap(String json) {
        try {
            return new ObjectMapper().readValue(
                    json.getBytes(StandardCharsets.UTF_8),
                    new TypeReference<Map<String, String>>() {}
            );
        } catch (IOException e) {
            throw new AuthenticationException("Cannot read json") {};
        } catch (JsonParseException e) {
            throw new AuthenticationException("Bad json format") {};
        }
    }
}