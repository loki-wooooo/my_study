//package com.zalesia.hellomessagequeue.step5;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/logs")
//public class LogController {
//
//    private final CustomExceptionHandler customExceptionHandler;
//
//    public LogController(CustomExceptionHandler customExceptionHandler) {
//        this.customExceptionHandler = customExceptionHandler;
//    }
//
//    @GetMapping("/error")
//    public ResponseEntity<String> errorAPI() {
//        try {
//            String value = null;
//            value.getBytes(); //nullpointer
//        } catch (Exception ex) {
//            customExceptionHandler.handleException(ex);
//        }
//
//        return ResponseEntity.ok("Controller NullPointException 처리");
//    }
//
//    @GetMapping("/warn")
//    public ResponseEntity<String> warnAPI() {
//        try {
//            throw new IllegalArgumentException("invalid argument 입니다.");
//        } catch (Exception ex) {
//            customExceptionHandler.handleException(ex);
//        }
//
//        return ResponseEntity.ok("Controller IllegalArgumentException 처리");
//    }
//
//    @PostMapping("/info")
//    public ResponseEntity<String> infoAPI(@RequestBody final String message) {
//        customExceptionHandler.handleMessage(message);
//        return ResponseEntity.ok("Controller Info Log 발송 처리");
//    }
//}
