# Anis User Guide

![Anis Logo](./docs/images/anis.png)

Anis is a friendly, command-line task management chatbot. It helps you keep track of **Todos, Deadlines, and Events**, mark and unmark them as done, delete tasks, list all tasks, and search for tasks by keyword. All your tasks are automatically saved to disk between sessions.

---

## Quick Start

1. **Ensure Java 17 or above is installed**  
   Run `java -version` in your terminal to check.

2. **Open the project in your IDE**  
   Recommended: IntelliJ IDEA, Eclipse, or VS Code with Java support.

3. **Run Anis**  
   Run the `anis.Anis` main class. You will see a welcome message and a prompt.

4. **Start using commands**  
   Type a command (e.g., `todo read book`) and press **Enter**.  
   Commands are **case-insensitive** and task indexes start at **1**.

---

## Features

- Commands are case-insensitive for the command word (e.g., `LIST` works), but arguments retain their case.
- Indexes in task lists are **1-based**.
- Anis automatically saves your tasks, so you don’t need to worry about losing data.

---

### List tasks: `list`
Displays all tasks currently in your task list.

**Example:** `list`

**Output:**
```markdown
Here are the tasks in your list:
1.[T][ ] read book
2.[D][ ] return book (by: Sep 20 2025)
```

---

### Add a Todo: `todo <description>`
Adds a simple task without a specific time.

**Example:** `todo read book`

**Output:**
```markdown
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.
```

---

### Add a Deadline: `deadline <description> /by <yyyy-MM-dd>`
Adds a task due by a specific date.

**Example:**  `deadline return book /by 2025-09-20`

**Output:**
```markdown
Got it. I've added this task:
[D][ ] return book (by: Sep 20 2025)
Now you have 2 tasks in the list.
```

---

### Add an Event: `event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>`
Adds a task that spans a period of time.

**Example:**  `event project meeting /from 2025-09-28 /to 2025-09-29`

**Output:**
```markdown
Got it. I've added this task:
[E][ ] project meeting (from: Sep 28 2025 to: Sep 29 2025)
Now you have 3 tasks in the list.
```

---

### Mark a task as done: `mark <index>`
Marks the specified task as completed.

**Example:**  `mark 1`

**Output:**
```markdown
Nice! I've marked this task as done:
[T][X] read book
```

---

### Unmark a task: `unmark <index>`
Marks the specified task as not done.

**Example:** `unmark 1`

**Output:**
```markdown
OK, I've marked this task as not done yet:
[T][ ] read book
```

---

### Delete a task: `delete <index>`
Removes the specified task from the list.

**Example:**  `delete 2`

**Output:**  
```markdown
Noted. I've removed this task:
[D][ ] return book (by: Sep 20 2025)
Now you have 2 tasks in the list.
```

---

### Find tasks: `find <keyword>`
Shows tasks whose descriptions contain the keyword (case-insensitive).

**Example:**  `find book`

**Output:**
```markdown
Here are the matching tasks in your list:
1.[T][ ] read book
```

---

### Exit: `bye`
Exits Anis.

**Example:** `bye`

**Output:**
```markdown
Glad I could assist! Have a wonderful day.
Feel free to reach out anytime.
```

---

## Data Storage

- Tasks are saved in `data/anis.txt` automatically.
- Each task is stored on one line in a simple format:
  - `T` → Todo, `D` → Deadline, `E` → Event
  - `marked` is `1` if done, `0` if not
  - `additional info` is used for deadlines and events

- The file is updated automatically whenever you add, mark, unmark, or delete a task.
- You usually do **not** need to edit this file manually.

---

## Common User Errors

- **Empty description:**  `todo` → "The description of a todo cannot be empty."
- **Missing parts:**  `deadline return book` → "Please use the format: deadline <desc> /by yyyy-MM-dd"
- **Invalid index:**  `delete 200` → "Invalid task number."
- **Unknown command:** Any unrecognized command → "I'm sorry, I don't understand that command."

---

## Command Summary

- `list`
- `todo <description>`
- `deadline <description> /by <yyyy-MM-dd>`
- `event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>`
- `mark <index>`
- `unmark <index>`
- `delete <index>`
- `find <keyword>`
- `bye`