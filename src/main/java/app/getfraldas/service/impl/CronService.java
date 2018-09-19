package app.getfraldas.service.impl;

import app.getfraldas.models.CronHistory;
import app.getfraldas.repository.CronHistoryRepository;
import app.getfraldas.service.ICronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by fprado on 18/09/18
 */
@Service
public class CronService implements ICronService {

    private static final String TIMEZONE_UTC = "UTC";

    @Autowired
    private CronHistoryRepository cronHistoryRepository;

    @Override
    public CronHistory saveCron() {
        CronHistory cronHistory = new CronHistory();
        cronHistory.setDoneDate(new Date());
        return cronHistoryRepository.save(cronHistory);
    }

    @Override
    public CronHistory getLastCron() {
        return cronHistoryRepository.findTopByOrderByIdDesc();
    }
}
