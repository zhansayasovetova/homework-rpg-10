package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for gear, supplies, and rewards.
 */
public class Quartermaster extends GuildMember {

    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {

        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {

        System.out.println(
                "[Quartermaster " + getName() + "] received topic '" +
                        topic + "' from " + from.getName() +
                        ": " + payload
        );

        if ("quest".equalsIgnoreCase(topic)) {

            System.out.println(
                    "[Quartermaster " + getName() + "] prepares weapons, food, and rewards."
            );
        }

        if ("supplies".equalsIgnoreCase(topic)) {

            System.out.println(
                    "[Quartermaster " + getName() + "] checks inventory and resource stock."
            );
        }
    }
}