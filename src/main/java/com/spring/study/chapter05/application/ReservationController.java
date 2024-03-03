package com.spring.study.chapter05.application;

import com.spring.study.chapter05.domain.reservation.Reservation;
import com.spring.study.chapter05.domain.reservation.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
@Controller
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        System.out.println("controller!");
        this.reservationService = reservationService;
    }

    @GetMapping
    public String home() {
        return "welcome";
    }

    @PostMapping
    public Callable<String> submitForm(
            @RequestParam("courtName") String courtName,
            Model model
    ) {
        log.info("process: [{}] ", getCurrentThreadName());
        return () -> {
            List<Reservation> reservations = Collections.emptyList();
            if (courtName != null) {
                Thread.sleep(5000);
                reservations = reservationService.query(courtName);
            }
            model.addAttribute("reservations", reservations);
            log.info("return: [{}] ", getCurrentThreadName());
            return "post";
        };
    }

    private String getCurrentThreadName() {
        return Thread.currentThread().getName();
    }
}
