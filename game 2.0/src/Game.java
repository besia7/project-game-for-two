import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Гра почалася!");

        Character player1 = createCharacter("Гравець 1", scanner);
        Character player2 = createCharacter("Гравець 2", scanner);

        while (player1.isAlive() && player2.isAlive()) {
            System.out.println("\nРаунд починається!");
            player1.displayStats();
            player2.displayStats();

            // Випадкова черговість ходу
            if (random.nextBoolean()) {
                playerAction(player1, player2, scanner);
                playerAction(player2, player1, scanner);
            } else {
                playerAction(player2, player1, scanner);
                playerAction(player1, player2, scanner);
            }
        }

        System.out.println("\nГра закінчилася!");
        if (player1.isAlive()) {
            System.out.println("Гравець 1 переміг!");
        } else {
            System.out.println("Гравець 2 переміг!");
        }

        scanner.close();
    }

    private static Character createCharacter(String name, Scanner scanner) {
        System.out.printf("%s, введіть максимальну кількість здоров'я: ", name);
        int maxHealth = scanner.nextInt();
        System.out.printf("%s, введіть силу атаки: ", name);
        int attackPower = scanner.nextInt();
        System.out.printf("%s, введіть силу захисту: ", name);
        int defensePower = scanner.nextInt();

        return new Character(name, maxHealth, attackPower, defensePower);
    }

    private static void playerAction(Character attacker, Character defender, Scanner scanner) {
        System.out.printf("%s, оберіть дію: 1 - Атакувати, 2 - Захиститися\n", attacker.getName());
        int action = scanner.nextInt();

        if (action == 1) {
            attacker.attack(defender);
        } else if (action == 2) {
            attacker.defend();
            System.out.println(attacker.getName() + " захистився!");
        } else {
            System.out.println("Невідома дія. Спробуйте ще раз.");
        }
    }
}

class Character {
    private String name;
    private int maxHealth;
    private int health;
    private int attackPower;
    private int defensePower;

    public Character(String name, int maxHealth, int attackPower, int defensePower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
    }

    public void attack(Character opponent) {
        int damage = attackPower - opponent.defensePower;
        if (damage > 0) {
            opponent.health = Math.max(0, opponent.health - damage);
            System.out.printf("%s завдав %d шкоди %s!\n", name, damage, opponent.getName());
        } else {
            System.out.println(name + " не завдав шкоди " + opponent.getName() + "!");
        }
    }

    public void defend() {
        // Додати логіку захисту (зменшення шкоди)
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void displayStats() {
        System.out.printf("%s: Здоров'я: %d/%d, Сила атаки: %d, Сила захисту: %d\n", name, health, maxHealth, attackPower, defensePower);
    }

    public String getName() {
        return name;
    }
}
