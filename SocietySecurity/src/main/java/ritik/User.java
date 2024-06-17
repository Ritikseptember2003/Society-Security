package ritik;

public class User {
    private String username;
    private int buildingId;
    private int flatId;

    public User(String username, int buildingId, int flatId) {
        this.username = username;
        this.buildingId = buildingId;
        this.flatId = flatId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getFlatId() {
        return flatId;
    }

    public void setFlatId(int flatId) {
        this.flatId = flatId;
    }
}