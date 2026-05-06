package TransitEtaClient.providers;

import TransitEtaClient.INextBusProvider;
import TransitEtaClient.entities.StopEta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provider1 is a class that implements  INextBusProvider interface
 * BASE_URL is the url the api use
 *
 */
public class Provider1 implements INextBusProvider {
    private static final String BASE_URL = "http://d2fo80vv1pnzuu.cloudfront.net/test/";
    private static final Pattern ARRIVAL =
            Pattern.compile("\"stopId\"\\s*:\\s*(\\d+)\\s*,\\s*\"eta\"\\s*:\\s*(\\d+)");
    private final HttpReq http;

    public Provider1(HttpReq http) { this.http = http; }

    @Override
    public List<StopEta> getLineEta(String lineNumber) throws Exception {
        String json = http.get(BASE_URL + lineNumber);

        List<StopEta> out = new ArrayList<>();
        Matcher m = ARRIVAL.matcher(json);
        while (m.find()) {
            int stopId = Integer.parseInt(m.group(1));
            long etaMillis = Long.parseLong(m.group(2));
            out.add(new StopEta(stopId, new Date(etaMillis)));
        }
        return out;
    }
}

