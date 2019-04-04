package m2dl.pcr.rmi.lightslack;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IServer extends Remote {
    void sendMessage(Message message) throws RemoteException;
    List<Message> getMessages() throws RemoteException;
}
