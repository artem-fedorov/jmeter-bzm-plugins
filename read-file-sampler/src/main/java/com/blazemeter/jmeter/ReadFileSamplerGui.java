package com.blazemeter.jmeter;

import org.apache.jmeter.samplers.gui.AbstractSamplerGui;
import org.apache.jmeter.testelement.TestElement;

public class ReadFileSamplerGui extends AbstractSamplerGui {

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
    }
}
