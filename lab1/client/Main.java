package lab1.client;

import java.util.Scanner;
import lab1.domain.facade.GameFacade;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new GameFacade(scanner).start();
        scanner.close();
    }
}
