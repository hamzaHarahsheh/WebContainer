package HamzasWebContainer;

import HamzasWebContainer.Container.HttpServlet;
import HamzasWebContainer.Container.Request;
import HamzasWebContainer.Container.Response;

import java.io.PrintWriter;

public class SecondServlet extends HttpServlet{

    @Override
    public void init() {
        System.out.println("SecondServlet init");
    }

    @Override
    public void doGet(Request request, Response response) {
        PrintWriter outputStream = response.getPrintWriter();
        outputStream.println("<html><body>");
        outputStream.println("<form method=\"post\">");
        outputStream.println("First Name: <input type=\"text\" id=\"fname\" name=\"fname\" value=\"lifeIs\"><br>");
        outputStream.println("Last Name: <input type=\"text\" id=\"lname\" name=\"lname\" value=\"Hard\"><br><br>");
        outputStream.println("<input type=\"submit\" value=\"Submit\">");
        outputStream.println("</body></html>");
    }

    @Override
    public void doPost(Request request, Response response) {
        PrintWriter outputStream = response.getPrintWriter();
        outputStream.println("<html><body>");
        outputStream.println("<p>First Name: " + request.getParameter("fname") + "</p>");
        outputStream.println("<p>Last Name: " + request.getParameter("lname") + "</p>");
        outputStream.println("</body></html>");
    }

    @Override
    public void destroy() {
        System.out.println("SecondServlet destroy");
    }
}
