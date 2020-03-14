package info.tracker.coronavirus.Components;

import info.tracker.coronavirus.Constants;
import info.tracker.coronavirus.exceptions.APIRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class AppInitializerComponent implements Constants {

    @Autowired
    CoronaVirusDataImpl virusData;

    @PostConstruct
    @Scheduled(cron = "0 0 6 * * ?")
    public void init() throws APIRuntimeException, IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                virusData.setData(VIRUS_DATA_URL);
            }
        }).start();
    }
}
