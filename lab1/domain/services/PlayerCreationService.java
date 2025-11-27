package lab1.domain.services;

import lab1.domain.models.*;

import java.util.Scanner;
import lab1.domain.core.GameConfig;

public class PlayerCreationService {

    private final PlayerDirector director = new PlayerDirector();
    private final PlayerBuilder builder = new PlayerBuilder();

    public Player createPlayer(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("Choose your class:");
        GameConfig.CLASS_OPTIONS.forEach((k, v) -> System.out.println(k + ") " + v));

        System.out.print("> ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        String cls = GameConfig.CLASS_OPTIONS.getOrDefault(choice, "Adventurer");

        return director.createDefault(builder, name, cls);
    }
}
