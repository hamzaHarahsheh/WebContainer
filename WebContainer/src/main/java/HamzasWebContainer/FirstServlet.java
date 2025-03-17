package HamzasWebContainer;

import HamzasWebContainer.Container.HttpServlet;
import HamzasWebContainer.Container.Request;
import HamzasWebContainer.Container.Response;

import java.io.PrintWriter;
import java.util.Random;

public class FirstServlet extends HttpServlet {
    private int targetNumber;
    private int attempts;

    @Override
    public void init() {
        System.out.println("FirstServlet init");
        targetNumber = new Random().nextInt(100) + 1;
        attempts = 0;
    }

    @Override
    public void doGet(Request request, Response response) {
        PrintWriter outputStream = response.getPrintWriter();
        outputStream.println("<html><body>");
        outputStream.println("<h1>Guess the Number Game</h1>");
        outputStream.println("<p>I'm thinking of a number between 1 and 100. Can you guess what it is?</p>");
        outputStream.println("<form method=\"post\">");
        outputStream.println("Your Guess: <input type=\"text\" name=\"guess\"><br>");
        outputStream.println("<input type=\"submit\" value=\"Submit\">");
        outputStream.println("</form>");
        outputStream.println("</body></html>");
    }

    @Override
    public void doPost(Request request, Response response) {
        PrintWriter outputStream = response.getPrintWriter();
        int guess = Integer.parseInt(request.getParameter("guess"));
        attempts++;
        outputStream.println("<html><body>");
        outputStream.println("<h1>Guess the Number Game</h1>");
        if (guess < targetNumber) {
            outputStream.println("<p>Your guess is too low. Try again!</p>");
        } else if (guess > targetNumber) {
            outputStream.println("<p>Your guess is too high. Try again!</p>");
        } else {
            outputStream.println("<p>Congratulations! You guessed the number in " + attempts + " attempts!</p>" + "<p>Next time use Binary Search <3 </p>");
            targetNumber = new Random().nextInt(100) + 1;
            attempts = 0;
        }
        outputStream.println("<form method=\"post\">");
        outputStream.println("Your Guess: <input type=\"text\" name=\"guess\"><br>");
        outputStream.println("<input type=\"submit\" value=\"Submit\">");
        outputStream.println("</form>");
        outputStream.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("FirstServlet destroy");
    }
}