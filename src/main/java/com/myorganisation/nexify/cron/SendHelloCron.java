package com.myorganisation.nexify.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SendHelloCron {

//    @Scheduled(cron = "* * * * * ?")
//    public void sayHelloEverySecond() {
//        System.out.println("Hello");
//    }
//
//    @Scheduled(cron = "0 * * * * ?")
//    public void sayHiEveryMinute() {
//        System.out.println("Hi");
//    }
//
//    @Scheduled(cron = "*/30 * * * * ?")
//    public void sayNamasteTwoTimesEveryMinute() {
//        System.out.println("Namaste");
//    }

    @Scheduled(cron = "${my.cron.job}")
    public void performJobAccordingToEnvironment() {
        System.out.println("Environment");
    }
}

