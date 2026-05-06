package TransitEtaClient;

import TransitEtaClient.entities.StopEta;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NextBusManager implements INextBusManager {
    //map all providers with its INextBusProvider-(return a list of etas)
    private final Map<Integer, INextBusProvider> allProviders = new HashMap<>();

    /**
     * @param providers map of all providers where first var is number of provider and second var is the provider itself.
     */
    public NextBusManager(Map<Integer, INextBusProvider> providers) {
        this.allProviders.putAll(providers);
    }

    /**
     * @param providerId the provider of api we want to check
     * @param lineNumber the line we want to check the etas about
     * @return a list of the etas a specific provider returns.
     */
    @Override
    public List<StopEta> getLineEta(int providerId, String lineNumber) {
        INextBusProvider provider = allProviders.get(providerId);
        if(provider == null) return Collections.emptyList();
        try{
            return provider.getLineEta(lineNumber);
        } catch (Exception e) {
            System.err.println("Provider " + providerId + " failed: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
