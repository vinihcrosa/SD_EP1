package EP.server;

class PartData extends Part{
    private Integer quantity;
    private String repositoryName;
    public PartData(Integer quantity, Part part, String repositoryName) {
        super(part.getId(), part.getName(), part.getDescription(), part.getSubParts());
        this.quantity = quantity;
        this.repositoryName = repositoryName;
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
}