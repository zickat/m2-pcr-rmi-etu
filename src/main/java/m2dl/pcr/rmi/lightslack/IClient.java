package m2dl.pcr.rmi.lightslack;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
    void notifyClient() throws RemoteException;
}
