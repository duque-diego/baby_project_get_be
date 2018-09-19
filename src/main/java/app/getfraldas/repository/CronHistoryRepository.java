package app.getfraldas.repository;

import app.getfraldas.models.CronHistory;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by fprado on 18/09/18
 */
public interface CronHistoryRepository extends CrudRepository<CronHistory, Long> {
    CronHistory findTopByOrderByIdDesc();
}
