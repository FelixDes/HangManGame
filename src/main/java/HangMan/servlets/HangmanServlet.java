package HangMan.servlets;

import HangMan.model.GameInstance;
import HangMan.model.GameProcessor;
import org.di.annotations.Component;
import org.di.annotations.Inject;
import org.di.annotations.Singleton;
import servlet.annotations.SimpleWebServlet;
import servlet.http.HttpServletRequest;
import servlet.http.HttpServletResponse;
import servlet.http.SimpleHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

@Singleton
@Component
@SimpleWebServlet(name = "Hangman", value = "/Hangman")
public class HangmanServlet extends SimpleHttpServlet {
    @Inject
    private GameProcessor gp;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        GameInstance gameInstance = null;


        String id = request.getParameter("id");

        String letter = request.getParameter("letter");

        if (letter != null) {
            Character ch = letter.toCharArray()[0];
            gameInstance = gp.pushLetterForId(id, ch);

        }

        boolean reset = Boolean.parseBoolean(request.getParameter("reset"));
        if (reset) {
            gameInstance = gp.createOrResetForId(id);
        }

        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");

        if (gameInstance != null) {
            try (PrintWriter printWriter = response.getWriter()) {
                printWriter.write(gameInstance.toJSONObjectString());
            }
        } else {
            response.sendError(400, "No parameters");
        }
    }
}
