package com.smileduster.vsboard.service.data;

import com.smileduster.vsboard.api.model.po.battle.BattleLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BattleLogRepository extends MongoRepository<BattleLog, Integer> {

}
