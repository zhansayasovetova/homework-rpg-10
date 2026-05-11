package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for route reports and reconnaissance.
 */
public class Scout extends GuildMember {

    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {

        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {

        System.out.println(
                "[Scout " + getName() + "] received topic '" +
                        topic + "' from " + from.getName() +
                        ": " + payload
        );

        if ("quest".equalsIgnoreCase(topic)) {

            System.out.println(
                    "[Scout " + getName() + "] scouts the surrounding area and marks safe paths."
            );
        }

        if ("danger".equalsIgnoreCase(topic)) {

            System.out.println(
                    "[Scout " + getName() + "] reports enemy movement near the mission route."
            );
        }
    }
}
