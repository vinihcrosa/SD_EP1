package EP;

import EP.server.Part;

import java.rmi.Remote;
import java.util.List;

public interface IPartRepository extends Remote {
    void addPart(Part part);
    void removePart(Integer id);
    List<Part> getParts();
}
