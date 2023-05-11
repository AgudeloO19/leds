package co.edu.umanizales.leds.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter

public class NodeDE {

    private Led data;

    private NodeDE next;

    private NodeDE previous;

    public  NodeDE (Led data)
    {
        this.data = data;
    }




}
