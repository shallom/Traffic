package TrafficSystem;

public interface Movable {

    enum MOVEMENT{STRAIGHT, TURN_LEFT, TURN_RIGHT}
    void move();
}
