import java.util.ArrayList;
import java.util.Scanner;

public class todo {
    static class Task {
        private String description;
        private boolean completed;

        public Task(String description) {
            this.description = description;
            this.completed = false;
        }

        public String getDescription() {
            return description;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void markAsCompleted() {
            this.completed = true;
        }
    }

    private static ArrayList<ArrayList<Task>> todoLists = new ArrayList<>();
    private static ArrayList<String> listNames = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Todo list");
            System.out.println("1. Create todo list");
            System.out.println("2. Use todo list");
            System.out.println("3. Remove todo list");
            System.out.println("4. Quit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createTodoList();
                    break;
                case 2:
                    useTodoList();
                    break;
                case 3:
                    removeTodoList();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
                    System.out.println(" ");
            }
        }
    }

    private static void createTodoList() {
        System.out.print("Enter name for new todo list: ");
        String name = sc.nextLine();
        ArrayList<Task> todoList = new ArrayList<>();
        todoLists.add(todoList);
        listNames.add(name);
        System.out.println("Todo list '" + name + "' created.");
        manageTodoList(todoList);
        System.out.println(" ");
    }

    private static void useTodoList() {
        System.out.println("List of todo lists:");
        for (int i = 0; i < listNames.size(); i++) {
            System.out.println((i + 1) + ". " + listNames.get(i));
        }
        System.out.print("Choose list number: ");
        int listIndex = sc.nextInt() - 1;
        sc.nextLine(); // Consume newline

        if (listIndex >= 0 && listIndex < todoLists.size()) {
            ArrayList<Task> todoList = todoLists.get(listIndex);
            displayTasks(todoList);
            System.out.println("------------------------- ");
            manageTodoList(todoList);
        } else {
            System.out.println("Todo list not found.");
        }
    }

    private static void removeTodoList() {
        System.out.println("List of todo lists:");
        for (int i = 0; i < listNames.size(); i++) {
            System.out.println((i + 1) + ". " + listNames.get(i));
        }
        System.out.print("Choose list number to remove: ");
        int listIndex = sc.nextInt() - 1;
        sc.nextLine(); //new line kha jayega

        if (listIndex >= 0 && listIndex < todoLists.size()) {
            String removedListName = listNames.remove(listIndex);
            todoLists.remove(listIndex);
            System.out.println("Todo list '" + removedListName + "' removed.");
        } else {
            System.out.println("Todo list not found.");
        }
    }

    private static void manageTodoList(ArrayList<Task> todoList) {
        while (true) {
            System.out.println(" ");
            displayTasks(todoList);
            System.out.println("1. Add tasks");
            System.out.println("2. Remove task");
            System.out.println("3. Mark as completed");
            System.out.println("4. Quit");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addTasks(todoList);
                    break;
                case 2:
                    removeTask(todoList);
                    break;
                case 3:
                    markTasksAsCompleted(todoList);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    System.out.println(" ");
            }
        }
    }

    private static void displayTasks(ArrayList<Task> todoList) {
        System.out.println(" ");
        System.out.println("Current tasks:");
        for (int i = 0; i < todoList.size(); i++) {
            Task task = todoList.get(i);
            String status = task.isCompleted() ? "✓" : "";
            System.out.println((i + 1) + ". " + task.getDescription() + " " + status);
        }
    }

    private static void addTasks(ArrayList<Task> todoList) {
        System.out.println("Enter tasks (type 'done' to finish):");
        while (true) {
            String taskName = sc.nextLine();
            if (taskName.equalsIgnoreCase("done")) {
                break;
            }
            todoList.add(new Task(taskName));
            System.out.println("Task '" + taskName + "' added.");
        }
    }

    private static void removeTask(ArrayList<Task> todoList) {
        System.out.print("Enter task number to remove: ");
        int taskIndex = sc.nextInt() - 1;
        sc.nextLine(); //new line kha jayega

        if (taskIndex >= 0 && taskIndex < todoList.size()) {
            Task removedTask = todoList.remove(taskIndex);
            System.out.println("Task '" + removedTask.getDescription() + "' removed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private static void markTasksAsCompleted(ArrayList<Task> todoList) {
        displayTasks(todoList);
        System.out.print("Enter task numbers to mark as completed (separated by spaces): ");
        String[] indices = sc.nextLine().split(" "); // ek sath sari choices lene k liye

        for (String indexStr : indices) {
            try {
                int taskIndex = Integer.parseInt(indexStr) - 1;
                if (taskIndex >= 0 && taskIndex < todoList.size()) {
                    Task task = todoList.get(taskIndex);
                    task.markAsCompleted();
                    System.out.println("Task '" + task.getDescription() + "' marked as completed (✓).");
                } else {
                    System.out.println("Invalid task number: " + (taskIndex + 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + indexStr);
            }
        }
    }
}
