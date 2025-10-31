package lab1.client;

import java.util.Scanner;

import lab1.domain.core.GameManager;
import lab1.domain.core.CombatManager;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameManager.getInstance(scanner, CombatManager.getInstance()).startGame();
        scanner.close();
    }
}
