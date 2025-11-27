package lab1.domain.services;

import java.util.Scanner;

public class ObserverSettingsService {

    public static class Settings {
        public boolean enableLogging;
        public boolean enableStats;
        public boolean enableAchievements;
        public boolean enableBuffExpiration;
    }

    public Settings getObserverSettings(Scanner scanner) {
        Settings s = new Settings();

        System.out.println("\n=== Observer Settings ===");

        s.enableLogging = ask(scanner, "Enable battle logging?");
        s.enableStats = ask(scanner, "Enable statistics tracker?");
        s.enableAchievements = ask(scanner, "Enable achievements?");
        s.enableBuffExpiration = ask(scanner, "Enable buff expiration alerts?");

        System.out.println("=========================\n");
        return s;
    }

    private boolean ask(Scanner sc, String text) {
        System.out.print(text + " (y/n): ");
        String ans = sc.nextLine().trim().toLowerCase();
        return ans.equals("y") || ans.equals("yes");
    }
}
