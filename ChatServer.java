import java.io.*;
import java.net.*;
import java.util.Vector;

public class ChatServer {

    // To store all connected clients
    static Vector<ClientHandler> clients = new Vector<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server started...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                ClientHandler client = new ClientHandler(socket);
                clients.add(client);
                client.start(); // start thread
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Thread class for handling each client
    static class ClientHandler extends Thread {
        Socket socket;
        BufferedReader in;
        PrintWriter out;

        ClientHandler(Socket socket) {
            this.socket = socket;
            try {
                in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(
                        socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String message;
            try {
                while ((message = in.readLine()) != null) {
                    // Send message to all clients
                    for (ClientHandler c : clients) {
                        c.out.println(message);
                    }
                }
            } catch (IOException e) {
                System.out.println("Client disconnected");
            }
        }
    }
}