package com.spring.study.chapter05.application;

import com.spring.study.chapter05.domain.reservation.Reservation;
import com.spring.study.chapter05.domain.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Controller
public class ReservationController {

    private final ReservationService reservationService;
    //private final AsyncListenableTaskExecutor asyncListenableTaskExecutor;
    private final TaskExecutor taskExecutor;

    @GetMapping
    public String home() {
        return "index";
    }

/*    @PostMapping
    public Callable<String> submitForm4(
            @RequestParam("courtName") String courtName,
            Model model
    ) {
        log.info("process: [{}] ", getCurrentThreadName());
        return () -> {
            List<Reservation> reservations = Collections.emptyList();
            log.info("mid: [{}] ", getCurrentThreadName());
            if (courtName != null) {
                Thread.sleep(5000);
                reservations = reservationService.query(courtName);
            }
            model.addAttribute("reservations", reservations);
            log.info("last: [{}] ", getCurrentThreadName());
            return "post";
        };
    }

    @PostMapping
    public DeferredResult<String> submitForm3(
            @RequestParam("courtName") String courtName,
            Model model
    ) {
        final DeferredResult<String> result = new DeferredResult<>();

        taskExecutor.execute(() -> {
            List<Reservation> reservations = Collections.emptyList();
            if (courtName != null) {
                try {
                    Thread.sleep(5000);
                    reservations = reservationService.query(courtName);
                    model.addAttribute("reservations", reservations);
                    result.setResult("reservationQuery");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        return result;
    }

    @PostMapping
    public CompletableFuture<String> submitForm2(
            @RequestParam("courtName") String courtName,
            Model model
    ) {
        log.info("process: [{}] ", getCurrentThreadName());
        return CompletableFuture.supplyAsync(() -> {
            List<Reservation> reservations = Collections.emptyList();
            log.info("mid: [{}] ", getCurrentThreadName());
            if (courtName != null) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {}
                reservations = reservationService.query(courtName);
            }
            model.addAttribute("reservations", reservations);
            log.info("last: [{}] ", getCurrentThreadName());
            return "post";
        }, taskExecutor);
    }

    @PostMapping
    public ListenableFuture<String> submitForm(
            @RequestParam("courtName") String courtName,
            Model model
    ) {
        return asyncListenableTaskExecutor.submitListenable(() -> {
            List<Reservation> reservations = Collections.emptyList();
            if (courtName != null) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {}
                reservations = reservationService.query(courtName);
            }
            model.addAttribute("reservations", reservations);
            return "welcome";
        });
    }*/

    @GetMapping("/find")
    public ResponseEntity<ResponseBodyEmitter> find(
            @RequestParam("courtName") String courtName
    ) {
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        log.info("first: [{}] ", getCurrentThreadName());
        taskExecutor.execute(() -> {
            log.info("second: [{}] ", getCurrentThreadName());
            Collection<Reservation> reservations = reservationService.query(courtName);
            try {
                for (Reservation reservation : reservations) {
                    log.info("reservation: {}", reservation);
                    emitter.send(reservation);
                }
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        log.info("third: [{}] ", getCurrentThreadName());
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .header("Accept", "TEST Value")
                .body(emitter);
    }

    @GetMapping("/sse/find")
    public SseEmitter sseFind(
            @RequestParam("courtName") String courtName
    ) {
        final SseEmitter emitter = new SseEmitter();
        taskExecutor.execute(() -> {
            Collection<Reservation> reservations = reservationService.query(courtName);
            try {
                for (Reservation reservation : reservations) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {}
                    emitter.send(
                            SseEmitter.event()
                                    .id(String.valueOf(reservation.hashCode()))
                                    .data(reservation)
                    );
                }
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });
        log.info("third: [{}] ", getCurrentThreadName());
        return emitter;
    }

    private String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }
}