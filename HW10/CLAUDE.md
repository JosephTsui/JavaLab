# Claude Code 專案說明（井字遊戲 Tic-Tac-Toe）

## 🎯 專案目標

使用 Java 開發一個「主控台版本」的井字遊戲（3x3），需求如下：

- 玩家使用 `O`
- 電腦使用 `X`
- 電腦不能隨機下棋，必須具備基本策略

---

## 🧩 必須實作的功能（Functions）

請將功能拆分為以下方法：

### 1. `printChessBoard()`

- 印出目前 3x3 棋盤
- 格式需清楚（有格線）
- 讓使用者能直觀理解棋盤狀態

---

### 2. `putChess()`

- 負責「放置棋子」
- 檢查座標是否合法（0~2）
- 檢查該格是否為空
- 回傳 boolean：
  - `true` → 放成功
  - `false` → 放失敗

---

### 3. `checkWin()`

- 檢查是否有玩家勝利
- 必須檢查：
  - 所有 row（橫）
  - 所有 column（直）
  - 兩條對角線（斜）

---

### 4. `isBoardFull()`

- 檢查棋盤是否已滿（平手判斷）

---

### 5. `computerMove()`

- 電腦下棋邏輯（不可隨機）

---

## 🧠 棋盤設計（2D Array）

請使用：

```java
char[][] board = new char[3][3];
```

每格狀態：

| 狀態 | 說明 |
|--|--|
| `' '` | 空格 |
| `'O'` | 玩家 |
| `'X'` | 電腦 |

---

## 🔄 遊戲流程（Game Loop）

請實作完整遊戲流程：

1. 初始化棋盤（全部設為空白 `' '`）
2. 印出棋盤
3. 玩家輸入座標
4. 呼叫 `putChess`
5. 檢查玩家是否勝利
6. 檢查是否平手
7. 電腦下棋
8. 檢查電腦是否勝利
9. 再檢查是否平手
10. 重複直到遊戲結束

---

## 🤖 電腦策略（非常重要）

請依照以下優先順序實作：

1. **能贏就先贏**
2. **擋玩家（避免玩家下一步贏）**
3. **下中間 (1,1)**
4. **下角落**
5. **最後才隨便找空格**

---

## 🧑‍💻 程式風格要求

- 使用「初學者友善」寫法
- 避免過度複雜語法
- 方法（function）要清楚分工
- 變數名稱要有意義（例如：`board`, `player`, `x`, `y`）
- 加上簡單英文註解
- 不使用外部套件
- 使用 `Scanner` 讀取輸入
- 儘量寫在單一 `Main.java` 檔案

---

## 📌 建議方法結構

```java
public static void printChessBoard(char[][] board)
public static boolean putChess(char[][] board, int x, int y, char player)
public static boolean checkWin(char[][] board, char player)
public static boolean isBoardFull(char[][] board)
public static void computerMove(char[][] board)
```

---

## 🖥️ 輸出格式（棋盤）

```text
-------------
| O |   | X |
-------------
|   | X |   |
-------------
| O |   |   |
-------------
```

---

## ⌨️ 輸入格式

```text
請輸入座標 x y：
1 2
```

---

## ⚠️ 錯誤處理

若使用者輸入：

- 超出範圍（例如 3, -1）
- 該位置已有棋子

👉 必須提示錯誤並重新輸入

---

## 📦 最終輸出（Claude 必須生成）

請生成完整 Java 程式碼，包括：

- `main` 主流程
- 棋盤初始化
- 所有 function
- 電腦策略
- 勝負與平手判斷
- 完整可執行 console 程式

---

## 🚫 重要限制

❌ 不要輸出 pseudo code  
✅ 必須輸出「可編譯、可執行」的完整 Java 程式
