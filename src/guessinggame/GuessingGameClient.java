package guessinggame;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GuessingGameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to the Guessing Game Server.");
            System.out.println(in.readLine());

            while (true) {
                System.out.print("Enter your guess: ");
                String guess = scanner.nextLine();
                out.println(guess);

                String response = in.readLine();
                System.out.println("Server response: " + response);

                if ("Correct! You've guessed the number.".equals(response)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

