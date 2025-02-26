package com.zalesia.hellomessagequeue.step3;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationPublisher notificationPublisher;

    public NotificationController(NotificationPublisher notificationPublisher) {
        this.notificationPublisher = notificationPublisher;
    }

    @PostMapping
    public String sendNotification(@RequestBody final String message) {
        notificationPublisher.publish(message);
        return "[#] Notification send : " + message + "\n";
    }
}