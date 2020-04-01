package com.mikhailkarpov.list;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class DoublyLinkedList<E> implements Iterable<E> {

    private static final Logger LOGGER = LogManager.getLogger(DoublyLinkedList.class);

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        else if (index == 0) {
            addFirst(e);
            return;
        }
        else if (index == size()) {
            addLast(e);
            return;
        }

        Node<E> nodeAtIndex = getNode(index);
        Node<E> newNode = new Node<>(e, nodeAtIndex.previousNode, nodeAtIndex);

        nodeAtIndex.previousNode.nextNode = newNode;
        nodeAtIndex.previousNode = newNode;

        size++;
        LOGGER.info("{} added at index {}", e, index);
    }

    public void addFirst(E e) {
        if (isEmpty()) {
            head = new Node<>(e, null, null);
            tail = head;
        } else {
            Node<E> newHead = new Node<>(e, null, head);
            head.previousNode = newHead;
            head = newHead;
        }
        size++;
        LOGGER.info("Add first element: " + e);
    }

    public void addLast(E e) {
        if (isEmpty()) {
            head = new Node<>(e, null, null);
            tail = head;
        }
        else {
            Node<E> newNode = new Node<>(e, tail, null);
            tail.nextNode = newNode;
            tail = newNode;
        }
        size++;
        LOGGER.info("Add last element: " + e);
    }

    public void clear() {
        Node<E> pointer = head;
        while (pointer != null) {
            Node<E> nextNode = pointer.nextNode;
            pointer.previousNode = null;
            pointer.nextNode = null;
            pointer = nextNode;
        }
        size = 0;
        head = tail = null;
        LOGGER.info("List cleared");
    }

    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    public E get(int index) {
        return getNode(index).element;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        else if (isEmpty())
            throw new RuntimeException("List is empty");
        else if (index == 0)
            return head;
        else if (index == size() - 1)
            return tail;

        int mediumIndex = size() / 2;
        Node<E> resultNode = null;

        if (index < mediumIndex) {
            resultNode = head;
            for (int i = 1; i <= mediumIndex; i++) {
                resultNode = resultNode.nextNode;
            }
        } else {
            resultNode = tail;
            for (int i = size() - 1; i > mediumIndex; i--) {
                resultNode = resultNode.previousNode;
            }
        }
        return resultNode;
    }

    public E getFirst() {
        return head.element;
    }

    public E getLast() {
        return tail.element;
    }

    public int indexOf(E e) {
        if (isEmpty())
            return -1;
        if (head.element.equals(e))
            return 0;
        if (tail.element.equals(e))
            return size() - 1;

        Node<E> pointer = head;
        for (int i = 1; i < size() - 1; i++) {
            pointer = pointer.nextNode;
            if (pointer.element.equals(e))
                return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E remove(int index) {
        if (isEmpty()) {
            throw new RuntimeException("Removal failed. List is empty.");
        }

        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        else if (index == 0) {
            return removeFirst();
        }
        else if (index == size() - 1) {
            return removeLast();
        }

        Node<E> removedNode = getNode(index);
        Node<E> previousNode = removedNode.previousNode;
        Node<E> nextNode = removedNode.nextNode;

        previousNode.nextNode = nextNode;
        nextNode.previousNode = previousNode;

        E removedElement = removedNode.element;
        removedNode.previousNode = null;
        removedNode.nextNode = null;
        removedNode.element = null;
        size--;

        LOGGER.info("{} removed from index {}", removedElement, index);
        return removedElement;
    }

    public E removeFirst() {
        if (isEmpty())
            return null;

        Node<E> removedNode = head;

        if (head.nextNode == null) { // If removing the only element in the list, then the list is empty now
            head = null;
            tail = null;
        } else {                        // Adjusting head
            head = head.nextNode;
            head.previousNode = null;
        }
        size--;

        E removedElement = removedNode.element;
        LOGGER.info("First element {} removed", removedElement);
        return removedElement;
    }

    public E removeLast() {
        if (isEmpty())
            return null;

        Node<E> removedNode = tail;

        if (tail.previousNode == null) { // If removing the only element in the list, then the list is empty now
            head = null;
            tail = null;
        } else {                            // Adjusting tail
            tail = tail.previousNode;
            tail.nextNode = null;
        }
        size--;

        E removedElement = removedNode.element;
        LOGGER.info("Last element {} removed", removedElement);
        return removedElement;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> pointer = head;

            @Override
            public boolean hasNext() {
                return pointer != null;
            }

            @Override
            public E next() {
                Node<E> nextNode = pointer;
                if (pointer == null)
                    return null;

                pointer = pointer.nextNode;
                return nextNode.element;
            }
        };
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[ empty ]";

        StringBuilder sb = new StringBuilder("[ ");

        Node<E> pointer = head;
        while (pointer != null) {
            sb.append(pointer.element);
            pointer = pointer.nextNode;
            if (pointer != null) {
                sb.append(" <=> ");
            }
        }

        sb.append(" ]");
        return sb.toString();
    }

    /**
     * Internal class to represent data
     */
    private static class Node<E> {

        private E element;
        private Node<E> previousNode;
        private Node<E> nextNode;

        public Node (E e) {
            this.element = e;
        }

        public Node(E element, Node<E> previousNode, Node<E> nextNode) {
            this.element = element;
            this.previousNode = previousNode;
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}