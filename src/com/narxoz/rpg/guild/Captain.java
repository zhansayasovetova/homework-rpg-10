package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for orders and mission coordination.
 */
public class Captain extends GuildMember {

    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void issueOrder(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {

        System.out.println(
                "[Captain " + getName() + "] received topic '" +
                        topic + "' from " + from.getName() +
                        ": " + payload
        );

        if ("danger".equalsIgnoreCase(topic)) {
            System.out.println(
                    "[Captain " + getName() + "] orders the guild to prepare for battle!"
            );
        }

        if ("healing".equalsIgnoreCase(topic)) {
            System.out.println(
                    "[Captain " + getName() + "] approves healing support."
            );
        }
    }
}