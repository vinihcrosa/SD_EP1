package EP.server;

import java.util.ArrayList;
import java.util.List;

public class PartInventory implements java.io.Serializable{
    private ArrayList<PartData> subParts;

    public PartInventory() {
        this.subParts = new ArrayList<PartData>();
    }

    public void addSubPart(Integer id, Integer quantity, String serverName){
        this.subParts.add(new PartData(id, quantity, serverName));
    }

    public Integer getSubPartsCount() {
        return this.subParts.size();
    }

    public ArrayList<PartData> getSubParts() {
        return this.subParts;
    }
}

