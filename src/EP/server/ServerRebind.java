package EP.server;

import EP.IPartRepository;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerRebind {
    public static void main(String[] args) throws RemoteException {
        if(args.length != 3) {
            System.out.println("Usage: java ServerRebind <serverName> <serverHost> <serverPort>");
            return;
        }
        try {
            Registry registry = LocateRegistry.getRegistry(args[1], Integer.parseInt(args[2]));

            IPartRepository partRepository = new PartRepository();

            registry.rebind(args[0], partRepository);
            String[] names = registry.list();

            for (String name : names){
                System.out.println(name);
            }

        } catch(Exception e) {
            System.out.println("Server failed to start!");
            e.printStackTrace();
        }
    }
}
