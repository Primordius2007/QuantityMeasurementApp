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
        }

        private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {
            return Math.round((lengthInInches / targetUnit.getConversionFactor()) * 100.0) / 100.0;
        }

        private boolean compare(Length thatLength) {
            return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
        private boolean compare(Length thatLength) {
            return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
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
            Length that = (Length) obj;
            return compare(that);
        }

        public Length convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) throw new IllegalArgumentException("Target unit must not be null");
            double convertedValue = convertFromBaseToTargetUnit(this.convertToBaseUnit(), targetUnit);
            return new Length(convertedValue, targetUnit);
        }

        public Length add(Length thatLength) {
            if (thatLength == null) throw new IllegalArgumentException("Length to add must not be null");
            double sumInBase = this.convertToBaseUnit() + thatLength.convertToBaseUnit();
            double resultValue = convertFromBaseToTargetUnit(sumInBase, this.unit);
            return new Length(resultValue, this.unit);
        }

        @Override
        public String toString() {
            return String.format("%.2f %s", value, unit);
            double baseValue = this.convertToBaseUnit();
            double convertedValue = Math.round((baseValue / targetUnit.getConversionFactor()) * 100.0) / 100.0;
            return new Length(convertedValue, targetUnit);
        }

        @Override
        public String toString() {
            return String.format("%.2f %s", value, unit);
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

    public static Length demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        Length length = new Length(value, fromUnit);
        Length converted = length.convertTo(toUnit);
        System.out.println("Convert " + value + " " + fromUnit + " to " + toUnit + " => " + converted);
        return converted;
    }

    public static Length demonstrateLengthConversion(Length length, LengthUnit toUnit) {
        Length converted = length.convertTo(toUnit);
        System.out.println("Convert " + length + " to " + toUnit + " => " + converted);
        return converted;
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2) {
        Length result = length1.add(length2);
        System.out.println("Add " + length1 + " + " + length2 + " => " + result);
        return result;
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
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 1.0, LengthUnit.FEET);
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 2.0, LengthUnit.FEET);

        // UC2: Inch measurement equality
        demonstrateLengthComparison(1.0, LengthUnit.INCHES, 1.0, LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, LengthUnit.INCHES, 2.0, LengthUnit.INCHES);

        // UC3: Generic Length class with unit conversion (DRY principle)
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, LengthUnit.FEET, 1.0, LengthUnit.INCHES);

        // UC4: Extended unit support - Yards and Centimeters
        demonstrateLengthComparison(1.0, LengthUnit.YARDS, 3.0, LengthUnit.FEET);
        demonstrateLengthComparison(1.0, LengthUnit.YARDS, 36.0, LengthUnit.INCHES);
        demonstrateLengthComparison(2.0, LengthUnit.YARDS, 2.0, LengthUnit.YARDS);
        demonstrateLengthComparison(1.0, LengthUnit.CENTIMETERS, 0.393701, LengthUnit.INCHES);
        demonstrateLengthComparison(2.0, LengthUnit.CENTIMETERS, 2.0, LengthUnit.CENTIMETERS);
        demonstrateLengthComparison(3.0, LengthUnit.FEET, 1.0, LengthUnit.YARDS);
        demonstrateLengthComparison(30.48, LengthUnit.CENTIMETERS, 1.0, LengthUnit.FEET);

        // UC5: Unit-to-unit conversion
        demonstrateLengthConversion(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS, LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES, LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        demonstrateLengthConversion(0.0, LengthUnit.FEET, LengthUnit.INCHES);
        Length yardsInstance = new Length(2.0, LengthUnit.YARDS);
        demonstrateLengthConversion(yardsInstance, LengthUnit.INCHES);

        // UC6: Addition of two length units
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(2.0, LengthUnit.FEET));
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES));
        demonstrateLengthAddition(new Length(12.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.FEET));
        demonstrateLengthAddition(new Length(1.0, LengthUnit.YARDS), new Length(3.0, LengthUnit.FEET));
        demonstrateLengthAddition(new Length(36.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.YARDS));
        demonstrateLengthAddition(new Length(2.54, LengthUnit.CENTIMETERS), new Length(1.0, LengthUnit.INCHES));
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(0.0, LengthUnit.INCHES));
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(-2.0, LengthUnit.FEET));
        demonstrateFeetEquality();

        // UC2: Inch measurement equality
        demonstrateInchEquality();

        // UC3: Generic Length class with unit conversion (DRY principle)
        demonstrateFeetInchesComparison();
    }
}
