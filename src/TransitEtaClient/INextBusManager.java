package TransitEtaClient;

import TransitEtaClient.entities.StopEta;

import java.util.List;

public interface INextBusManager {
    List<StopEta> getLineEta(int providerId, String lineNumber);

}
