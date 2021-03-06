package com.smileduster.vsboard.web.controller;

import com.fasterxml.uuid.impl.UUIDUtil;
import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.dto.BattleDTO;
import com.smileduster.vsboard.api.model.dto.BattleEventDTO;
import com.smileduster.vsboard.api.model.dto.BattleMemberDTO;
import com.smileduster.vsboard.api.model.factory.TestFactory;
import com.smileduster.vsboard.api.service.IBattleService;
import com.smileduster.vsboard.web.form.CreateBattleEventForm;
import com.smileduster.vsboard.web.form.CreateBattleForm;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/battle")
@CrossOrigin("*")
public class BattleController {

    private final IBattleService battleService;

    public BattleController(IBattleService battleService) {
        this.battleService = battleService;
    }

    @PostMapping("/create")
    public Response<?> createBattle(@RequestBody CreateBattleForm form) {
        BattleDTO dto = new BattleDTO();
        dto.setBattleName(form.getBattleName());
        dto.setBattleNote(form.getBattleNote());
        dto.setBattleInfo(form.getBattleInfo());
        dto.setGroupUUID(form.getGroupUUID());
        dto.setBattleBeginTime(form.getBattleBeginTime());
        dto.setBattleEndTime(form.getBattleEndTime());
        return battleService.createBattle(dto, userId());
    }

    @PostMapping("/finish/{battleUUID}")
    public Response<?> finishBattle(@PathVariable String battleUUID) {
        return battleService.finishBattle(battleUUID, userId());
    }

    @PostMapping("/delete/{battleUUID}")
    public Response<?> deleteBattle(@PathVariable String battleUUID) {
        return battleService.deleteBattle(battleUUID, userId());
    }

    @GetMapping("/{battleUUID}")
    public Response<BattleDTO> getBattleDetail(@PathVariable String battleUUID) {
        return battleService.getBattle(battleUUID, userId());
    }

    @GetMapping("/list")
    public Response<List<BattleDTO>> getOwnedBattleList(int page, int size) {
        return battleService.getBattles(page, size, userId());
    }

    @GetMapping("/member/{battleUUID}/{groupMemberNumber}")
    public Response<BattleMemberDTO> getBattleMemberDetail(
            @PathVariable String battleUUID,
            @PathVariable String groupMemberNumber
    ) {
        int number = Integer.parseInt(groupMemberNumber);
        return battleService.getBattleMember(battleUUID, number, userId());
    }

    @PostMapping("/event/create")
    public Response<?> createBattleEvent(@RequestBody CreateBattleEventForm form) {
        BattleEventDTO dto = new BattleEventDTO();
        dto.setBattleEventTitle(form.getBattleEventTitle());
        dto.setBattleEventNote(form.getBattleEventNote());
        dto.setBattleEventResult(form.getBattleEventResult());
        dto.setBattleEventScore(form.getBattleEventScore());
        dto.setBattleEventStartTime(form.getBattleEventStartTime());
        dto.setBattleEventEndTime(form.getBattleEventEndTime());
        dto.setBattleUUID(form.getBattleUUID());
        return battleService.createBattleEvent(dto, userId());
    }

    @GetMapping("/event/{battleUUID}/{battleEventNumber}")
    public Response<BattleEventDTO> getBattleEventDetail(
            @PathVariable String battleUUID,
            @PathVariable String battleEventNumber
    ) {
        int number = Integer.parseInt(battleEventNumber);
        return battleService.getEvent(battleUUID, number, userId());
    }

    private int userId() {
        return (int) SecurityUtils.getSubject().getPrincipal();
    }

}
