package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.Captain;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;

import java.util.List;

/**
 * Orchestrates a planning session that uses both Iterator and Mediator.
 */
public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {

        int questsTraversed = 0;
        int messagesRouted = 0;
        int membersNotified = 0;

        Captain councilLeader = new Captain("Council Leader", hall);

        System.out.println("\n=== WAR COUNCIL STARTED ===");

        System.out.println("\nParty members:");
        for (Hero hero : party) {
            System.out.println(hero);
        }

        System.out.println("\n--- Ordered quest traversal ---");
        QuestIterator orderedIterator = questLog.ordered();

        while (orderedIterator.hasNext()) {
            Quest quest = orderedIterator.next();
            questsTraversed++;

            System.out.println("Planning quest: " + quest);

            hall.dispatch("quest", councilLeader, "Plan mission: " + quest);
            messagesRouted++;
            membersNotified += 2;
        }

        System.out.println("\n--- High priority quest traversal ---");
        QuestIterator priorityIterator = questLog.priorityAtLeast(QuestPriority.HIGH);

        while (priorityIterator.hasNext()) {
            Quest quest = priorityIterator.next();
            questsTraversed++;

            System.out.println("Urgent review: " + quest);

            hall.dispatch("danger", councilLeader, "High priority danger detected: " + quest);
            messagesRouted++;
            membersNotified += 2;

            hall.dispatch("healing", councilLeader, "Prepare healing support for: " + quest);
            messagesRouted++;
            membersNotified += 2;

            hall.dispatch("supplies", councilLeader, "Prepare supplies for: " + quest);
            messagesRouted++;
            membersNotified += 2;
        }

        System.out.println("\n=== WAR COUNCIL FINISHED ===");

        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}