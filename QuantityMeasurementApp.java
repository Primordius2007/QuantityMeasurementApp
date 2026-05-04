package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

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
        }

        public boolean compare(Length thatLength) {
            return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
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
