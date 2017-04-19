package m2dl.pcr.rmi.helloworld;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by franck on 19/04/2017.
 */
public interface Hello extends Remote {
    String sayHello() throws RemoteException;
}
