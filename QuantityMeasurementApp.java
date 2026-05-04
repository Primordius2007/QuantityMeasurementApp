package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);
        INCHES(1.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    static class Length {
        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double convertToBaseUnit() {
            return Math.round(value * unit.getConversionFactor() * 100.0) / 100.0;
            return value * unit.getConversionFactor();
        }

        public boolean compare(Length thatLength) {
            return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
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
            Length that = (Length) obj;
            return compare(that);
        }
    }

    public static boolean demonstrateLengthEquality(Length length1, Length length2) {
        return length1.equals(length2);
    }

    public static boolean demonstrateLengthComparison(double value1, LengthUnit unit1, double value2, LengthUnit unit2) {
        Length length1 = new Length(value1, unit1);
        Length length2 = new Length(value2, unit2);
        boolean result = demonstrateLengthEquality(length1, length2);
        System.out.println("Are " + value1 + " " + unit1 + " and " + value2 + " " + unit2 + " equal? " + result);
        return result;
    }

    public static void demonstrateFeetEquality() {
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 1.0, LengthUnit.FEET);
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 2.0, LengthUnit.FEET);
    }

    public static void demonstrateInchEquality() {
        demonstrateLengthComparison(1.0, LengthUnit.INCHES, 1.0, LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, LengthUnit.INCHES, 2.0, LengthUnit.INCHES);
    }

    public static void demonstrateFeetInchesComparison() {
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 1.0, LengthUnit.INCHES);
    public static void demonstrateFeetEquality() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length feet2 = new Length(1.0, LengthUnit.FEET);
        System.out.println("Are 1.0 ft and 1.0 ft equal? " + demonstrateLengthEquality(feet1, feet2));

        Length feet3 = new Length(1.0, LengthUnit.FEET);
        Length feet4 = new Length(2.0, LengthUnit.FEET);
        System.out.println("Are 1.0 ft and 2.0 ft equal? " + demonstrateLengthEquality(feet3, feet4));
    }

    public static void demonstrateInchEquality() {
        Length inch1 = new Length(1.0, LengthUnit.INCHES);
        Length inch2 = new Length(1.0, LengthUnit.INCHES);
        System.out.println("Are 1.0 in and 1.0 in equal? " + demonstrateLengthEquality(inch1, inch2));

        Length inch3 = new Length(1.0, LengthUnit.INCHES);
        Length inch4 = new Length(2.0, LengthUnit.INCHES);
        System.out.println("Are 1.0 in and 2.0 in equal? " + demonstrateLengthEquality(inch3, inch4));
    }

    public static void demonstrateFeetInchesComparison() {
        Length feet1 = new Length(1.0, LengthUnit.FEET);
        Length inch1 = new Length(12.0, LengthUnit.INCHES);
        System.out.println("Are 1.0 ft and 12.0 in equal? " + demonstrateLengthEquality(feet1, inch1));

        Length feet2 = new Length(1.0, LengthUnit.FEET);
        Length inch2 = new Length(1.0, LengthUnit.INCHES);
        System.out.println("Are 1.0 ft and 1.0 in equal? " + demonstrateLengthEquality(feet2, inch2));
            Inch inch = (Inch) obj;
            return Double.compare(this.value, inch.value) == 0;
        }
    }

    public static void demonstrateFeetEquality() {
    public static void main(String[] args) {

        // UC1: Feet measurement equality
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

        // UC3: Generic Length class with unit conversion (DRY principle)
        demonstrateFeetInchesComparison();

        // UC4: Extended unit support - Yards and Centimeters
        demonstrateLengthComparison(1.0, LengthUnit.YARDS, 3.0, LengthUnit.FEET);
        demonstrateLengthComparison(1.0, LengthUnit.YARDS, 36.0, LengthUnit.INCHES);
        demonstrateLengthComparison(2.0, LengthUnit.YARDS, 2.0, LengthUnit.YARDS);
        demonstrateLengthComparison(1.0, LengthUnit.CENTIMETERS, 0.393701, LengthUnit.INCHES);
        demonstrateLengthComparison(2.0, LengthUnit.CENTIMETERS, 2.0, LengthUnit.CENTIMETERS);
        demonstrateLengthComparison(3.0, LengthUnit.FEET, 1.0, LengthUnit.YARDS);
        demonstrateLengthComparison(30.48, LengthUnit.CENTIMETERS, 1.0, LengthUnit.FEET);
    }
}
