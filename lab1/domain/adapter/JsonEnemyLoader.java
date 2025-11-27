package lab1.domain.adapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonEnemyLoader {

    public ExternalEnemyData load(Path path) throws IOException {

        String json = Files.readString(path);

        ExternalEnemyData data = new ExternalEnemyData();

        data.name = extractString(json, "name");
        data.hp = extractInt(json, "hp");
        data.attack = extractInt(json, "attack");
        data.defense = extractInt(json, "defense");
        data.taunt = extractString(json, "taunt");

        return data;
    }

    private String extractString(String json, String key) {
        String pattern = "\"" + key + "\"";
        int pos = json.indexOf(pattern);
        if (pos == -1) return "Unknown";

        pos = json.indexOf(":", pos) + 1;
        pos = json.indexOf("\"", pos) + 1;
        int end = json.indexOf("\"", pos);

        return json.substring(pos, end);
    }

    private int extractInt(String json, String key) {
        String pattern = "\"" + key + "\"";
        int pos = json.indexOf(pattern);
        if (pos == -1) return 0;

        pos = json.indexOf(":", pos) + 1;

        int comma = json.indexOf(",", pos);
        int end = (comma == -1) ? json.indexOf("}", pos) : comma;

        return Integer.parseInt(json.substring(pos, end).trim());
    }
}

