package m2dl.pcr.rmi.lightslack;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SlackServer implements IServer{

    private List<Message> messages = new ArrayList<Message>();


    public void sendMessage(Message message) throws RemoteException {
        messages.add(message);
    }

    public List<Message> getMessages() throws RemoteException {
        return messages;
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
