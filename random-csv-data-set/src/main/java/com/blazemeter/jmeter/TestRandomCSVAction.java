package com.blazemeter.jmeter;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.threads.JMeterContextService;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.util.JMeterStopThreadException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRandomCSVAction implements ActionListener {

    private final RandomCSVDataSetConfigGui randomCSVConfigGui;

    public TestRandomCSVAction(RandomCSVDataSetConfigGui randomCSVConfigGui) {
        this.randomCSVConfigGui = randomCSVConfigGui;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        final RandomCSVDataSetConfig config = (RandomCSVDataSetConfig) randomCSVConfigGui.createTestElement();

        config.setRewindOnTheEndOfList(false);
        config.setIndependentListPerThread(false);

        JTextArea checkArea = randomCSVConfigGui.getCheckArea();

        try {
            final CompoundVariable compoundVariable = new CompoundVariable();

            compoundVariable.setParameters(config.getFilename());
            config.setFilename(compoundVariable.execute());

            compoundVariable.setParameters(config.getVariableNames());
            config.setVariableNames(compoundVariable.execute());

            String[] destinationVariableKeys = config.getDestinationVariableKeys();

            JMeterVariables jMeterVariables = new JMeterVariables();
            JMeterContextService.getContext().setVariables(jMeterVariables);

            final List<Map<String, String>> result = new ArrayList<>();

            config.testStarted();

            try {
                while (true) {
                    config.iterationStart(null);
                    Map<String, String> record = new HashMap<>();
                    for (String var : destinationVariableKeys) {
                        record.put(var, jMeterVariables.get(var));
                    }
                    result.add(record);
                }
            } catch (JMeterStopThreadException ex) {
                // OK
            }

            config.testEnded();

            final StringBuilder builder = new StringBuilder();

            builder.append("Reading CSV successfully finished, ").append(result.size()).append(" records found:\r\n");
            for (Map<String, String> record : result) {
                for (String key : record.keySet()) {
                    builder.append("${").append(key).append("} = ");
                    builder.append(record.get(key));
                    builder.append("\r\n");
                }
                builder.append("------------");
            }

            checkArea.setText(builder.toString());
            // move scroll to top
            checkArea.setCaretPosition(0);
        } catch (RuntimeException | InvalidVariableException ex) {
            checkArea.setText(ex.getMessage());
        }
    }

}
