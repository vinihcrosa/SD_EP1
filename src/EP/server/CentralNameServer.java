package EP.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import EP.IPartRepository;

public class CentralNameServer {
    public static void main(String[] args) throws RemoteException {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            IPartRepository partRepository_1 = new PartRepository();

            registry.rebind("partRepository_1", partRepository_1);
            String[] names = registry.list();

            for (String name : names){
                System.out.println(name);
            }
            System.out.println("Server started successfully!");
        } catch ( Exception e) {
            System.out.println("Server failed to start!");
            e.printStackTrace();
        }


    }
}
