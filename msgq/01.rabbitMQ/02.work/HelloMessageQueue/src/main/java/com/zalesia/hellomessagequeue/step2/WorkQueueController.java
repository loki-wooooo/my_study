package com.zalesia.hellomessagequeue.step2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WorkQueueController {

    private final WorkQueueProducer workQueueProducer;

    public WorkQueueController(WorkQueueProducer workQueueProducer) {
        this.workQueueProducer = workQueueProducer;
    }

    /**
     *
     * curl -X "POST" "http://localhost:8080/api/work-queue?message=Task1&duration=3000"
     * curl -X "POST" "http://localhost:8080/api/work-queue?message=Task2&duration=4000"
     * curl -X "POST" "http://localhost:8080/api/work-queue?message=Task3&duration=5000"
     * Invoke-WebRequest -Method POST -Uri "http://localhost:8080/api/work-queue?message=Task1&duration=3000"
     * Invoke-WebRequest -Method POST -Uri "http://localhost:8080/api/work-queue?message=Task2&duration=4000"
     * Invoke-WebRequest -Method POST -Uri "http://localhost:8080/api/work-queue?message=Task3&duration=5000"
     * */
    @PostMapping("/work-queue")
    public String workQueue(@RequestParam final String message, @RequestParam final Integer duration) throws Exception {
        workQueueProducer.sendWorkQueue(message, duration);
        return "Work queue send = " + message + ", (" + duration + ")";
    }
}
