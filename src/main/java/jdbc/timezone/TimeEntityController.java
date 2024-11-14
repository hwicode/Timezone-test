package jdbc.timezone;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.TimeZone;

@RequiredArgsConstructor
@RestController
public class TimeEntityController {

    private final TimeEntityRepository timeEntityRepository;

    @GetMapping("/save")
    public TimeEntity saveTask(@RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        TimeEntity timeEntity = new TimeEntity(LocalDateTime.now(), dateTime);
        return timeEntityRepository.save(timeEntity);
    }

    @GetMapping("/timezone")
    public String getTimezone() {
        return TimeZone.getDefault().getID();
    }

}
