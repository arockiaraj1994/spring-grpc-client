package com.learnings.spring.grpc.client;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class CollateralAPIService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollateralAPIService.class);

    private PreviewServiceGrpc.PreviewServiceBlockingStub previewServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build();

        previewServiceBlockingStub = PreviewServiceGrpc.newBlockingStub(managedChannel);
    }

    public String sayHello(String firstName, String lastName) {
        PreviewSummary previewSummaryRequest = PreviewSummary.newBuilder().setCounterParty("CME").build();

        LOGGER.info("client sending {}", previewSummaryRequest);
        PreviewSummary previewSummary = previewServiceBlockingStub.preview(previewSummaryRequest);
        LOGGER.info("client received {}", previewSummary);

        return previewSummary.getCounterParty();
    }
}