package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.Captain;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.Healer;
import com.narxoz.rpg.guild.Loremaster;
import com.narxoz.rpg.guild.Quartermaster;
import com.narxoz.rpg.guild.Scout;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===");

        List<Hero> party = List.of(
                new Hero("Arman", 120, 30, 12),
                new Hero("Aizhan", 90, 40, 8)
        );

        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Clear the goblin cave", QuestPriority.NORMAL, 120, false));
        questLog.add(new Quest("Rescue the village healer", QuestPriority.HIGH, 250, true));
        questLog.add(new Quest("Escort the merchant caravan", QuestPriority.LOW, 80, false));
        questLog.add(new Quest("Defeat the shadow knight", QuestPriority.URGENT, 500, true));
        questLog.add(new Quest("Recover the ancient map", QuestPriority.NORMAL, 180, false));

        GuildHall hall = new GuildHall();

        Captain captain = new Captain("Dias", hall);
        Scout scout = new Scout("Miras", hall);
        Healer healer = new Healer("Dana", hall);
        Quartermaster quartermaster = new Quartermaster("Bek", hall);
        Loremaster loremaster = new Loremaster("Aru", hall);

        System.out.println("\n--- Ordered iterator demo ---");
        printQuests(questLog.ordered());

        System.out.println("\n--- Reverse iterator demo ---");
        printQuests(questLog.reverse());

        System.out.println("\n--- Priority iterator demo ---");
        printQuests(questLog.priorityAtLeast(QuestPriority.HIGH));

        System.out.println("\n--- Reward sorted iterator demo ---");
        printQuests(questLog.rewardSorted());

        System.out.println("\n--- Mediator demo ---");
        captain.issueOrder("quest", "Prepare the guild for today's missions.");
        scout.reportRoute("danger", "Enemy tracks were found near the mountain road.");
        healer.prepareAid("healing", "Healing potions are ready.");
        quartermaster.requestSupplies("supplies", "Food, ropes, and weapons are prepared.");
        loremaster.studyQuest("quest", "Old records mention a hidden tunnel.");

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, hall);

        System.out.println("\nFinal result:");
        System.out.println(result);
    }

    private static void printQuests(QuestIterator iterator) {
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}