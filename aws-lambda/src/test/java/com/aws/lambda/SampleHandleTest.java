package com.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleHandleTest {

    private static final Logger logger = LoggerFactory.getLogger(SampleHandleTest.class);

    @Test
    void invokeTest() {
        logger.info("Invoke TEST");
        Context context = new TestContext();
        SampleHandle handler = new SampleHandle();
        String result = handler.handleRequest("Hello", context);
        assertTrue(result.contains("200 OK"));
    }


}
