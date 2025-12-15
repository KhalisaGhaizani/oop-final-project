package utils;

public class InputValidator {
    
    public static boolean isValidString(String input) {
        return input != null && !input.trim().isEmpty();
    }
    
    public static Integer validateInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    public static Integer validateIndex(String input, int size) {
        Integer num = validateInteger(input);
        if (num == null) return null;
        
        int index = num - 1;
        if (index < 0 || index >= size) {
            return null;
        }
        return index;
    }
}
