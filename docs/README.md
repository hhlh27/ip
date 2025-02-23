# DazAi User Guide


![image](https://github.com/user-attachments/assets/ff2e71fa-4387-40c7-abf1-1eeb9b461f41)




Welcome to **DazAi**, your personal assistant chatbot! DazAi is designed to help you manage tasks efficiently and interact with ease using simple text commands.
## 1. Adding Tasks
You can add different types of tasks to DazAi:

- **To-Do**: Add a simple task.
  - Command: `todo <task description>`
  - Example: `todo Read book`

- **Deadline**: Add a task with a due date.
  - Command: `deadline <task description> /by <yyyy-mm-dd HHmm>`
  - Example: `deadline Submit assignment /by 2025-02-20 1800`

- **Event**: Add a task with a scheduled date.
  - Command: `event <task description> /from <yyyy-mm-dd HHmm /to <yyyy-mm-dd HHmm>`
  - Example: `event Attend camp /from 2025-02-21 1600 /to 2025-02-23 1600`
    
The task will be added, and the output will look something like this:

```plaintext
  Added: [T][] Read book
```
- **Tagging Tasks**: To add a tag to a task, use `#<tagname>`.
  - Example: `todo finish essay #urgent`
  The task with tag will be added, and the output will look something like this:

```plaintext
  Added: [T][] finish essay #urgent
```
  

## 2a. Marking Tasks as Done

Mark tasks as completed using the task number:

- Command: `mark <task number>`
- Example: `mark 2`
The task will be marked as completed, and the output will look something like this:

```plaintext
Nice! I've marked this task as done:
  [T][X] Submit assignment
```

## 2b. Unmark Task

Unmark task using the task number:

- Command: `unmark <task number>`
- Example: `unmark 2`
The task will be reverted to its original state, and the output will be:

```plaintext
OK, I've unmarked this task as not done yet:
  [T][ ] Submit assignment
```

## 3. Listing Tasks

View all tasks currently in your list:

- Command: `list`
Example output:

```plaintext

Here are the tasks in your list:
1. [T][ ] Read book
2. [D][ ] Submit assignment (by: Feb 01 2025, 6:00pm)
3. [E][ ] Attend meeting (at: Feb 08 2025, 6:00pm)
```
### 4. Deleting Tasks

Remove tasks from the list using the task number:

- Command: `delete <task number>`
- Example: `delete 1`
The task will be removed, and the output will be:

```plaintext

Noted! I've removed this task:
  [T][ ] Read book
```

### 6. Searching for Tasks

Search for tasks containing specific keywords:

- Command: `find <keyword>`
- Example: `find book`
The output will list all tasks containing the keyword:

```plaintext

Here are your matching tasks:
1. [T][ ] Read book
```

### 7. Exiting the Chatbot

Close Duke and save your tasks:

- Command: `bye`
The chatbot will save your tasks and exit.


---


