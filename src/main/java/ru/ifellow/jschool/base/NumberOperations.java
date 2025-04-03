package ru.ifellow.jschool.base;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberOperations {

    private NumberOperations() {}

    public static Integer find(int[] array, int value) {
        if (array == null)
            return null;

        for (int i = 0; i < array.length; i++)
            if (array[i] == value) return i;

        return null;
    }

    public static Integer find(double[] array, double value, double eps) {
        if (array == null)
            return null;

        for (int i = 0; i < array.length; i++)
            if (Math.abs(array[i] - value) <= eps)
                return i;

        return null;
    }

    public static Double calculateDensity(double weight, double volume, double min, double max) {

        double density = weight / volume;
        if (density >= min && density <= max)
            return density;

        return null;
    }

    public static Integer find(BigInteger[] array, BigInteger value) {
        if (array == null)
            return null;

        for (int i = 0; i < array.length; i++)
            if (array[i].equals(value))
                return i;

        return null;
    }


    public static BigDecimal calculateDensity(BigDecimal weight, BigDecimal volume, BigDecimal min, BigDecimal max) {

        BigDecimal density = weight.divide(volume, BigDecimal.ROUND_HALF_UP);

        if (density.compareTo(min) >= 0 && density.compareTo(max) <= 0)
            return density;

        return null;
    }

}
