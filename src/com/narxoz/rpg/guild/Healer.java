package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for wounds, potions, and recovery plans.
 */
public class Healer extends GuildMember {

    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {

        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {

        System.out.println(
                "[Healer " + getName() + "] received topic '" +
                        topic + "' from " + from.getName() +
                        ": " + payload
        );

        if ("danger".equalsIgnoreCase(topic)) {

            System.out.println(
                    "[Healer " + getName() + "] prepares healing potions and recovery beds."
            );
        }

        if ("healing".equalsIgnoreCase(topic)) {

            System.out.println(
                    "[Healer " + getName() + "] organizes medical support for the heroes."
            );
        }
    }
}