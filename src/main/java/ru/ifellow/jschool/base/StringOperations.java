package ru.ifellow.jschool.base;

public class StringOperations {

    private StringOperations() {}

    public static int getSummaryLength(String[] strings) {
        int sum = 0;
        for (String str : strings) if (str!= null) sum += str.length();
        return sum;
    }

    public static String getFirstAndLastLetterString(String string) {
        return string.substring(0, 1) + string.substring(string.length() - 1, string.length());
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        return string1.charAt(index) == string2.charAt(index);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        return string1.indexOf(str) == string2.indexOf(str);
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    public static boolean isEqual(String string1, String string2) {
        return string1.equals(string2);
    }
    public static boolean isEqualIgnoreCase(String string1, String string2) {
        return string1.equalsIgnoreCase(string2);
    }
    public static boolean isLess(String string1, String string2) {
        return string1.compareTo(string2) < 0;
    }
    public static boolean isLessIgnoreCase(String string1, String string2) {
        return string1.compareToIgnoreCase(string2) < 0;
    }
    public static String concat(String string1, String string2) {
        return string1 + string2;
    }
    public static boolean isSamePrefix(String string1, String string2, String prefix) {
        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }
    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }
    public static String getCommonPrefix(String string1, String string2) {
        StringBuilder commonPrefix = new StringBuilder();

        int minLength = Math.min(string1.length(), string2.length());

        for (int i = 0; i < minLength; i++) {
            if (string1.charAt(i) == string2.charAt(i)) {
                commonPrefix.append(string1.charAt(i));
            } else {
                break;
            }
        }
        return commonPrefix.toString();
    }

    public static String reverse(String string) {
        StringBuilder reversedString = new StringBuilder(string);
        return reversedString.reverse().toString();
    }

    public static boolean isPalindrome(String string) {
        return string.equals(reverse(string));
    }
    public static boolean isPalindromeIgnoreCase(String string) {
        return string.equalsIgnoreCase(reverse(string));
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        String longestPalindrome = "";

        for (String str : strings)
            if (isPalindrome(str) && str.length() > longestPalindrome.length())
                longestPalindrome = str;

        return longestPalindrome;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {

        if (index < 0 || length < 0 || index + length > string1.length() || index + length > string2.length())
            return false;

        String substring1 = string1.substring(index, index + length);
        String substring2 = string2.substring(index, index + length);

        return substring1.equals(substring2);
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1, String string2, char replaceInStr2, char replaceByInStr2) {
        String modifiedString1 = string1.replace(replaceInStr1, replaceByInStr2);
        String modifiedString2 = string2.replace(replaceInStr2, replaceByInStr2);

        return modifiedString1.equals(modifiedString2);
    }
    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1, String string2, String replaceInStr2, String replaceByInStr2) {
        String modifiedString1 = string1.replace(replaceInStr1, replaceByInStr1);
        String modifiedString2 = string2.replace(replaceInStr2, replaceByInStr2);

        return modifiedString1.equals(modifiedString2);
    }
    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        String cleanedString = string.replaceAll(" ", "");
        return isPalindromeIgnoreCase(cleanedString);
    }
    public static boolean isEqualAfterTrimming(String string1, String string2) {
        return string1.trim().equals(string2.trim());
    }


    public static String makeCsvStringFromInts(int[] array) {
        if (array == null || array.length == 0) return "";

        StringBuilder stringBuilder = makeCsvStringBuilderFromInts(array);

        return stringBuilder.toString();
    }

    public static String makeCsvStringFromDoubles(double[] array) {
        if (array == null || array.length == 0) return "";

        StringBuilder csvBuilder = makeCsvStringBuilderFromDoubles(array);

        return csvBuilder.toString();
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {

            stringBuilder.append(array[i]);
            if (i < array.length - 1) stringBuilder.append(",");
        }

        return stringBuilder;
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {

        StringBuilder csvBuilder = new StringBuilder();
        for (int i = 0; i < array.length; i++) {

            csvBuilder.append(String.format("%.2f", array[i]));
            if (i < array.length - 1) csvBuilder.append(",");
        }

        return csvBuilder;
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {
        StringBuilder stringBuilder = new StringBuilder(string);

        for (int i = positions.length - 1; i >= 0; i--)
            stringBuilder.deleteCharAt(positions[i]);

        return stringBuilder ;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        StringBuilder stringBuilder = new StringBuilder(string);

        for (int i = 0; i < positions.length; i++)
            stringBuilder.insert(positions[i], characters[i]);

        return stringBuilder;
    }



}
