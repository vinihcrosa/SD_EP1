package EP.server;

import java.util.ArrayList;
import java.util.List;

class PartInventory {
    private List<PartData> subParts;

    public PartInventory() {
        this.subParts = new ArrayList<PartData>();
    }

    public void addSubPart(Part subpart, Integer quantity, String serverName){
        this.subParts.add(new PartData(quantity, subpart, serverName));
    }

    public Integer getSubPartsCount() {
        return this.subParts.size();
    }
}

