package com.jelly.GhosterBuster.utils.HypixelUtils;

import com.jelly.GhosterBuster.GhosterBuster;
import com.jelly.GhosterBuster.macros.macros.CommissionMacro;
import com.jelly.GhosterBuster.utils.TablistUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ComissionUtils {
    public static Pair<CommissionMacro.ComissionType, Boolean> determineComm() {
        CommissionMacro.ComissionType quest = null;
        boolean isDone = false;
        List<String> tablist = TablistUtils.getTabListPlayersUnprocessed();
        for (String s : tablist) {
            for (CommissionMacro.ComissionType value : CommissionMacro.ComissionType.values()) {
                if (s.contains(value.questName) && !s.contains("Golden") && !s.contains("Raid")) {
                    if (GhosterBuster.config.commSkipGoblinSlayerQuest) {
                        if (!s.contains("Goblin Slayer")) {
                            quest = value;
                            isDone = s.contains("DONE");
                        }
                    } else {
                        quest = value;
                        isDone = s.contains("DONE");
                    }
                }
            }
        }
        return Pair.of(quest, isDone);
    }

    public static boolean anyCommissionDone() {
        List<String> tablist = TablistUtils.getTabListPlayersUnprocessed();
        for (String s : tablist) {
            for (CommissionMacro.ComissionType value : CommissionMacro.ComissionType.values()) {
                if (s.contains(value.questName) && s.contains("DONE")) {
                    return true;
                }
            }
        }
        return false;
    }
}
