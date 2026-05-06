package TransitEtaClient;

import TransitEtaClient.entities.StopEta;

import java.util.List;

public interface INextBusProvider {
    List<StopEta> getLineEta(String lineNumber)throws Exception;
}
