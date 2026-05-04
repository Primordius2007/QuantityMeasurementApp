package com.apps.quantitymeasurement;

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

    public double convertToBaseUnit(double value) {
        return Math.round(value * conversionFactor * 100.0) / 100.0;
    }

    public double convertFromBaseUnit(double baseValue) {
        return Math.round((baseValue / conversionFactor) * 100.0) / 100.0;
    }
}

public class QuantityMeasurementApp {

    static class Length {
        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double convertToBaseUnit() {
            return unit.convertToBaseUnit(value);
        }

        private double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit) {
            return targetUnit.convertFromBaseUnit(lengthInInches);
        }

        private boolean compare(Length thatLength) {
            return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
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
            return addAndConvert(thatLength, this.unit);
        }

        public Length add(Length thatLength, LengthUnit targetUnit) {
            if (thatLength == null) throw new IllegalArgumentException("Length to add must not be null");
            if (targetUnit == null) throw new IllegalArgumentException("Target unit must not be null");
            return addAndConvert(thatLength, targetUnit);
        }

        private Length addAndConvert(Length thatLength, LengthUnit targetUnit) {
            double sumInBase = this.convertToBaseUnit() + thatLength.convertToBaseUnit();
            double resultValue = convertFromBaseToTargetUnit(sumInBase, targetUnit);
            return new Length(resultValue, targetUnit);
        }

        @Override
        public String toString() {
            return String.format("%.2f %s", value, unit);
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
    }

    public static Length demonstrateLengthAddition(Length length1, Length length2, LengthUnit targetUnit) {
        Length result = length1.add(length2, targetUnit);
        System.out.println("Add " + length1 + " + " + length2 + " in " + targetUnit + " => " + result);
        return result;
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
        demonstrateLengthConversion(new Length(2.0, LengthUnit.YARDS), LengthUnit.INCHES);

        // UC6: Addition of two length units (result in first operand's unit)
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(2.0, LengthUnit.FEET));
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES));
        demonstrateLengthAddition(new Length(12.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.FEET));
        demonstrateLengthAddition(new Length(1.0, LengthUnit.YARDS), new Length(3.0, LengthUnit.FEET));
        demonstrateLengthAddition(new Length(36.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.YARDS));
        demonstrateLengthAddition(new Length(2.54, LengthUnit.CENTIMETERS), new Length(1.0, LengthUnit.INCHES));
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(0.0, LengthUnit.INCHES));
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(-2.0, LengthUnit.FEET));

        // UC7: Addition with explicit target unit specification
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES), LengthUnit.INCHES);
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES), LengthUnit.YARDS);
        demonstrateLengthAddition(new Length(1.0, LengthUnit.YARDS), new Length(3.0, LengthUnit.FEET), LengthUnit.YARDS);
        demonstrateLengthAddition(new Length(36.0, LengthUnit.INCHES), new Length(1.0, LengthUnit.YARDS), LengthUnit.FEET);
        demonstrateLengthAddition(new Length(2.54, LengthUnit.CENTIMETERS), new Length(1.0, LengthUnit.INCHES), LengthUnit.CENTIMETERS);
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(0.0, LengthUnit.INCHES), LengthUnit.YARDS);
        demonstrateLengthAddition(new Length(5.0, LengthUnit.FEET), new Length(-2.0, LengthUnit.FEET), LengthUnit.INCHES);

        // UC8: Standalone LengthUnit with conversion responsibility
        System.out.println("FEET.convertToBaseUnit(12.0) => " + LengthUnit.FEET.convertToBaseUnit(12.0));
        System.out.println("INCHES.convertToBaseUnit(12.0) => " + LengthUnit.INCHES.convertToBaseUnit(12.0));
        System.out.println("YARDS.convertToBaseUnit(1.0) => " + LengthUnit.YARDS.convertToBaseUnit(1.0));
        System.out.println("FEET.convertFromBaseUnit(36.0) => " + LengthUnit.FEET.convertFromBaseUnit(36.0));
        demonstrateLengthConversion(new Length(1.0, LengthUnit.FEET), LengthUnit.INCHES);
        demonstrateLengthAddition(new Length(1.0, LengthUnit.FEET), new Length(12.0, LengthUnit.INCHES), LengthUnit.FEET);
        demonstrateLengthComparison(36.0, LengthUnit.INCHES, 1.0, LengthUnit.YARDS);
    }
}
