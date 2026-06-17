# Tic Tac Toe Game

A simple and interactive **Tic Tac Toe game** built with **Java**. This project demonstrates the use of object-oriented programming concepts, game logic implementation, and console-based user interaction. The application implements a complete MVC (Model-View-Controller) architecture pattern with service and repository layers for data management.

## 📋 Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Architecture Overview](#architecture-overview)
- [Game Logic](#game-logic)
- [Class Descriptions](#class-descriptions)
- [How to Run](#how-to-run)
- [Game Flow](#game-flow)
- [Technical Details](#technical-details)
- [Known Limitations](#known-limitations)

---

## ✨ Features

- **Two-Player Gameplay**: Play against another player on the same machine
- **User Registration System**: Register new players or log in with existing user ID
- **Persistent User Data**: Users are stored in a HashMap-based database for session persistence
- **Toss Mechanism**: Fair coin toss to determine who plays first
- **Board State Tracking**: Uses optimized win detection with row, column, and diagonal counters
- **Win Detection**: Automatic detection of winning conditions and game-over scenarios
- **Console-Based UI**: Simple and interactive command-line interface

---

## 🗂️ Project Structure

```
tic-tac-toe/
├── src/
│   ├── Main.java                           # Entry point of the application
│   ├── Modal/                              # Model classes (Data entities)
│   │   ├── User.java                       # User entity class
│   │   ├── Game.java                       # Game entity class
│   │   └── Board.java                      # Board entity class
│   ├── controler/                          # Controller layer
│   │   └── GamerController.java            # Handles user interactions and game flow
│   ├── service/                            # Business logic layer
│   │   ├── GameService.java                # Game-related operations
│   │   └── UserService.java                # User-related operations
│   └── respository/                        # Data persistence layer
│       ├── GameDb.java                     # Game database operations
│       └── UserDb.java                     # User database operations
├── tictacktoe.iml                          # IntelliJ IDEA project configuration
└── README.md                               # Project documentation
```

---

## 🏗️ Architecture Overview

The project follows a **layered architecture** pattern:

```
┌─────────────────────────────────────────────┐
│          Main (Entry Point)                 │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│   GamerController (Controller Layer)        │
│   - User Input Handling                     │
│   - Game Flow Control                       │
│   - Toss Logic                              │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│  GameService & UserService (Service Layer) │
│  - Business Logic                          │
│  - Game Management                         │
│  - User Management                         │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│ GameDb & UserDb (Repository/DAO Layer)     │
│  - Data Persistence                        │
│  - CRUD Operations                         │
│  - HashMap Storage                         │
└─────────────────────────────────────────────┘
```

---

## 🎮 Game Logic

### Win Detection Algorithm

The game uses an **optimized win detection mechanism** that tracks:

1. **Row Counter**: Increments for each 'X' move, decrements for 'O' moves
   - Win condition: Row counter reaches ±3

2. **Column Counter**: Similar to row counter
   - Win condition: Column counter reaches ±3

3. **Main Diagonal Counter**: Tracks moves on the primary diagonal (top-left to bottom-right)
   - Triggered when: `rowNo == colNo`

4. **Anti-Diagonal Counter**: Tracks moves on the secondary diagonal (top-right to bottom-left)
   - Triggered when: `rowNo + colNo == 2`

### Win Conditions

```
Player 1 ('X') Wins When:
  - Any row has 3 consecutive 'X': rows[i] == 3
  - Any column has 3 consecutive 'X': cols[j] == 3
  - Main diagonal has 3 'X': dig == 3
  - Anti-diagonal has 3 'X': antiDig == 3

Player 2 ('O') Wins When:
  - Any row has 3 consecutive 'O': rows[i] == -3
  - Any column has 3 consecutive 'O': cols[j] == -3
  - Main diagonal has 3 'O': dig == -3
  - Anti-diagonal has 3 'O': antiDig == -3

Tie Condition:
  - All 9 moves are played without any player winning
```

### Game Board State

```
Initial Board (3x3):
  [empty] [empty] [empty]
  [empty] [empty] [empty]
  [empty] [empty] [empty]

Position Indexing (0-2):
  [0,0] [0,1] [0,2]
  [1,0] [1,1] [1,2]
  [2,0] [2,1] [2,2]

Example Game State:
  x     o     x
  o     x     empty
  o     empty empty
```

---

## 📚 Class Descriptions

### 1. **Main.java** (Entry Point)
```java
- Creates GamerController instance
- Calls startProgram() to initialize the game
- Acts as the application launcher
```

**Key Methods:**
- `main(String[] args)`: Application entry point

---

### 2. **User.java** (Modal - Data Entity)
```java
Represents a player in the game system
```

**Attributes:**
- `int id`: Unique identifier for the user
- `String name`: Player's name
- `List<Game> game`: List of games played by the user

**Key Methods:**
- `getId() / setId()`: Get/set user ID
- `getName() / setName()`: Get/set player name
- `getGame() / setGame()`: Get/set game history

**Constructors:**
- `User(int id, String name, List<Game> game)`: Parameterized constructor
- `User()`: Default constructor

---

### 3. **Board.java** (Modal - Game Board State)
```java
Manages the 3x3 tic-tac-toe board and win detection counters
```

**Attributes:**
- `char[][] board`: 3x3 game board storing 'x', 'o', or empty
- `int[] rows`: Array of 3 integers tracking row states (increment for X, decrement for O)
- `int[] cols`: Array of 3 integers tracking column states
- `int dig`: Counter for main diagonal (top-left to bottom-right)
- `int antiDig`: Counter for anti-diagonal (top-right to bottom-left)

**Key Methods:**
- `displayBoard()`: Prints the current board state to console
- Getters/Setters for all attributes

---

### 4. **Game.java** (Modal - Game Entity)
```java
Represents a single game session with metadata
```

**Attributes:**
- `int id`: Unique game identifier
- `LocalDateTime startingTime`: Timestamp when game started
- `User user1`: First player (goes first after toss)
- `User user2`: Second player
- `User Winner`: The winning player (null if tie)
- `Board board`: The game board instance

**Key Methods:**
- Getters/Setters for all attributes

---

### 5. **GamerController.java** (Controller Layer)
```java
Orchestrates user interactions and game flow
```

**Attributes:**
- `UserService userService`: Handles user operations
- `GameService gameService`: Handles game operations
- `Scanner scn`: Console input handler

**Key Methods:**

1. **`startProgram()`**: Main game flow orchestrator
   - Displays welcome message
   - Gets player details for both players
   - Performs coin toss to determine who plays first
   - Initiates game session

2. **`inputPlayerDetails(int playerNumber)`**: Player registration/login
   - Prompts new player registration or existing user login
   - For new users: Creates account with username
   - For existing users: Validates user ID
   - Returns User object

3. **`toss()`**: Fair coin toss implementation
   - Generates random number
   - Returns 0 (Player 1 wins) or 1 (Player 2 wins)
   - Formula: `Math.abs(random.nextInt()) % 2`

---

### 6. **GameService.java** (Service Layer - Business Logic)
```java
Encapsulates all game-related business logic
```

**Attributes:**
- `GameDb gameDB`: Database layer for game persistence

**Key Methods:**

1. **`createGame(User p1, User p2)`**: Creates new game instance
   - Delegates to GameDb.createGame()
   - Returns initialized Game object

2. **`startGame(Game game)`**: Main game loop and win detection
   - Takes game parameters from Game object
   - **Game Loop Logic:**
     - Iterates maximum 9 times (full board capacity)
     - Each iteration represents one player's move
     - Prompts for row and column input
     - Updates board state based on current player's symbol ('x' or 'o')

   - **Win Detection Logic:**
     ```
     For Player 1 ('X'):
       - Increment rows[rowNo], cols[colNo]
       - If rows[rowNo] == 3: Player 1 wins
       - If cols[colNo] == 3: Player 1 wins
       - If rowNo == colNo: Increment diagonal
       - If rowNo + colNo == 2: Increment anti-diagonal
     
     For Player 2 ('O'):
       - Decrement rows[rowNo], cols[colNo]
       - If rows[rowNo] == -3: Player 2 wins
       - If cols[colNo] == -3: Player 2 wins
       - Similar logic for diagonals
     ```

   - **Turn Management:**
     - Alternates between players
     - Switches symbol between 'x' and 'o'
     - Updates current player reference

   - **Game End Conditions:**
     - Immediate win detection (returns early)
     - All 9 moves exhausted: Declares tie

---

### 7. **UserService.java** (Service Layer - User Management)
```java
Handles user-related business operations
```

**Attributes:**
- `UserDb userDb`: User database layer

**Key Methods:**

1. **`validateUserId(int id)`**: Validates existing user
   - Queries UserDb for user by ID
   - Returns User object if found, null otherwise

2. **`createNewUser(String username)`**: Creates new user account
   - Delegates to UserDb.createNewUser()
   - Returns newly created User object

---

### 8. **GameDb.java** (Repository Layer - Game Persistence)
```java
Data access object for game persistence
```

**Attributes:**
- `HashMap<Integer, Game> gameMap`: In-memory game storage (key: game ID, value: Game object)

**Key Methods:**

1. **`createGame(User player1, User player2)`**: Creates and stores game
   - Instantiates new Game object
   - Creates new Board instance
   - Sets game ID: `gameMap.size() + 1`
   - Records starting time: `LocalDateTime.now()`
   - Sets player references
   - Stores in HashMap
   - Returns created Game object

**Data Structure:**
```
gameMap = {
  1: Game(p1, p2, board1, ...),
  2: Game(p1, p3, board2, ...),
  ...
}
```

---

### 9. **UserDb.java** (Repository Layer - User Persistence)
```java
Data access object for user persistence
```

**Attributes:**
- `HashMap<Integer, User> userMap`: In-memory user storage (key: user ID, value: User object)

**Key Methods:**

1. **`getUserById(int id)`**: Retrieves user by ID
   - Returns User object or null if not found

2. **`createNewUser(String name)`**: Creates new user
   - Creates new User instance
   - Sets user name
   - Calculates new ID: `userMap.size() + 1`
   - Initializes empty game list
   - Stores in HashMap
   - Returns created User object

**Data Structure:**
```
userMap = {
  1: User(id=1, name="Alice", games=[]),
  2: User(id=2, name="Bob", games=[]),
  ...
}
```

---

## 🚀 How to Run

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- IDE (IntelliJ IDEA, Eclipse, etc.) or command line

### Steps to Run

**Option 1: Using IDE (IntelliJ IDEA)**
1. Open the project in IntelliJ IDEA
2. Navigate to `src/Main.java`
3. Right-click and select "Run 'Main'"
4. Follow console prompts

**Option 2: Using Command Line**
```bash
# Compile the project
cd tic-tac-toe
javac -d out src/**/*.java

# Run the application
java -cp out Main
```

**Option 3: Direct Compilation**
```bash
# Navigate to src directory
cd src

# Compile all Java files
javac *.java controler/*.java service/*.java respository/*.java Modal/*.java

# Run the main class
java Main
```

---

## 🎯 Game Flow

```
┌─────────────────────────────────────────┐
│   Start Application (Main.java)         │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│ GamerController.startProgram()          │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│ Display Welcome Message                 │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│ inputPlayerDetails(1)                   │
│ - Register new or login existing        │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│ inputPlayerDetails(2)                   │
│ - Register new or login existing        │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│ toss() - Determine First Player         │
├──────────────────┬──────────────────────┤
│   Result: 0 or 1                        │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│ GameService.createGame(player1, player2)│
│ - Create game with board initialization │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│ GameService.startGame(game)             │
│ ┌──────────────────────────────────┐    │
│ │ GAME LOOP (max 9 iterations)     │    │
│ │                                  │    │
│ │ 1. Prompt current player         │    │
│ │ 2. Get row and column input      │    │
│ │ 3. Update board state            │    │
│ │ 4. Check win conditions:         │    │
│ │    - Row win                     │    │
│ │    - Column win                  │    │
│ │    - Diagonal win                │    │
│ │ 5. If win: Announce winner       │    │
│ │ 6. Display board                 │    │
│ │ 7. Switch player & symbol        │    │
│ │ 8. Increment move counter        │    │
│ │                                  │    │
│ └──────────────────────────────────┘    │
└──────────────────┬──────────────────────┘
                   │
        ┌──────────┴──────────┐
        │                     │
  ┌─────▼────┐         ┌─────▼────┐
  │   WIN    │         │   TIE    │
  │ Announce │         │ Announce │
  │ Winner   │         │   Tie    │
  └──────────┘         └──────────┘
        │                     │
        └──────────┬──────────┘
                   │
              ┌────▼────┐
              │ End Game│
              └─────────┘
```

---

## 💻 Technical Details

### Key Algorithms

#### 1. Win Detection Algorithm
```java
// For Player 1 (X):
if(ch == 'x') {
    board.getRows()[rowNo]++;           // Increment row counter
    if (board.getRows()[rowNo] == 3) {  // Check row win
        System.out.printf("%s won the game", currPlayer.getName());
        return;
    }
    board.getCols()[colNo]++;           // Increment column counter
    if (board.getCols()[colNo] == 3) {  // Check column win
        System.out.printf("%s won the game", currPlayer.getName());
        return;
    }
    if (rowNo == colNo) {                // Check main diagonal
        board.setDig(board.getDig() + 1);
    }
    if (rowNo + colNo == 2) {           // Check anti-diagonal
        board.setAntiDig(board.getAntiDig() + 1);
    }
}
```

#### 2. Toss Algorithm
```java
public int toss() {
    Random random = new Random();
    return Math.abs(random.nextInt()) % 2;  // Returns 0 or 1
}
```

#### 3. User ID Generation
```java
public User createNewUser(String name) {
    User user = new User();
    user.setName(name);
    int size = userMap.size();
    int id = size + 1;              // Auto-increment ID
    user.setId(size);
    user.setGame(new ArrayList<>());
    userMap.put(id, user);
    return user;
}
```

### Data Structures Used

1. **HashMap<Integer, User>**: O(1) average lookup for user retrieval
2. **HashMap<Integer, Game>**: O(1) average lookup for game retrieval
3. **ArrayList<Game>**: Dynamic list to store user's game history
4. **2D Array (char[][])**: 3x3 board representation
5. **1D Arrays (int[])**: Row and column counters for efficient win detection

### Time Complexity Analysis

| Operation | Complexity | Notes |
|-----------|-----------|-------|
| User lookup by ID | O(1) | HashMap lookup |
| Create new user | O(1) | HashMap insertion |
| Create new game | O(1) | HashMap insertion |
| Board update | O(1) | Array access |
| Win detection | O(1) | Counter comparison |
| Single game | O(9) | Maximum 9 moves |
| Game loop | O(1) per move | Constant operations |

### Space Complexity Analysis

| Component | Complexity | Details |
|-----------|-----------|---------|
| User storage | O(n) | n = number of users |
| Game storage | O(m) | m = number of games |
| Board | O(1) | Fixed 3x3 + 6 counters |
| Per game | O(1) | Constant size |

---

## ⚠️ Known Limitations

1. **ID Assignment Bug in UserDb**
   ```java
   // Current implementation has a bug:
   int size = userMap.size();     // Gets size
   int id = size + 1;              // Creates ID as size+1
   user.setId(size);               // But sets user ID as size (off by 1)
   userMap.put(id, user);          // Stores at size+1
   
   // This causes ID mismatch and potential issues in retrieval
   ```

2. **Board Display Issue**
   ```java
   // Current display doesn't show moves correctly:
   board.getBoard()[rowNo][count] = ch;  // Stores at wrong column
   // Should be: board.getBoard()[rowNo][colNo] = ch;
   ```

3. **No Input Validation**
   - Doesn't validate row/column input (0-2 range)
   - Doesn't prevent overwriting occupied cells
   - Doesn't handle non-integer inputs gracefully

4. **In-Memory Storage Only**
   - All data lost when application terminates
   - No persistent file storage
   - No database integration

5. **Case Sensitivity in User Input**
   - User responses must match exact case (e.g., "Yes" vs "yes")

6. **Limited Error Handling**
   - No try-catch blocks for input errors
   - No validation for player moves

7. **Diagonal Win Detection Logic**
   - Anti-diagonal logic appears flawed (increments for both players)
   - Should decrement for 'O' player

8. **User Symbol Assignment**
   - Both players can end up with same symbol in edge cases
   - Should use explicit player-to-symbol mapping

---

## 🔄 Suggested Improvements

1. **Fix ID Generation Bug**
   ```java
   int id = userMap.size() + 1;
   user.setId(id);  // Use same ID
   userMap.put(id, user);
   ```

2. **Add Input Validation**
   ```java
   if (rowNo < 0 || rowNo > 2 || colNo < 0 || colNo > 2) {
       System.out.println("Invalid position!");
       continue;
   }
   ```

3. **Implement Persistent Storage**
   - Add file I/O or database support (MySQL, SQLite)
   - Serialize game history

4. **Improve Error Handling**
   - Add try-catch blocks
   - Graceful handling of invalid inputs

5. **Add Move History**
   - Store all moves with timestamps
   - Allow game replay functionality

6. **Fix Diagonal Counters**
   - Correct anti-diagonal logic for 'O' player
   - Ensure symmetry in win detection

---

## 📝 License

This project is open-source and available for educational purposes.

---

## 👤 Author

**Yash2758**

---

## 📞 Support

For issues, suggestions, or improvements, please open an issue on the GitHub repository.

---

## 🎓 Educational Value

This project demonstrates:
- ✅ Object-Oriented Programming principles (Encapsulation, Inheritance, Polymorphism)
- ✅ Design patterns (MVC, Repository pattern, Service layer)
- ✅ Data structures (HashMap, ArrayList, 2D Arrays)
- ✅ Game logic implementation and win detection algorithms
- ✅ Console-based user interaction
- ✅ Code organization and layering
- ✅ Java fundamentals and best practices

---

**Happy Gaming! 🎮**
