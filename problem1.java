import java.util.HashMap;

class LRUCache {

    class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    HashMap<Integer, Node> mapp;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        mapp = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
    }

    public void addToHead(Node node) {
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        node.prev = head;

    }

    public void removeTail(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;

    }

    public int get(int key) {
        if (!mapp.containsKey(key)) {
            return -1;
        }
        Node node = mapp.get(key);
        removeTail(node);
        addToHead(node);
        return node.value;

    }

    public void put(int key, int value) {

        if (mapp.containsKey(key)) {
            Node node = mapp.get(key);
            node.value = value;
            removeTail(node);
            addToHead(node);
        } else {
            if (mapp.size() >= capacity) {
                Node lruNode = tail.prev;
                removeTail(lruNode);
                mapp.remove(lruNode.key);
            }
            Node newNode = new Node(key, value);
            mapp.put(key, newNode);
            addToHead(newNode);
        }

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */