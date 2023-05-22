package Logic;

import java.util.EventObject;

public class CreateVBDEvent extends EventObject {
    public String SMS;
    public CreateVBDEvent(Object source,String SMS) {
        super(source);
        this.SMS = SMS;
    }
}
