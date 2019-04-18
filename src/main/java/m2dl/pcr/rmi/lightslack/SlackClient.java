package m2dl.pcr.rmi.lightslack;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class SlackClient implements IClient{

    private IServer server;

    private UUID name;

    public SlackClient(IServer server, UUID name) {
        this.server = server;
        this.name = name;
    }

    public void launch() throws RemoteException, NotBoundException {
        server.connect(name);
        showAllMessages();
        new Thread(new Runnable() {
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while(true){
                    try {
                        String text = scanner.nextLine();
                        Message message = new Message(text, name.toString());
                        server.sendMessage(message);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void showAllMessages() throws RemoteException {
        List<Message> messages = server.getMessages();
        System.out.println("----------------------------------------------------");
        for (Message message: messages){
            System.out.println(message.getSender() + " : " + message.getContent());
        }
        System.out.println("----------------------------------------------------");
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        UUID uuid = UUID.randomUUID();
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            IServer stub = (IServer) registry.lookup("IServer");

            SlackClient client = new SlackClient(stub, uuid);

            IClient clientUnicast = (IClient) UnicastRemoteObject.exportObject(client, 0);
            registry.bind(uuid.toString(), clientUnicast);
            client.launch();
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void notifyClient() throws RemoteException {
        showAllMessages();
    }
}
