import java.util.LinkedList;
import java.util.Queue;

interface QueueBehaviour {
    void enqueue(String person);
    String dequeue();
}

interface MarketBehaviour {
    void enterMarket(String person);
    void exitMarket(String person);
    void update();
}

public class Main {
    public static void main(String[] args) {
        Market market = new Market(10); // Assume capacity of 10
        market.enterMarket("Alice");
        market.enqueue("Alice");
        market.enqueue("Bob");
        market.enterMarket("Bob");
        market.enqueue("Charlie");
        market.enterMarket("Charlie");

        System.out.println("Dequeuing: " + market.dequeue());
        System.out.println("Dequeuing: " + market.dequeue());

        market.update();

        market.exitMarket("Alice");
        market.exitMarket("Bob");
        market.exitMarket("Charlie");
    }
}

class Market implements QueueBehaviour, MarketBehaviour {
    private Queue<String> queue;
    private int capacity;
    private int currentCapacity;

    public Market(int capacity) {
        this.capacity = capacity;
        this.currentCapacity = 0;
        this.queue = new LinkedList<>();
    }

    @Override
    public void enqueue(String person) {
        if (currentCapacity < capacity) {
            queue.add(person);
            currentCapacity++;
            System.out.println(person + " joined the queue.");
        } else {
            System.out.println("Queue is full. " + person + " cannot join.");
        }
    }

    @Override
    public String dequeue() {
        if (!queue.isEmpty()) {
            currentCapacity--;
            return queue.poll();
        }
        return null;
    }

    @Override
    public void enterMarket(String person) {
        System.out.println(person + " entered the market.");
    }

    @Override
    public void exitMarket(String person) {
        System.out.println(person + " exited the market.");
    }

    @Override
    public void update() {
        System.out.println("Market state updated.");
        // Additional update logic can be implemented here
    }
}