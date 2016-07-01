package org.kurator.validation.actors;

import org.kurator.akka.KuratorActor;

import java.util.Map;

/**
 * Created by lowery on 7/1/16.
 */
public class JavaTemplateActor extends KuratorActor {
    public String input;

    @Override
    protected void onInitialize() throws Exception {
        // TODO: Implement or remove this method

        System.out.println("Value of field \"input\" is: " + input);
    }

    @Override
    protected void onStart() throws Exception {
        // TODO: Implement or remove this method

        System.out.println(getClass().getSimpleName() + " started.");
    }

    @Override
    protected void onData(Object value) throws Exception {
        // TODO: Implement this method with your own actor logic

        if (value instanceof Map<?,?>) {
            Map<String,String> record = (Map<String,String>) value; // Input value from CsvReader is a Map
            System.out.println(record);
            broadcast(record); // Output value for CsvFileWriter is also a Map
        }
    }

    @Override
    protected void onEnd() throws Exception {
        // TODO: Implement or remove this method

        System.out.println(getClass().getSimpleName() + " ended.");
    }
}
