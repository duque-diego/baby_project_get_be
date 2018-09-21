package app.getfraldas.service;

import app.getfraldas.models.CronHistory;

/**
 * Created by fprado on 18/09/18
 */
public interface ICronService {
    CronHistory saveCron();
    CronHistory getLastCron();
}
