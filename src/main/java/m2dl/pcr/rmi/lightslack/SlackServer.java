package m2dl.pcr.rmi.lightslack;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class SlackServer implements IServer{

    private List<Message> messages = new ArrayList<Message>();
    private Map<UUID, IClient> clients = new HashMap<UUID, IClient>();

    public void connect(UUID uuid) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry();
        IClient stub = (IClient) registry.lookup(uuid.toString());
        System.out.println(uuid.toString() + " registered");
        clients.put(uuid, stub);
    }

    public void sendMessage(Message message) throws RemoteException {
        messages.add(message);
        for(IClient client: clients.values()){
            client.notifyClient();
        }
    }

    public List<Message> getMessages() throws RemoteException {
        return messages;
    }

    public void disconnect(UUID uuid) throws RemoteException {
        clients.remove(uuid);
    }

    public static void main(String args[]) {

        try {
            SlackServer obj = new SlackServer();
            IServer stub = (IServer) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("IServer", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
