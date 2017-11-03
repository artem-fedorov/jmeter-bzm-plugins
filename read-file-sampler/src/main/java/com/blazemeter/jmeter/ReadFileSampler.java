package com.blazemeter.jmeter;

import org.apache.jmeter.samplers.AbstractSampler;
import org.apache.jmeter.samplers.Entry;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

public class ReadFileSampler extends AbstractSampler {
    private static final Logger LOGGER = LoggingManager.getLoggerForClass();

    public static final String FILENAME = "FILENAME";


    public SampleResult sample(Entry entry) {
        return null;
    }


    public String getFilename() {
        return getPropertyAsString(FILENAME);
    }

    public void setFilename(String filename) {
        setProperty(FILENAME, filename);
    }
}
