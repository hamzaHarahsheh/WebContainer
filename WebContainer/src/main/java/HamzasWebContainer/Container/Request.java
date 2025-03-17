package HamzasWebContainer.Container;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private BufferedReader reader;
    private String method;
    private String path;
    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> parameters = new HashMap<>();

    public Request(BufferedReader reader) {
        this.reader = reader;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHeaders(String key) {
        return headers.get(key);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }


    private void parseParameters(String parameter) {
        for(String param : parameter.split("&")) {
            String[] keyValue = param.split("=");
            parameters.put(keyValue[0], keyValue[1]);
        }
    }
    public boolean parse() throws IOException {
        String line = reader.readLine();
        String[] parts = line.split(" ");
        if(parts.length != 3) {
            return false;
        }

        method = parts[0];
        String url = parts[1];
        int idx = url.indexOf("?");
        if(idx != -1) {
            path = url.substring(0, idx);
            parseParameters(url.substring(idx + 1));
        }else {
            path = url;
        }

        while((line = reader.readLine()) != null && !line.isEmpty()) {
           String[] header = line.split(":");
           headers.put(header[0], header[1]);
        }

        if(method.equals("POST")) {
            StringBuilder requestBody = new StringBuilder();
            while(reader.ready()) {
                requestBody.append((char)reader.read());
            }
            parseParameters(requestBody.toString());
        }

        return true;
    }

}
