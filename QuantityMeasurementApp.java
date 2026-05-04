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

    public static void main(String[] args) {

        // UC1: Feet measurement equality
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        System.out.println("Are 1.0 ft and 1.0 ft equal? " + feet1.equals(feet2));

        Feet feet3 = new Feet(1.0);
        Feet feet4 = new Feet(2.0);
        System.out.println("Are 1.0 ft and 2.0 ft equal? " + feet3.equals(feet4));
    }
}
