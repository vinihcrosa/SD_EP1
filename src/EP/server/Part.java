package EP.server;

import java.io.Serializable;

public class Part implements Serializable{
    private Integer id;
    private String name;
    private String description;
    private final PartInventory subParts;

    public Part() {
        this.subParts = new PartInventory();
    }

    public Part(Integer id, String name, String description, PartInventory subParts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subParts = subParts;
    }

    public void addSubPart(Part subpart, Integer quantity, String repositoryName){
        System.out.println("Subpart added!");
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public PartInventory getSubParts() {
        return subParts;
    }

    public String toString() {
        return "Part: id: " + this.id + " " + this.name + " - " + this.description;
    }
}
