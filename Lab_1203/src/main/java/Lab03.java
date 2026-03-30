import java.util.Scanner;

public class Lab03 {

    /**
     * Check and fix parentheses symmetry in a string
     */
    public static String fixParentheses(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // First, remove premature closing parentheses
        String cleanedInput = removePrematureClosingParentheses(input);

        // Then, add missing closing parentheses
        String fixedInput = addMissingClosingParentheses(cleanedInput);

        return fixedInput;
    }

    /**
     * Remove premature closing parentheses (right parentheses that appear before
     * corresponding left parentheses)
     */
    private static String removePrematureClosingParentheses(String input) {
        StringBuilder result = new StringBuilder();
        int openCount = 0;
        int i = 0;

        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '(') {
                openCount++;
                result.append(c);
            } else if (c == ')') {
                if (openCount > 0) {
                    openCount--;
                    result.append(c);
                }
                // If openCount <= 0, this is a premature closing parenthesis, ignore it
            } else {
                result.append(c);
            }
            i++;
        }

        return result.toString();
    }

    /**
     * Add missing closing parentheses for unmatched opening parentheses
     */
    private static String addMissingClosingParentheses(String input) {
        StringBuilder result = new StringBuilder(input);
        int openCount = 0;
        int i = 0;

        // Count unmatched opening parentheses using while loop
        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '(') {
                openCount++;
            } else if (c == ')') {
                openCount--;
            }
            i++;
        }

        // Add missing closing parentheses using while loop
        i = 0;
        while (i < openCount) {
            result.append(')');
            i++;
        }

        return result.toString();
    }

    /**
     * Check if parentheses in the string are balanced
     */
    public static boolean isParenthesesBalanced(String input) {
        if (input == null) {
            return true;
        }

        int openCount = 0;
        int i = 0;

        while (i < input.length()) {
            char c = input.charAt(i);
            if (c == '(') {
                openCount++;
            } else if (c == ')') {
                if (openCount == 0) {
                    return false; // Right parenthesis appears before left parenthesis
                }
                openCount--;
            }
            i++;
        }

        return openCount == 0; // If openCount is 0, all parentheses are matched
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Parentheses Symmetry Checker ===");
        System.out.println("Enter a string with parentheses:");

        String input = scanner.nextLine();

        System.out.println("Original: " + input);

        // Check if balanced
        if (isParenthesesBalanced(input)) {
            System.out.println("Result: Balanced - output original string");
            System.out.println("Output: " + input);
        } else {
            System.out.println("Result: Not balanced - auto-correcting");
            String fixedString = fixParentheses(input);
            System.out.println("Fixed: " + fixedString);
        }

        // Test cases
        System.out.println("\n=== Test Cases ===");
        testCase("abc", "String without parentheses");
        testCase("(abc)", "Balanced parentheses");
        testCase("(z((abc)de", "Missing closing parentheses");
        testCase("gd)s(dq)", "Premature closing parentheses");
        testCase("))(((", "Complex unbalanced case");
        testCase("a(b)c(d", "Mixed case");
        testCase("((()))", "Multi-level nested balanced");
        testCase("())", "Extra closing parentheses");

        scanner.close();
    }

    /**
     * Test case method
     */
    private static void testCase(String input, String description) {
        System.out.println("\nTest: " + description);
        System.out.println("Input: " + input);
        System.out.println("Balanced: " + (isParenthesesBalanced(input) ? "Yes" : "No"));
        if (!isParenthesesBalanced(input)) {
            System.out.println("Fixed: " + fixParentheses(input));
        }
    }
}