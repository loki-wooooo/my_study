//package com.zalesia.hellomessagequeue.step4;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/news/api")
//public class NewsRestController {
//    private final NewsPublisher newsPublisher;
//
//    public NewsRestController(NewsPublisher newsPublisher) {
//        this.newsPublisher = newsPublisher;
//    }
//
//    @PostMapping("/publish")
//    public ResponseEntity<String> publishNews(@RequestParam String newsType) {
//        String result = newsPublisher.publishAPI(newsType);
//        return ResponseEntity.ok("# Message published to RabbitMQ: " + result);
//    }
//}
