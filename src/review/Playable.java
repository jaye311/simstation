package review;

import java.util.List;

interface Playable {
    public void play();
}
abstract class OrderingStrategy {
    protected PlayList context;
    abstract public Playable next();
    abstract Boolean hasNext();
}

class VideoRecordingAdapter implements Playable {
    private VideoRecording adaptee;
    public void play() {
        adaptee.stream();
    }
}
class VideoRecording{
    public void stream(){}
}

class PlayList implements Playable {
    private OrderingStrategy strategy;
    private List<Playable> recordings;
    public void play() {
        while(strategy.hasNext()) {
            strategy.next().play();
        }
    }
}

