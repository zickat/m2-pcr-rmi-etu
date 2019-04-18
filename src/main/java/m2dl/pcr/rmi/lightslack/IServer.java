package m2dl.pcr.rmi.lightslack;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface IServer extends Remote {
    void connect(UUID uuid) throws RemoteException, NotBoundException;
    void sendMessage(Message message) throws RemoteException;
    List<Message> getMessages() throws RemoteException;
    void disconnect(UUID uuid) throws RemoteException;
}
