package HangMan.servlets;

import org.di.annotations.Component;
import org.di.annotations.Singleton;
import servlet.annotations.SimpleWebServlet;
import servlet.http.HttpServletRequest;
import servlet.http.HttpServletResponse;
import servlet.http.SimpleHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@Singleton
@Component
@SimpleWebServlet(name = "IdGenerator", value = "/GetId")
public class IdGeneratorServlet extends SimpleHttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.write(UUID.randomUUID().toString());
        }
    }
}
