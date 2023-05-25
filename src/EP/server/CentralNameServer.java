package EP.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CentralNameServer {
    public static void main(String[] args) throws RemoteException {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            System.out.println("Server started successfully!");
        } catch ( Exception e) {
            System.out.println("Server failed to start!");
            e.printStackTrace();
        }


    }
}
