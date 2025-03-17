package HamzasWebContainer.Container;

public abstract class HttpServlet {

    public void init() {
        System.out.println("HttpServlet init");
    }

    public void service(Request request, Response response) {
        if (request.getMethod().equals("GET")) {
            this.doGet(request, response);
        } else if (request.getMethod().equals("POST")) {
            this.doPost(request, response);
        }else {
            throw  new RuntimeException("IDK what u r talking about ):");
        }
    }

    public void doGet(Request request, Response response) {
        System.out.println("HttpServlet doGet");
    }

    public void doPost(Request request, Response response) {
        System.out.println("HttpServlet doPost");
    }

    public void destroy() {
        System.out.println("HttpServlet destroy");
    }

}
