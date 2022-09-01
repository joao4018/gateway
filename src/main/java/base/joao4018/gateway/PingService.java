package base.joao4018.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingService.class);

    @Bean
    public static void threadteste(){
        new Thread(t1).start();
    }




    public static boolean ping() throws InterruptedException {
        LOGGER.info("ping start");
        List<String> hosts = Arrays.asList("http://api-email-jj.herokuapp.com",
                                             "http://api-login-all-it.herokuapp.com/",
                                             "http://personal-project-jj.herokuapp.com",
                                             "http://secret-anchorage-03030.herokuapp.com");

        while (true){
            extracted(hosts);
            Thread.sleep(1750000);
        }


    }

    private static Runnable t1 = new Runnable() {
        public void run() {
            try{
                for(int i=0; i<5; i++){
                    ping();
                }
            } catch (Exception e){}

        }
    };

    private static void extracted(List<String> hosts) {
        hosts.forEach(host -> {
            pingHost(host, 80, 350);
        });
    }

    public static boolean pingHost(String host, int port, int timeout) {

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlTemplate = UriComponentsBuilder.fromHttpUrl(host)
                .encode()
                .toUriString();

        Map<String, ?> params = new HashMap<>();


        HttpEntity<String> response = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                entity,
                String.class,
                params
        );
        return true;
    }
}
