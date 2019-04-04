package m2dl.pcr.rmi.lightslack;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class SlackClient {

    private IServer server;

    private String name;

    public SlackClient(IServer server, String name) {
        this.server = server;
        this.name = name;
    }

    public void launch() throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        while(true){
            showAllMessages();
            String text = scanner.nextLine();
            Message message = new Message(text, name);
            server.sendMessage(message);
        }
    }

    private void showAllMessages() throws RemoteException {
        List<Message> messages = server.getMessages();
        for (Message message: messages){
            System.out.println(message.getSender() + " : " + message.getContent());
        }
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        String name = "Jean";
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            IServer stub = (IServer) registry.lookup("IServer");

            SlackClient client = new SlackClient(stub, name);
            client.launch();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
