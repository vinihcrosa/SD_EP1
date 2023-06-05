package EP.server;

public class PartData implements java.io.Serializable{
    private Integer id;
    private Integer quantity;
    private String repositoryName;
    public PartData(Integer id, Integer quantity, String repositoryName) {
        this.quantity = quantity;
        this.repositoryName = repositoryName;
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void changeQuantity(Integer quantity){
        this.quantity = quantity;
    }

    public void changeRepositoryName(String repositoryName){
        this.repositoryName = repositoryName;
    }

    public String toString() {
        return "id:" + this.id + " - Repository name: " + this.repositoryName + " - Quantity: " + this.quantity;
    }
}