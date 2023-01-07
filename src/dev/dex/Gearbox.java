package dev.dex;

import java.util.ArrayList;

public class Gearbox {
    private int maxGears = 7;
    private int currentGear = 0;
    private boolean clutchIsIn;
    private ArrayList<Gear> gears = new ArrayList<>(maxGears);
    private int lives = 4;

    public Gearbox() {
        this.gears.add(new Gear(0, 0.0));
        addGear(1, 4.35);
        addGear(2, 2.5);
        addGear(3, 1.66);
        addGear(4, 1.23);
        addGear(5, 1);
        addGear(6, 0.85);
        addGear(-1, 3.93);
    }

    public void operateClutch(boolean in) {
        if (clutchIsIn && in) {
            System.out.println("Clutch is already in. Warning Clutch is Hot!");
        }
        clutchIsIn = in;
    }

    private void addGear(int number, double ratio) {
        if (number < -1 || number > maxGears) {
            throw new RuntimeException("addGearException number incorrect");
        }

        gears.add(new Gear(number, ratio));
    }

    public void changeGear(int newGear) {
        if ((newGear < -1 || newGear >= gears.size()) || clutchIsIn == false) {
            currentGear = 0;
            lives--;
            throw new RuntimeException("Gearbox -> changeGear gear is invalid or clutch in not in");
        }
        currentGear = newGear;
        System.out.println("Gear " + newGear + " selected");
    }

    public double wheelSpeed(int revs) {
        if (revs > 6500 || revs < 1000) {
            throw new RuntimeException("Gearbox -> wheelSpeed revs is too high or too low");
        }
        if (clutchIsIn) {
            System.out.println("Scream!!!");
            return 0.0;
        }
        return revs * gears.get(currentGear).getRatio();
    }

    public void printGears() {
        System.out.print("Gears: ");
        for (Gear g: gears) {
            System.out.print(g.gearNumber + " ");
        }
        System.out.println("\n");
    }

    public int CurrentGear() {
        if (clutchIsIn) {
            return 0;
        }
        return currentGear;
    }

    public int getLives() {
        return lives;
    }

    public boolean isDead() {
        return lives <= 0 ? true : false;
    }

    private class Gear {
        private int gearNumber;
        private double ratio;

        public Gear(int gearNumber, double ratio) {
            this.gearNumber = gearNumber;
            this.ratio = ratio;
        }

        public double driveSpeed(int revs) {
            return revs * ratio;
        }

        public double getRatio() {
            return ratio;
        }
    }

}
