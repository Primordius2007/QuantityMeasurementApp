package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet feet = (Feet) obj;
            return Double.compare(this.value, feet.value) == 0;
        }
    }

    static class Inch {
        private final double value;

        public Inch(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inch inch = (Inch) obj;
            return Double.compare(this.value, inch.value) == 0;
        }
    }

    public static void demonstrateFeetEquality() {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        System.out.println("Are 1.0 ft and 1.0 ft equal? " + feet1.equals(feet2));

        Feet feet3 = new Feet(1.0);
        Feet feet4 = new Feet(2.0);
        System.out.println("Are 1.0 ft and 2.0 ft equal? " + feet3.equals(feet4));
    }

    public static void demonstrateInchEquality() {
        Inch inch1 = new Inch(1.0);
        Inch inch2 = new Inch(1.0);
        System.out.println("Are 1.0 in and 1.0 in equal? " + inch1.equals(inch2));

        Inch inch3 = new Inch(1.0);
        Inch inch4 = new Inch(2.0);
        System.out.println("Are 1.0 in and 2.0 in equal? " + inch3.equals(inch4));
    }

    public static void main(String[] args) {

        // UC1: Feet measurement equality
        demonstrateFeetEquality();

        // UC2: Inch measurement equality
        demonstrateInchEquality();
    }
}
