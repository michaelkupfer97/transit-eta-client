package TransitEtaClient.providers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * HttpReq is a class that help the Providers use GET or POST
 * requests and not to repeat code in provider itself.
 */
public class HttpReq {
    private final HttpClient client = HttpClient.newHttpClient();

     public String get(String url) throws IOException, InterruptedException {
        HttpRequest req = HttpRequest.newBuilder(URI.create(url)).GET().build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() / 100 != 2) throw new IOException("HTTP " + res.statusCode());
        return res.body();
    }

    public String postReq(String url, String body) throws IOException,InterruptedException{
        HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() / 100 != 2) throw new IOException("HTTP " + res.statusCode());
        return res.body();
    }
}
