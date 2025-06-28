# Task Tracker

A CLI app to track your tasks and manage your to-do list.<br>
Project idea: https://roadmap.sh/projects/task-tracker

## How to run the project
```
javac *.java
```

### Adding a new task
```
java Main add "Buy groceries"
```

### Updating and deleting tasks
```
java Main update 1 "Buy groceries and cook dinner"
java Main delete 1
```

### Marking a task as in progress or done
```
java Main mark-in-progress 1
java Main mark-done 1
```

### Listing all tasks
```
java Main list
```

### Listing tasks by status
``
java Main list done
java Main list todo
java Main list in-progress
```