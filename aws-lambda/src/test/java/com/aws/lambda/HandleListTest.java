package com.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandleListTest {

    private static final Logger logger = LoggerFactory.getLogger(HandleListTest.class);

    @Test
    void invokeTest() {
        logger.info("Invoke HandleListTest");
        Context context = new TestContext();
        HandleList handler = new HandleList();
        List<String> result = handler.handleRequest(Arrays.asList("Hello"), context);
        System.out.println(result);
        assertTrue(result.contains("200 OK"));
    }
}
