package HamzasWebContainer.Container;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
public class SocketHandler extends Thread {
    private Socket socket;
    private Map<String, HttpServlet> handlers = new HashMap<>();

    public SocketHandler(Socket socket, Map<String, HttpServlet> handlers) {
        this.socket = socket;
        this.handlers = handlers;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Request request = new Request(reader);
            if(!request.parse()) {
                PrintWriter outputStream = new PrintWriter(socket.getOutputStream());
                outputStream.println("HTTP/1.1 500 Server Error");
                outputStream.println("Content-Type: text/plain");
                outputStream.println();
                outputStream.println("<html><body><h1>500 Server Error</h1></body></html>");
                outputStream.flush();
            }else {
                HttpServlet servlet = handlers.get(request.getPath());
                if (servlet == null) {
                    PrintWriter outputStream = new PrintWriter(socket.getOutputStream());
                    outputStream.println("HTTP/1.1 404 Not Found");
                    outputStream.println("Content-Type: text/html");
                    outputStream.println();
                    outputStream.println("<html><body><h1>404 Not Found</h1></body></html>");
                    outputStream.flush();
                } else {
                    Response response = new Response(socket.getOutputStream());
                    PrintWriter outputStream = response.getPrintWriter();
                    outputStream.println("HTTP/1.1 200");
                    outputStream.println("Content-Type: text/html");
                    outputStream.println();
                    servlet.service(request, response);
                    outputStream.flush();
                }
            }
        }catch (IOException exception) {
            exception.printStackTrace();
        }finally {
            try {
                socket.close();
            }catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
