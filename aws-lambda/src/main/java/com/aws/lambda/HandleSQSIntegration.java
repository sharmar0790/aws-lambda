package com.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.stream.Collectors;

public class HandleSQSIntegration implements RequestHandler<SQSEvent, List<String>> {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public List<String> handleRequest(SQSEvent event, Context context) {
        LambdaLogger log = context.getLogger();
        log.log("EVENT: " + gson.toJson(event));
        log.log("EVENT TYPE: " + event.getClass());
        log.log("SQS Records: " + event.getRecords());

        logDetails(event, log);

        return event.getRecords()
                .stream()
                .map(SQSEvent.SQSMessage::getBody)
                .collect(Collectors.toList());
    }

    private void logDetails(SQSEvent event, LambdaLogger log) {
        event.getRecords().forEach(r -> {
            log.log(r.getBody());
            log.log(r.getAwsRegion());
            log.log(r.getEventSource());
            log.log(r.getEventSourceArn());
            log.log(r.getMessageId());

        });
    }
}
