package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    enum LengthUnit {
        FEET(12.0),
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
            return value * unit.getConversionFactor();
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
    }

    public static void main(String[] args) {

        // UC1: Feet measurement equality
        demonstrateFeetEquality();

        // UC2: Inch measurement equality
        demonstrateInchEquality();

        // UC3: Generic Length class with unit conversion (DRY principle)
        demonstrateFeetInchesComparison();
    }
}
