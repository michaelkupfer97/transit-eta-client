import TransitEtaClient.NextBusManager;
import TransitEtaClient.providers.HttpReq;
import TransitEtaClient.providers.Provider1;
import TransitEtaClient.providers.Provider2;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpReq http = new HttpReq();
        NextBusManager manger = new NextBusManager(Map.of(1,new Provider1(http),2, new Provider2(http)));
        System.out.println(manger.getLineEta(1,"11"));

    }
}