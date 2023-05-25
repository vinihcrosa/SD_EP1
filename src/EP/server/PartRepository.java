package EP.server;

import EP.IPartRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class PartRepository extends UnicastRemoteObject implements IPartRepository {
    private List<Part> parts;
    public PartRepository() throws RemoteException {
        super();
        this.parts = new ArrayList<Part>();
    }

    public void addPart(Part part) {
        this.parts.add((Part) part);
    }

    public void removePart(Integer id) {
        for (Part part : this.parts) {
            if (part.getId().equals(id)) {
                this.parts.remove(part);
                break;
            }
        }
    }

    public List<Part> getParts() {
        return this.parts;
    }
}
