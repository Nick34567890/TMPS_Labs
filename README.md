# Grocery List CLI — 3 SOLID Principles Demo

A simple command-line grocery list application demonstrating **Single Responsibility**, **Dependency Inversion**, and **Open/Closed** principles in Java.

## Quick Start

```powershell
# Create Program (class)
javac -d target/classes (Get-ChildItem -Path .\com\grocery -Recurse -Filter *.java | ForEach-Object { $_.FullName })

# Basic usage
java -cp "target/classes" grocery.GroceryApp add "Coconut"
java -cp "target/classes" grocery.GroceryApp add "Kiwi"
java -cp "target/classes" grocery.GroceryApp list
java -cp "target/classes" grocery.GroceryApp check 1
java -cp "target/classes" grocery.GroceryApp remove 2
```

## Commands

- `add "item name"` — Add item to list
- `list` — Show all items  
- `check <id>` — Mark item as bought ✓
- `remove <id>` — Delete item
- `clear` — Remove all items
- `help` — Show usage

## SOLID Principles Applied

**Single Responsibility Principle (SRP)**
- `Item.java` → Data model only
- `Storage.java` → Storage interface only  
- `App.java` → Business logic only
- `GroceryApp.java` → CLI parsing only

**Dependency Inversion Principle (DIP)**
- `App` depends on `Storage` interface, not concrete implementations
- Tests use `MemoryStorage`, production uses `FileStorage`
- Same app code works with any storage type

**Open/Closed Principle (OCP)**
- Add new storage (DatabaseStorage) without changing existing code
- Add new commands without modifying core business logic

## File Structure

```
src/main/java/com/grocery/
├── Item.java          # Data model
├── Storage.java       # Storage interface  
├── MemoryStorage.java # In-memory implementation
├── FileStorage.java   # File-based implementation
├── App.java           # Core business logic
└── GroceryApp.java    # CLI entry point

src/test/java/com/grocery/
└── AppTest.java       # Unit tests using MemoryStorage
```

## Data Storage

Items are saved to `grocery_list.txt` in pipe-separated format:
```
1|Coconut|false|2025-10-10T16:59:18
2|Kiwi|true|2025-10-10T17:05:24
```

The beauty of SOLID design: swap storage types without changing business logic!

If you want, I can now scaffold this project in Python (files + tests + basic implementation) or provide a single-file implementation that still follows these principles in a lightweight way. Tell me which you prefer.
- A `DiscountCalculator` interface is created.

## Results of program
<img width="1711" height="617" alt="image" src="https://github.com/user-attachments/assets/20f698cf-530a-4d8c-b350-4f4d66c3424a" />

