import java.util.ArrayList;
import java.util.Scanner;

public class GradeCalculator {

  public static void main(String[] args) {
    ArrayList<Double> grades = new ArrayList<>();

    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        System.out.println("1. Enter a grade");
        System.out.println("2. Compute average, highest, and lowest scores");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();

        switch (option) {
          case 1 -> addGrade(grades, scanner);
          case 2 -> calculateAndPrintScores(grades);
          case 3 -> {
            System.out.println("Exiting...");
            return;
          }
          default -> System.out.println("Invalid option. Please choose a valid option.");
        }
      }
    }
  }

  private static void addGrade(ArrayList<Double> grades, Scanner scanner) {
    System.out.print("Enter a grade: ");
    double grade = scanner.nextDouble();
    grades.add(grade);
  }

  private static void calculateAndPrintScores(ArrayList<Double> grades) {
    if (grades.isEmpty()) {
      System.out.println("No grades entered yet.");
    } else {
      double average = calculateAverage(grades);
      double highest = findHighest(grades);
      double lowest = findLowest(grades);

      System.out.println("Average score: " + average);
      System.out.println("Highest score: " + highest);
      System.out.println("Lowest score: " + lowest);
    }
  }

  private static double calculateAverage(ArrayList<Double> grades) {
    double sum = 0;
    for (double grade : grades) {
      sum += grade;
    }
    return sum / grades.size();
  }

  private static double findHighest(ArrayList<Double> grades) {
    return grades.stream().max(Double::compare).orElse(0.0);
  }

  private static double findLowest(ArrayList<Double> grades) {
    return grades.stream().min(Double::compare).orElse(0.0);
  }
}