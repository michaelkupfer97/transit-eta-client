package TransitEtaClient.providers;

import TransitEtaClient.INextBusProvider;
import TransitEtaClient.entities.StopEta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Provider2 implements INextBusProvider {
    private static final String URL = "https://provider2.com/etas";
    private static final Pattern ARRIVAL =
            Pattern.compile("\"stop_id\"\\s*:\\s*(\\d+).*?\"time\"\\s*:\\s*(\\d+)", Pattern.DOTALL);
    private final HttpReq http;

    public Provider2(HttpReq http) { this.http = http; }

    @Override
    public List<StopEta> getLineEta(String lineNumber) throws Exception {
        String body = "{\"lineRequest\":{\"lineId\":\"" + lineNumber + "\"}}";
        String json = http.postReq(URL, body);

        List<StopEta> out = new ArrayList<>();
        Matcher m = ARRIVAL.matcher(json);
        while (m.find()) {
            int stopId = Integer.parseInt(m.group(1));
            long timeMillis = Long.parseLong(m.group(2));
            out.add(new StopEta(stopId, new Date(timeMillis)));
        }
        return out;
    }
}
