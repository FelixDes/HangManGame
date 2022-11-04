package HangMan.servlets;

import servlet.annotations.SimpleWebServlet;
import servlet.exeptions.SimpleServletException;
import servlet.http.HttpServletRequest;
import servlet.http.HttpServletResponse;
import servlet.http.SimpleHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

@SimpleWebServlet(name = "IdGenerator", value = "/GetId")
public class IdGeneratorServlet extends SimpleHttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws SimpleServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.write(UUID.randomUUID().toString());
        }
    }
}
