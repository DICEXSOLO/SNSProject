package guessinggame;

import java.io.*;
import java.net.*;
import java.util.Random;

public class GuessingGameServer {
    private static final int PORT = 12345;
    private static final int MIN = 1;
    private static final int MAX = 100;

    public static void main(String[] args) {
        System.out.println("Guessing Game Server is running...");
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    int targetNumber = new Random().nextInt((MAX - MIN) + 1) + MIN;
                    System.out.println("Generated number: " + targetNumber);

                    out.println("Game started! Guess a number between " + MIN + " and " + MAX + ".");
                    String clientGuess;

                    while ((clientGuess = in.readLine()) != null) {
                        try {
                            int guess = Integer.parseInt(clientGuess);

                            if (guess < targetNumber) {
                                out.println("Too low!");
                            } else if (guess > targetNumber) {
                                out.println("Too high!");
                            } else {
                                out.println("Correct! You've guessed the number.");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            out.println("Please enter a valid number.");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Connection error: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

