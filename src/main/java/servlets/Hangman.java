package servlets;

import model.HangManModel;
import org.json.JSONObject;
import servlet.annotations.SimpleWebServlet;
import servlet.exeptions.SimpleServletException;
import servlet.http.HttpServletRequest;
import servlet.http.HttpServletResponse;
import servlet.http.SimpleHttpServlet;

import java.io.IOException;
import java.io.PrintWriter;

@SimpleWebServlet(name = "Hangman", value = "/Hangman")
public class Hangman extends SimpleHttpServlet {
    HangManModel m = new HangManModel();

    @Override
    public void init() throws SimpleServletException {
        try {
            m.reset();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");

        Character letter = request.getParameter("letter").toCharArray()[0];
        m.pushLetter(letter);

        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.write(gameState().toString());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        m.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");

        try (PrintWriter printWriter = response.getWriter()) {
            printWriter.write(gameState().toString());
        }
    }

    private JSONObject gameState() {
        JSONObject obj = new JSONObject();
        obj.put("letters", m.getCurrentLetters());
        obj.put("state", m.getState());
        obj.put("winFlag", m.isWinFlag());
        obj.put("alreadyTried", m.getAlreadyTried());
        return obj;
    }
}
