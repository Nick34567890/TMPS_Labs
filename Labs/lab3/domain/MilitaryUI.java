package domain;

import client.MissionFacade;
import client.MilitaryUnit;

import java.util.Scanner;


public class MilitaryUI {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MissionFacade facade = new MissionFacade();

        System.out.println("============================================================");
        System.out.println("        Welcome to ARES Military Operations Command!");
        System.out.println("============================================================");
        System.out.println("Tax Rate: 10.0%\n");
        System.out.println("* Elite Operator Program Available!\n");
        System.out.println("Elite Operator Benefits:");
        System.out.println("  - 10% discount on all mission deployments\n");

        boolean elite = false;

        System.out.print("Are you an Elite Operator? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            System.out.print("Enter your operator ID: ");
            String id = scanner.nextLine();

            if (!id.equals("123456789")) {
                System.out.println("x Operator ID not found in our system.\n");

                System.out.print("Would you like to sign up? (yes/no): ");
                if (scanner.nextLine().equalsIgnoreCase("yes")) {
                    System.out.print("Enter new operator ID to register: ");
                    String newID = scanner.nextLine();
                    System.out.println("Yayy!!! Successfully enrolled! Your ID: " + newID);
                    System.out.println("+ You can now enjoy 10% off missions!\n");
                    elite = true;
                }
            } else elite = true;
        }

        while (true) {
            System.out.println("What would you like to add?");
            System.out.println("1. Deploy Unit");
            System.out.println("2. Add Equipment (coming soon)");
            System.out.println("3. Finish Mission");
            System.out.print("Choose (1-3): ");

            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println();

            if (choice == 3) break;

            if (choice == 1) {
                System.out.println("============================================================");
                System.out.println("                !!! NEW UNIT DEPLOYMENT !!!");
                System.out.println("============================================================\n");

                System.out.println("Available Unit Types:");
                System.out.println("1. Recon Drone      - $2500");
                System.out.println("2. Armored Vehicle  - $4700");
                System.out.println("3. Heavy Tank       - $9300");
                System.out.print("Choose a unit (1-3): ");
                int unitChoice = Integer.parseInt(scanner.nextLine());

                System.out.println("\nChoose Deployment Protocol:");
                System.out.println("1. Satellite Link (+$300)");
                System.out.println("2. Radio Link (+$150)");
                System.out.println("3. Encrypted Network (+$500)");
                System.out.print("Choose (1-3): ");
                int protocolChoice = Integer.parseInt(scanner.nextLine());

                MilitaryUnit unit = facade.createUnit(unitChoice, protocolChoice);

                System.out.println("\nWould you like to add mission upgrades?");
                System.out.println("1. Stealth Coating (+$300)");
                System.out.println("2. Reinforced Armor (+$500)");
                System.out.println("3. Thermal Imaging (+$250)");
                System.out.println("4. No upgrade");
                System.out.print("Choose (1-4): ");
                int upgradeChoice = Integer.parseInt(scanner.nextLine());
                System.out.println();

                unit = facade.applyUpgrade(unit, upgradeChoice);

                double finalPrice = facade.calculateFinalCost(unit, elite);

                System.out.println("Unit successfully added!");
                System.out.println("Description: " + unit.getDescription());
                System.out.println("Final Mission Cost (with tax): $" + finalPrice);
                System.out.println();
            }
        }

        System.out.println("Mission finalized. Good luck, Operator.");
        scanner.close();
    }
}
