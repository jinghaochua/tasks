package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;


import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("\nPrinting all data ...");
        printAllData(tasksData);

        printDeadlinesUsingParallelStreams(tasksData);
        printDeadlinesUsingStreams(tasksData);

        System.out.println("\nPrinting deadlines ...");
        printDeadlines(tasksData);
        printDeadlinesUsingStreamsWithMethodReference(tasksData);

        System.out.println("\nTotal number of deadlines: " + countDeadlinesUsingStreams(tasksData));

        ArrayList<Task> filteredTasks = filterTasksByString(tasksData, "10");
        printAllData(filteredTasks);

    }

    private static int countDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        return (int) tasksData.stream()
                .filter(t -> t instanceof Deadline)
                .count();
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlinesUsingParallelStreams(ArrayList<Task> tasksData) {
        System.out.println("\nPrinting deadlines using parallel streams ...");
        tasksData.parallelStream()
                .filter(t -> t instanceof Deadline)
                .forEach(System.out::println);
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("\nPrinting deadlines using streams ...");
        tasksData.stream()
                .filter(t -> t instanceof Deadline)
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStreamsWithMethodReference(ArrayList<Task> tasksData) {
        System.out.println("\nPrinting deadlines using streams with method reference ...");
        tasksData.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTasksByString(ArrayList<Task> tasksData, String filterString) {
        System.out.println("\nFiltering tasks by string: " + filterString);
        return (ArrayList<Task>) tasksData.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(filterString.toLowerCase()))
                .collect(toList());
    }

}
