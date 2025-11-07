package lab2.main_client;

import lab2.builder.WeaponBuilder;
import lab2.domain.inventory.AmmoClipPool;
import lab2.domain.inventory.Inventory;
import lab2.domain.models.Weapon;
import lab2.factory.abstractfactory.CivilianFactory;
import lab2.factory.abstractfactory.MilitaryFactory;
import lab2.factory.factorymethod.ConcreteCreators;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Inventory inventory = Inventory.getInstance();
        Scanner scanner = new Scanner(System.in);
        AmmoClipPool pool = AmmoClipPool.getInstance();

        boolean running = true;
        while (running) {
            System.out.println("\n=== Weapon Inventory Menu ===");
            System.out.println("1. Factory Method");
            System.out.println("2. Abstract Factory");
            System.out.println("3. Builder Pattern");
            System.out.println("4. Prototype Pattern");
            System.out.println("5. Singleton Check");
            System.out.println("6. Object Pool (Ammo Clips)");
            System.out.println("7. List Inventory");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline


            switch (choice) {
                case 1 -> {
                    System.out.println("=== 1. FACTORY METHOD ===");
                    var pistolCreator = new ConcreteCreators.PistolCreator();
                    var rifleCreator = new ConcreteCreators.RifleCreator();

                    System.out.print("Enter pistol name: ");
                    String pistolName = scanner.nextLine();
                    System.out.print("Enter pistol caliber: ");
                    String pistolCal = scanner.nextLine();
                    System.out.print("Enter pistol magazine size: ");
                    int pistolMag = scanner.nextInt();
                    System.out.print("Enter pistol weight: ");
                    double pistolWeight = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter rifle name: ");
                    String rifleName = scanner.nextLine();
                    System.out.print("Enter rifle caliber: ");
                    String rifleCal = scanner.nextLine();
                    System.out.print("Enter rifle magazine size: ");
                    int rifleMag = scanner.nextInt();
                    System.out.print("Enter rifle weight: ");
                    double rifleWeight = scanner.nextDouble();
                    scanner.nextLine();

                    Weapon glock = pistolCreator.orderWeapon(pistolName, pistolCal, pistolMag, pistolWeight);
                    Weapon ak = rifleCreator.orderWeapon(rifleName, rifleCal, rifleMag, rifleWeight);
                    inventory.addWeapon(glock);
                    inventory.addWeapon(ak);

                    System.out.println("Added to inventory: " + glock.getName() + ", " + ak.getName());
                }
                case 2 -> {
                    System.out.println("\n=== 2. ABSTRACT FACTORY ===");
                    var civFactory = new CivilianFactory();
                    var milFactory = new MilitaryFactory();

                    inventory.addWeapon(civFactory.createPistol());
                    inventory.addWeapon(milFactory.createRifle());
                    System.out.println("Helmet: " + milFactory.createHelmet());
                }
                case 3 -> {
                    System.out.println("\n=== 3. BUILDER PATTERN ===");
                    System.out.print("Enter weapon name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter caliber: ");
                    String caliber = scanner.nextLine();
                    System.out.print("Enter magazine size: ");
                    int mag = scanner.nextInt();
                    System.out.print("Enter weight: ");
                    double weight = scanner.nextDouble();
                    scanner.nextLine();

                    Weapon custom = new WeaponBuilder()
                            .setName(name)
                            .setCaliber(caliber)
                            .setMagazineSize(mag)
                            .setWeight(weight)
                            .build();
                    inventory.addWeapon(custom);
                    System.out.println("Added to inventory: " + custom.getName());
                }
                case 4 -> {
                    System.out.println("\n=== 4. PROTOTYPE PATTERN ===");

                    if (inventory.getWeapons().isEmpty()) {
                        System.out.println("Inventory is empty. Cannot clone any weapon.");
                    } else {
                        System.out.println("Select weapon index to clone:");
                        for (int i = 0; i < inventory.getWeapons().size(); i++) {
                            System.out.println(i + ": " + inventory.getWeapons().get(i));
                        }


                        int idx = scanner.nextInt();

                        if (idx < 0 || idx >= inventory.getWeapons().size()) {
                            System.out.println("Invalid index! Please choose between 0 and " + (inventory.getWeapons().size() - 1));
                        } else {
                            Weapon toClone = inventory.getWeapons().get(idx);
                            Weapon cloned = toClone.clone();
                            inventory.addWeapon(cloned);
                            System.out.println("Cloned weapon: " + cloned);
                        }
                        }
                }
                case 5 -> {
                    System.out.println("\n=== 5. SINGLETON (Inventory) ===");
                    Inventory anotherRef = Inventory.getInstance();
                    System.out.println("Same instance? " + (inventory == anotherRef));
                }
                case 6 -> {
                    System.out.println("\n=== 6. OBJECT POOL (Ammo Clips) ===");
                    var clip = pool.acquire();
                    System.out.println("Acquired clip: " + clip);

                    System.out.print("Enter number of rounds to fire: ");
                    int rounds = scanner.nextInt();
                    scanner.nextLine();
                    for (int i = 0; i < rounds; i++) clip.fire();

                    pool.release(clip);
                    System.out.println("Clip returned and reloaded.");
                }
                case 7 -> {
                    System.out.println("\n--- INVENTORY ---");
                    inventory.listAll();
                }
                case 8 -> {
                    running = false;
                }
                default -> System.out.println("Invalid option!");
            }
        }

        scanner.close();
        System.out.println("Exiting...");
    }
}
