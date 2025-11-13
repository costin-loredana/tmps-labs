package lab1.domain.adapter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonEnemyLoader {

    public ExternalEnemyData load(Path path) throws IOException {

        String json = Files.readString(path);

        ExternalEnemyData data = new ExternalEnemyData();

        data.name = extractString(json, "name");
        data.taunt = extractString(json, "taunt");
        data.hp = extractInt(json, "hp");
        data.attack = extractInt(json, "attack");
        data.defense = extractInt(json, "defense");

        return data;
    }

    private String extractString(String json, String key) {
        String pattern = "\"" + key + "\"";
        int start = json.indexOf(pattern);
        if (start == -1) return null;

        start = json.indexOf(":", start) + 1;
        start = json.indexOf("\"", start) + 1;
        int end = json.indexOf("\"", start);

        return json.substring(start, end);
    }

    private int extractInt(String json, String key) {
        String pattern = "\"" + key + "\"";
        int start = json.indexOf(pattern);
        if (start == -1) return 0;

        start = json.indexOf(":", start) + 1;
        int end = json.indexOf(",", start);
        if (end == -1) end = json.indexOf("}", start);

        String number = json.substring(start, end).trim();
        return Integer.parseInt(number);
    }
}
