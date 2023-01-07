package dev.dex;

import java.util.Scanner;

public class Main {
    private static Gearbox e46 = new Gearbox();
    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\te46 Gearbox");

        int choice;
        do {
            menu();

            choice = Integer.parseInt(s.nextLine());
            switch (choice) {
                case 1 -> {
                    e46.operateClutch(true);
                }
                case 2 -> {
                    e46.operateClutch(false);
                }
                case 3 -> {
                    putGear();
                }
                case 4 -> {
                    wheelSpeed();
                }
                case 5 -> {
                    e46.operateClutch(true);
                    e46.changeGear(0);
                    e46.operateClutch(false);
                }
            }
        } while (choice != 6 && !e46.isDead());

        if (e46.isDead()) {
            System.out.println("Gearbox is gone");
        }
        System.out.println("Bye");
        s.close();

    }

    private static void putGear() {
        e46.printGears();
        int gear = Integer.parseInt(s.nextLine());
        try {
            e46.changeGear(gear);
        } catch (RuntimeException e) {
            System.out.println("Grind.. something bad happened");
        }
    }

    private static void wheelSpeed() {
        System.out.println("Add revs from 1000 to 6500");
        try {
            System.out.println("Wheel Speed = " + e46.wheelSpeed(Integer.parseInt(s.nextLine())));
        } catch (RuntimeException e) {
            System.out.println("Revs are problematic");
        }
    }

    private static void menu() {
        System.out.println("MS " + e46.CurrentGear() + ", Lives " + e46.getLives());
        System.out.println("""
                1 - Press and hold the clutch
                2 - Let go of the clutch
                3 - Put a gear
                4 - Calculate wheel speed
                5 - Stop
                6 - Quit
                """);
    }
}
