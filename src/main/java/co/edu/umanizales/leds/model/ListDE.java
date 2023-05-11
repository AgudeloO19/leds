package co.edu.umanizales.leds.model;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;


@Data

public class ListDE {

    private NodeDE head;
    private NodeDE tail;
    private int size;
    private List<Led> leds = new ArrayList<>();

    public void addLed(Led led) {

        int size = 0;

        if (head == null) {
            head = new NodeDE(led);
        } else {
            NodeDE newNode = new NodeDE(led);
            NodeDE current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
            newNode.setPrevious(current);
        }
        size++;
    }
    public List <Led> print(){
        leds.clear();
        if (head != null){
            NodeDE temp = head;
            while (temp != null){
                leds.add(temp.getData());
                temp = temp.getNext();
            }
        }
        return leds;
    }

    public void restart (){
        NodeDE temp = head;
        while (temp != null){
            temp.getData().setStatus(false);
            temp.getData().setDateoff(null);
            temp.getData().setDateon(null);

            temp = temp.getNext();
        }


    }
    public void addToStart(Led led)  {
        NodeDE newNode = new NodeDE(led);
        if (head != null) {
            head.setPrevious(newNode);
            newNode.setNext(head);
        }
        head = newNode;
        size++;
    }

    public void addToEnd(Led led) {
        NodeDE newNode = new NodeDE(led);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        size++;
    }
    public void turnOnLight() throws InterruptedException {
        if (size % 2 == 0){
            int medium = (size/2);
            NodeDE temp = head;
            int pasos = 1;

            while (temp != null){
                if (pasos == (medium + 1)){
                    temp.getData().setStatus(true);
                    temp.getData().setDateon(LocalTime.from(LocalDateTime.now()));

                    NodeDE temp2 = temp.getPrevious();
                    temp2.getData().setStatus(true);
                    temp2.getData().setDateon(LocalTime.from(LocalDateTime.now()));

                    if (temp.getNext() != null){
                        while (temp.getNext() != null){
                            Thread.sleep(1000);
                            temp.getData().setStatus(false);
                            temp.getData().setDateoff(LocalTime.from(LocalDateTime.now()));

                            temp2.getData().setStatus(false);
                            temp2.getData().setDateoff(LocalTime.from(LocalDateTime.now()));

                            temp = temp.getNext();
                            temp.getData().setStatus(true);
                            temp.getData().setDateon(LocalTime.from(LocalDateTime.now()));

                            temp2 = temp.getNext();
                            temp2.getData().setStatus(true);
                            temp2.getData().setDateon(LocalTime.from(LocalDateTime.now()));
                        }
                    }
                }
                pasos ++;
                temp = temp.getNext();
            }
        }else{
            int medium = (size/2)+1;
            NodeDE temp = head;
            int pasos = 1;
            while (temp != null){
                if (pasos == medium){
                    temp.getData().setStatus(true);
                    temp.getData().setDateon(LocalTime.from(LocalDateTime.now()));
                    NodeDE temp2 = temp;
                    if (temp.getNext() != null){
                        Thread.sleep(1000);
                        temp.getData().setStatus(false);
                        temp.getData().setDateoff(LocalTime.from(LocalDateTime.now()));

                        temp2.getData().setStatus(false);
                        temp2.getData().setDateoff(LocalTime.from(LocalDateTime.now()));

                        temp = temp.getNext();
                        temp.getData().setStatus(true);
                        temp.getData().setDateon(LocalTime.from(LocalDateTime.now()));

                        temp2 = temp.getNext();
                        temp2.getData().setStatus(true);
                        temp2.getData().setDateon(LocalTime.from(LocalDateTime.now()));
                    }
                }
            }
            pasos ++;
            temp = temp.getNext();
        }
    }




















}
