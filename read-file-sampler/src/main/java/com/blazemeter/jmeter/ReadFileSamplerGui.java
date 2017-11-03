package com.blazemeter.jmeter;

import kg.apc.jmeter.JMeterPluginsUtils;
import kg.apc.jmeter.gui.BrowseAction;
import kg.apc.jmeter.gui.GuiBuilderHelper;
import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

import javax.swing.*;
import java.awt.*;

public class ReadFileSamplerGui extends AbstractSamplerGui {

    // TODO: use full URL and change cmn version to 0.6 after it has been released
    public static final String WIKIPAGE = "ReadFileSampler";

    private JTextField filenameField;
    private JButton browseButton;


    public ReadFileSamplerGui() {
        initGui();
        initGuiValues();
    }

    private void initGui() {
        setLayout(new BorderLayout(0, 5));
        setBorder(makeBorder());

        Container topPanel = makeTitlePanel();

        add(JMeterPluginsUtils.addHelpLinkToPanel(topPanel, WIKIPAGE), BorderLayout.NORTH);
        add(topPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.anchor = GridBagConstraints.FIRST_LINE_END;

        GridBagConstraints editConstraints = new GridBagConstraints();
        editConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        editConstraints.weightx = 1.0;
        editConstraints.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        addToPanel(mainPanel, labelConstraints, 0, row, new JLabel("Filename: ", JLabel.RIGHT));
        addToPanel(mainPanel, editConstraints, 1, row, filenameField = new JTextField(20));
        addToPanel(mainPanel, labelConstraints, 2, row, browseButton = new JButton("Browse..."));
        GuiBuilderHelper.strechItemToComponent(filenameField, browseButton);

        editConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        labelConstraints.insets = new java.awt.Insets(2, 0, 0, 0);

        browseButton.addActionListener(new BrowseAction(filenameField, true));

        JPanel container = new JPanel(new BorderLayout());
        container.add(mainPanel, BorderLayout.NORTH);
        add(container, BorderLayout.CENTER);
    }

    private void initGuiValues() {
        filenameField.setText("");
    }

    private void addToPanel(JPanel panel, GridBagConstraints constraints, int col, int row, JComponent component) {
        constraints.gridx = col;
        constraints.gridy = row;
        panel.add(component, constraints);
    }

    @Override
    public String getStaticLabel() {
        return "bzm - Read File Sampler";
    }

    public String getLabelResource() {
        return "read_file_sampler";
    }

    public TestElement createTestElement() {
        return null;
    }

    public void modifyTestElement(TestElement testElement) {

    }

    @Override
    public void configure(TestElement element) {
        super.configure(element);
    }

    @Override
    public void clearGui() {
        super.clearGui();
        initGuiValues();
    }
}
