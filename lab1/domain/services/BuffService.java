package lab1.domain.services;

import lab1.domain.combat.IFighter;
import lab1.domain.services.buffs.BuffRegistry;
import lab1.domain.services.buffs.IBuffOption;

import java.util.*;

public class BuffService {

    public IFighter applyBuffs(Scanner scanner, IFighter fighter) {

        while (true) {
            System.out.println("\n== Buff Menu (0 to finish) ==");
            BuffRegistry.BUFFS.forEach((id, buff) ->
                    System.out.println(id + ") " + buff.getLabel()));
            System.out.println("0) Done");

            System.out.print("> ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0)
                return fighter;

            IBuffOption buff = BuffRegistry.BUFFS.get(choice);

            if (buff == null) {
                System.out.println("Invalid choice.");
                continue;
            }

            fighter = buff.apply(fighter);
            System.out.println("Applied: " + buff.getLabel());
        }
    }
}

