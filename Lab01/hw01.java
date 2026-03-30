import java.util.*;

public class hw01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // 使用 StringBuilder 提升效能
        StringBuilder result = new StringBuilder();
        int leftBracketCount = 0;

        // 處理每個字元
        for (char ch : input.toCharArray()) {
            if (ch == '(') {
                leftBracketCount++;
                result.append(ch);
            } else if (ch == ')') {
                // 只有在有對應左括號時才加入右括號
                if (leftBracketCount > 0) {
                    leftBracketCount--;
                    result.append(ch);
                }
                // 無對應左括號時直接忽略
            } else {
                result.append(ch);
            }
        }

        // 為剩餘的左括號補上右括號
        result.append(")".repeat(leftBracketCount));

        System.out.println(result);

        sc.close();
    }
}