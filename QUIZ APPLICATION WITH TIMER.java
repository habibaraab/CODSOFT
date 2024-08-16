/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quizapp;

/**
 *
 * @Habiba Rajab
 * 
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    List<String> options;
    int correctAnswerIndex;

    public Question(String question, List<String> options, int correctAnswerIndex) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }
}

public class QuizApp {
    private static List<Question> questions = new ArrayList<>();
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static final int TIME_LIMIT = 10; // in seconds

    public static void main(String[] args) {
        // Sample questions
        loadQuestions();

        Scanner scanner = new Scanner(System.in);
        
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            askQuestion(scanner);
        }
        
        showResults();
        scanner.close();
    }

    private static void loadQuestions() {
        List<String> options1 = List.of("Paris", "London", "Berlin", "Madrid");
        questions.add(new Question("What is the capital of France?", options1, 0));

        List<String> options2 = List.of("5", "6", "7", "8");
        questions.add(new Question("What is 2 + 3?", options2, 0));

        List<String> options3 = List.of("Mercury", "Venus", "Earth", "Mars");
        questions.add(new Question("Which planet is closest to the Sun?", options3, 0));

        List<String> options4 = List.of("Shakespeare", "Hemingway", "Dickens", "Austen");
        questions.add(new Question("Who wrote 'Hamlet'?", options4, 0));

        List<String> options5 = List.of("Water", "Oxygen", "Carbon Dioxide", "Hydrogen");
        questions.add(new Question("What is the chemical formula for water?", options5, 0));
    }

    private static void askQuestion(Scanner scanner) {
        Question question = questions.get(currentQuestionIndex);
        System.out.println("\n" + question.question);
        for (int i = 0; i < question.options.size(); i++) {
            System.out.println((i + 1) + ": " + question.options.get(i));
        }

        // Timer for the question
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime is up! Moving to the next question.");
                // Moving to next question
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.size()) {
                    askQuestion(scanner);
                } else {
                    showResults();
                }
                System.exit(0);
            }
        }, TIME_LIMIT * 1000);

        // user input
        int answer = -1;
        try {
            answer = Integer.parseInt(scanner.nextLine()) - 1;
            timer.cancel(); // Cancel the timer if answered in time
        } catch (Exception e) {
            System.out.println("Invalid input. You missed the question.");
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                askQuestion(scanner);
            } else {
                showResults();
            }
            return;
        }

        // Check the answer
        if (answer == question.correctAnswerIndex) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect! The correct answer was: " + question.options.get(question.correctAnswerIndex));
        }

        if (currentQuestionIndex < questions.size()) {
            askQuestion(scanner);
        } else {
            showResults();
        }
    }

    private static void showResults() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your score: " + score + "/" + questions.size());
        System.out.println("Thank you for playing!");
    }
}
