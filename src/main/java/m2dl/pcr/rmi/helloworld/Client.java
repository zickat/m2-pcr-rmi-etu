package m2dl.pcr.rmi.helloworld;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by franck on 19/04/2017.
 */
public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}