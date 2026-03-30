import java.util.Scanner;

public class Main {

    // Print the current 3x3 board with grid lines
    public static void printChessBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Place a piece on the board; return true if successful, false otherwise
    public static boolean putChess(char[][] board, int x, int y, char player) {
        // 為了讓玩家輸入更直觀的座標（1~3），我們在這裡將輸入的座標減 1 以對應到陣列索引（0~2）
        x -= 1;
        y -= 1;

        // Check if coordinates are in range
        if (x < 0 || x > 2 || y < 0 || y > 2) {
            System.out.println("座標超出範圍，請輸入 1~3 的數字。");
            return false;
        }
        // Check if cell is already occupied
        if (board[x][y] != ' ') {
            System.out.println("該位置已有棋子，請重新選擇。");
            return false;
        }
        board[x][y] = player;
        return true;
    }

    // Check if the given player has won
    public static boolean checkWin(char[][] board, char player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        // Check main diagonal (top-left to bottom-right)
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        // Check anti-diagonal (top-right to bottom-left)
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    // Check if the board is completely filled (draw)
    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Computer move strategy (priority-based, not random)
    public static void computerMove(char[][] board) {
        // Priority 1: Win if possible
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'X';
                    if (checkWin(board, 'X')) {
                        return; // Found winning move
                    }
                    board[i][j] = ' '; // Undo
                }
            }
        }

        // Priority 2: Block player from winning
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    if (checkWin(board, 'O')) {
                        board[i][j] = 'X'; // Block here
                        return;
                    }
                    board[i][j] = ' '; // Undo
                }
            }
        }

        // Priority 3: Take center
        if (board[1][1] == ' ') {
            board[1][1] = 'X';
            return;
        }

        // Priority 4: Take a corner
        int[][] corners = { { 0, 0 }, { 0, 2 }, { 2, 0 }, { 2, 2 } };
        for (int[] corner : corners) {
            if (board[corner[0]][corner[1]] == ' ') {
                board[corner[0]][corner[1]] = 'X';
                return;
            }
        }

        // Priority 5: Take any remaining empty cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'X';
                    return;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize board with spaces
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        System.out.println("=== 井字遊戲開始 ===");
        System.out.println("你是 O，電腦是 X");
        System.out.println("座標格式：row col（例如：1 2，範圍 1~3）");

        // Game loop
        while (true) {
            // Print current board
            printChessBoard(board);

            // Player's turn
            int x, y;
            while (true) {
                System.out.print("請輸入座標 x y：");
                x = scanner.nextInt();
                y = scanner.nextInt();
                if (putChess(board, x, y, 'O')) {
                    break; // Valid move placed
                }
            }

            // Check if player wins
            if (checkWin(board, 'O')) {
                printChessBoard(board);
                System.out.println("恭喜你贏了！");
                break;
            }

            // Check for draw after player's move
            if (isBoardFull(board)) {
                printChessBoard(board);
                System.out.println("平手！");
                break;
            }

            // Computer's turn
            System.out.println("電腦下棋中...");
            computerMove(board);

            // Check if computer wins
            if (checkWin(board, 'X')) {
                printChessBoard(board);
                System.out.println("電腦贏了！再試一次吧。");
                break;
            }

            // Check for draw after computer's move
            if (isBoardFull(board)) {
                printChessBoard(board);
                System.out.println("平手！");
                break;
            }
        }

        scanner.close();
    }
}
