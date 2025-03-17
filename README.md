HamzasWebContainer
HamzasWebContainer is a simple web container implemented in Java. It supports basic HTTP request handling and servlet management.


Features
Basic HTTP request parsing
Servlet lifecycle management (init, service, destroy)
Multi-threaded request handling using a thread pool
Configurable servlet mappings
Project Structure
src/main/java/HamzasWebContainer/Container/HttpServlet.java: Abstract class defining the basic lifecycle methods for servlets.
src/main/java/HamzasWebContainer/Container/Request.java: Class for parsing HTTP requests.
src/main/java/HamzasWebContainer/Container/Response.java: Class for handling HTTP responses.
src/main/java/HamzasWebContainer/Container/WebContainer.java: Main class for starting the web container and managing servlets.
src/main/java/HamzasWebContainer/SocketHandler.java: Class for managing individual client connections.
src/main/java/HamzasWebContainer/FirstServlet.java: Example servlet implementing a number guessing game.
src/main/java/HamzasWebContainer/SecondServlet.java: Example servlet with form handling and animated heart display.
Getting Started
Prerequisites
Java 8 or higher
Maven
Building the Project
Clone the repository:


git clone https://github.com/hamzaHarahsheh/HamzasWebContainer.git
cd HamzasWebContainer
Build the project using Maven:


mvn clean install
Running the Web Container
Create a configuration file config in the resources directory with the following content:


/Bingo.bango.bongo.bish.bash.bosh=HamzasWebContainer.FirstServlet
/Easy.peasy.lemon.squeezy=HamzasWebContainer.SecondServlet
Run the WebContainer class:


mvn exec:java -Dexec.mainClass="HamzasWebContainer.Container.WebContainer"
The web container will start on port 8080 and load the servlets defined in the configuration file.


Accessing the Servlets
Open a web browser and navigate to http://localhost:8080/Bingo.bango.bongo.bish.bash.bosh to access the FirstServlet.
Navigate to http://localhost:8080/Easy.peasy.lemon.squeezy to access the SecondServlet.
