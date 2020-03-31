package com.mikhailkarpov.list;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class SinglyLinkedList<E> implements Iterable<E> {

    private static final Logger LOGGER = LogManager.getLogger(SinglyLinkedList.class);

    private Node<E> head;
    private int size;

    public void addFirst(E e) {
        LOGGER.info("Add first element: " + e);
        Node<E> node = new Node<>(e);
        node.nextElement = head;
        head = node;
        size++;
    }

    public void addAt(int index, E e) {
        if (index < 0 || index > size()) {
            throw new IllegalArgumentException("Illegal index: " + index);
        }
        else if (index == 0) {
            addFirst(e);
        }
        else {
            LOGGER.info("Adding element {} at index {}", e, index);
            Node<E> pointer = head;
            for (int i = 0; i < index - 1; i++) {
                pointer = pointer.nextElement;
            }
            Node<E> newNode = new Node<>(e);
            newNode.nextElement = pointer.nextElement;
            pointer.nextElement = newNode;
            size++;
        }
    }

    public void clear() {
        LOGGER.info("Clear list");
        for (Node<E> pointer = head; pointer != null; ) {
            Node<E> next = pointer.nextElement;
            pointer.element = null;
            pointer.nextElement = null;
            pointer = next;
        }
        head = null;
        size = 0;
    }

    public boolean contains(E e) {
        if (isEmpty())
            return false;

        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(E e) {
        if (isEmpty())
            return -1;

        Iterator<E> iterator = iterator();
        int index = 0;
        while (iterator.hasNext()) {
            if (iterator.next().equals(e)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E removeFirst() {
        if (isEmpty()) {
            String errMsg = "List is empty. Removing failed";
            LOGGER.warn(errMsg);
            throw new RuntimeException(errMsg);
        }

        Node<E> removedNode = head;
        head = head.nextElement;
        removedNode.nextElement = null;
        size--;

        E removedElement = removedNode.element;
        LOGGER.info("Removing first element: " + removedElement);
        return removedElement;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public E next() {
                Node<E> nextNode = currentNode;
                if (currentNode == null)
                    return null;

                currentNode = currentNode.nextElement;
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
            pointer = pointer.nextElement;
            if (pointer != null) {
                sb.append(" => ");
            }
        }

        sb.append(" ]");
        return sb.toString();
    }

    private class Node<E> {

        private E element;
        private Node<E> nextElement;

        public Node (E e) {
            this.element = e;
        }
    }
}