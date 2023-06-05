package EP;

import EP.server.Part;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IPartRepository extends Remote {
    void addPart(Part part) throws RemoteException;
    void removePart(Integer id) throws RemoteException;
    List<Part> getParts() throws RemoteException;
    Part getPartById(Integer id) throws RemoteException;
}
