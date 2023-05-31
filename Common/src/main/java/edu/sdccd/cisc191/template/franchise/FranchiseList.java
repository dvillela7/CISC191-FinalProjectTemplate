package edu.sdccd.cisc191.template.franchise;

// MODULE 10

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FranchiseList<Franchise> implements Serializable {
    public Node head;
    public Node tail;
    public Node current;

    class Node {
        Franchise data;
        Node prev;
        Node next;

        Node(Franchise data) {
            this.data = data;
        }
    }

    /**
     * Adds a franchise to the franchise list.
     *
     * @param data the franchise to add
     */
    public void add(Franchise data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
            current = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    /**
     * Removes a franchise from the franchise list.
     *
     * @param data the franchise to remove
     */
    public void remove(Franchise data) {
        Node currentNode = head;
        while (currentNode != null) {
            if (currentNode.data.equals(data)) {
                Node prevNode = currentNode.prev;
                Node nextNode = currentNode.next;

                if (prevNode != null)
                    prevNode.next = nextNode;
                else
                    head = nextNode;

                if (nextNode != null)
                    nextNode.prev = prevNode;
                else
                    tail = prevNode;

                if (currentNode == current)
                    current = nextNode;
                return;
            }

            currentNode = currentNode.next;
        }
    }

    /**
     * Converts the franchise list to an ArrayList.
     *
     * @return the ArrayList containing the franchise data
     */
    public List<Franchise> toArrayList() {
        List<Franchise> franchiseData = new ArrayList<>();
        Node currentNode = head;
        while (currentNode != null) {
            franchiseData.add(currentNode.data);
            currentNode = currentNode.next;
        }
        return franchiseData;
    }

    /**
     * Clears the franchise list.
     */
    public void clear() {
        head = null;
        tail = null;
        current = null;
    }

    /**
     * Returns a stream of franchises from the franchise list.
     *
     * @return the stream of franchises
     */
    public Stream<Franchise> stream() {
        List<Franchise> franchiseData = toArrayList();
        return franchiseData.stream();
    }
}