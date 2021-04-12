package com.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class HandleList implements RequestHandler<List<String>, List<String>> {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<String> handleRequest(List<String> event, Context context) {

        LambdaLogger logger = context.getLogger();
        List<String> response = new ArrayList<>();
        // log execution details
        logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
        logger.log("CONTEXT: " + gson.toJson(context));
        // process event
        logger.log("EVENT: " + gson.toJson(event));
        logger.log("EVENT TYPE: " + event.getClass());
        response.add("200 OK");
        response.add("CONTEXT: " + gson.toJson(context));
        response.add("EVENT: " + gson.toJson(event));
        response.add("EVENT TYPE: " + event.getClass());
        return response;
    }
}
