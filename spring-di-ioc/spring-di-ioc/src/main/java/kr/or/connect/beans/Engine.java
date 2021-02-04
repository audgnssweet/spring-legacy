package kr.or.connect.beans;

import org.springframework.stereotype.Component;

@Component
public class Engine {

    public Engine() {
        System.out.println("engine constructed");
    }

    public void run() {
        System.out.println("boorom boorom engine run!");
    }
}
