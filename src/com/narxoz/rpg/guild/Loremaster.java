package com.narxoz.rpg.guild;

/**
 * Extra guild member for open/closed proof.
 */
public class Loremaster extends GuildMember {

    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void studyQuest(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println(
                "[Loremaster " + getName() + "] received topic '" +
                        topic + "' from " + from.getName() +
                        ": " + payload
        );

        if ("quest".equalsIgnoreCase(topic)) {
            System.out.println(
                    "[Loremaster " + getName() + "] studies old records and finds useful lore."
            );
        }
    }
}