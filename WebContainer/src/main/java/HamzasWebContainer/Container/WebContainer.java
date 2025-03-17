package HamzasWebContainer.Container;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebContainer {

    private final int port;
    private final String config;
    private final ExecutorService threadPool;
    private Map<String, HttpServlet> handlers = new HashMap<>();

    public WebContainer(int port, String config) {
        this.port = port;
        this.config = config;
        this.threadPool = Executors.newFixedThreadPool(12);
    }

    private void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket socket = serverSocket.accept();
            threadPool.submit(new SocketHandler(socket, handlers));
        }
    }

    private void loadconfigs() throws IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream(config);
        if (input == null) {
            throw new RuntimeException("IDK what u r talking about ):");
        }

        Properties prop = new Properties();
        prop.load(input);
        prop.forEach((k, v) -> {
            HttpServlet servlet = getServletInstances((String) v);
            servlet.init();
            handlers.put((String) k, servlet);
        });
    }

    public HttpServlet getServletInstances(String servletName) {
        try {
            return (HttpServlet) Class.forName(servletName).getDeclaredConstructor().newInstance();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        WebContainer webContainer = new WebContainer(8080, "config");
        webContainer.loadconfigs();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                webContainer.handlers.forEach((key, servlet) -> servlet.destroy());
            }
        });
        webContainer.start();
    }
}