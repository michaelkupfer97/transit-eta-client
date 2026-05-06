package TransitEtaClient.entities;

import java.util.Date;

public class StopEta {
    private final int stopId;
    private final Date eta;

    public StopEta(int stopId, Date eta){
        this.stopId = stopId;
        this.eta = eta;
    }

    public int getStopId() {
        return stopId;
    }

    public Date getEta() {
        return eta;
    }
    @Override
    public String toString(){
        return "stopId: " + stopId + ",\n" + "eta: "+eta;
    }
}
