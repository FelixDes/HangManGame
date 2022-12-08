package HangMan.db.dao;

import HangMan.db.connector.DBControllerH2;
import HangMan.db.storage.MockStorage;
import HangMan.model.GameInstance;
import org.apache.commons.lang3.ArrayUtils;
import org.di.annotations.Component;
import org.di.annotations.Inject;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GameInstanceDAO implements DAO<GameInstance> {
    @Inject
    private DBControllerH2 dbControllerH2;

    @Override
    public Map<String, GameInstance> getAll() {
        Map<String, GameInstance> map = new HashMap<>();
        try (var rs = dbControllerH2.executeQuery("select * from GAME_INSTANCE;")) {
            while (rs.next()) {
                map.put(rs.getString("id"), new GameInstance(
                        rs.getString("word"),
                        stringToCurrentLetters(rs.getString("currentLetters")),
                        rs.getInt("deathState"),
                        rs.getInt("winFlag"),
                        rs.getString("alreadyTried").chars().mapToObj(c -> Character.valueOf((char) c)).collect(Collectors.toList())
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    public GameInstance getForId(String id) {
        GameInstance gameInstance = null;
        try (var rs = dbControllerH2.executeQuery("select * from GAME_INSTANCE where id = '" + id + "'")) {
            if (rs.next()) {
                gameInstance = new GameInstance(
                        rs.getString("word"),
                        stringToCurrentLetters(rs.getString("currentLetters")),
                        rs.getInt("deathState"),
                        rs.getInt("winFlag"),
                        rs.getString("alreadyTried").chars().mapToObj(c -> (char) c).collect(Collectors.toList())
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return gameInstance;
    }

//    @Override
    private void save(String id, GameInstance gi) {
        String insert = "insert into game_instance (id, word, currentLetters, deathState, winFlag, alreadyTried) values (?, ?, ?, ?, ?, ?);";
        try (var st = dbControllerH2.prepareStatement(insert)) {
            st.setString(1, id);
            st.setString(2, gi.getWord());
            st.setString(3, currentLettersToString(gi.getCurrentLetters()));
            st.setInt(4, gi.getDeathState());
            st.setInt(5, gi.getWinFlag().getVal());
            st.setString(6, gi.getAlreadyTried().stream().map(String::valueOf).collect(Collectors.joining()));

            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        try (var rs = dbControllerH2.executeQuery("delete from game_instance where id = '" + id + "'")) {
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String id, GameInstance gi) {
        if (!getAll().containsKey(id)) {
            save(id, gi);
        } else {
            String update = "update game_instance set " +
                    "word = ?, " +
                    "currentLetters = ?, " +
                    "deathState = ?, " +
                    "winFlag = ?, " +
                    "alreadyTried = ? " +
                    "where id = ?";
            try (var st = dbControllerH2.prepareStatement(update)) {
                st.setString(1, gi.getWord());
                st.setString(2, currentLettersToString(gi.getCurrentLetters()));
                st.setInt(3, gi.getDeathState());
                st.setInt(4, gi.getWinFlag().getVal());
                st.setString(5, gi.getAlreadyTried().stream().map(String::valueOf).collect(Collectors.joining()));
                st.setString(6, id);
                st.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String currentLettersToString(Character[] currentLetters) {
        StringBuilder sb = new StringBuilder();
        for (Character c : currentLetters) {
            sb.append(c != null ? c : ' ');
        }
        return sb.toString();
    }

    public Character[] stringToCurrentLetters(String str) {
        var res = new Character[str.length()];
        int i = 0;
        for (char c : str.toCharArray()) {
            res[i++] = c != ' ' ? c : null;
        }
        return res;
    }
}
