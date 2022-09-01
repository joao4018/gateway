package base.joao4018.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.sql.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class PingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingService.class);

    @Bean
    public static void threadteste(){
        new Thread(t1).start();
    }




    public static boolean ping() throws InterruptedException {
        LOGGER.info("ping start");
        List<String> hosts = Arrays.asList("api-email-jj.herokuapp.com",
                                             "api-login-all-it.herokuapp.com",
                                             "personal-project-jj.herokuapp.com",
                                             "secret-anchorage-03030.herokuapp.com");

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
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), timeout);
            LOGGER.info("Success ping " + host);
            return true;
        } catch (IOException e) {
            LOGGER.error("Failed ping " + host);
            return false;
        }
    }
}
