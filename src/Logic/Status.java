package Logic;

public enum Status {
    WAITING(false),ACTIVE(true);
    public final boolean status;
    Status(boolean status){
        this.status = status;
    }

}
