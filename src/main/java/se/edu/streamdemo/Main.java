package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        //printAllData(tasksData);
        printDeadlinesUsingParallelStreams(tasksData);
        printDeadlinesUsingStreams(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlinesUsingStreams(tasksData));

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
        System.out.println("Printing deadlines using parallel streams ...");
        tasksData.parallelStream()
                .filter(t -> t instanceof Deadline)
                .forEach(System.out::println);
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines using streams ...");
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

}
