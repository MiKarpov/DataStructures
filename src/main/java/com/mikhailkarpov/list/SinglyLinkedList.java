package com.mikhailkarpov.list;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

public class SinglyLinkedList<E> implements Iterable<E> {

    private static final Logger LOGGER = LogManager.getLogger(SinglyLinkedList.class);

    private Node<E> head;
    private int size;

    public void addFirst(E e) {
        head = new Node<>(e, head);
        size++;
        LOGGER.info("Add first: " + e);
    }

    public void addAt(int index, E e) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        else if (index == 0) {
            addFirst(e);
        }
        else {
            LOGGER.info("Adding element {} at index {}", e, index);
            Node<E> pointer = head;
            for (int i = 0; i < index - 1; i++) {
                pointer = pointer.nextNode;
            }
            Node<E> newNode = new Node<>(e, pointer.nextNode);
            pointer.nextNode = newNode;
            size++;
        }
    }

    public void clear() {
        LOGGER.info("Clear list");
        for (Node<E> pointer = head; pointer != null; ) {
            Node<E> next = pointer.nextNode;
            pointer.element = null;
            pointer.nextNode = null;
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

    public E getFirst() {
        if (isEmpty())
            throw new RuntimeException("List is empty");

        E element = head.element;
        LOGGER.info("First element fetched: " + element);
        return element;
    }

    public E get(int index) {
        if (isEmpty())
            throw new RuntimeException("Removing failed. List is empty");
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        else if (index == 0)
            return head.element;

        Node<E> resultNode = head;
        for (int i = 1; i <= index; i++) {
            resultNode = resultNode.nextNode;
        }
        return resultNode.element;
    }

    public E remove(int index) {
        if (isEmpty())
            throw new RuntimeException("Removing failed. List is empty");
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        else if (index == 0) {
            return removeFirst();
        }

        Node<E> removedNode = head;
        Node<E> previousNode = null;
        for (int i = 1; i <= index; i++) {
            previousNode = removedNode;
            removedNode = removedNode.nextNode;
        }

        E removedElement = removedNode.element;
        previousNode.nextNode = removedNode.nextNode;
        removedNode.nextNode = null;
        size--;

        LOGGER.info("Removing element {} at index {}", removedElement, index);
        return removedElement;
    }

    public E removeFirst() {
        if (isEmpty()) {
            String errMsg = "List is empty. Removing failed";
            LOGGER.warn(errMsg);
            throw new RuntimeException(errMsg);
        }

        Node<E> removedNode = head;
        head = head.nextNode;
        removedNode.nextNode = null;
        size--;

        E removedElement = removedNode.element;
        LOGGER.info("Removing first element: " + removedElement);
        return removedElement;
    }

    public boolean set(int index, E e) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        else if(isEmpty() && index == 0) {
            addFirst(e);
            return true;
        }

        if (index == 0) {
            head = new Node<>(e, head);
        }
        else {
            Node<E> pointer = head;
            for (int i = 1; i < index; i++) {
                pointer = pointer.nextNode;
            }
            pointer.nextNode = new Node<>(e, pointer.nextNode.nextNode);
        }
        LOGGER.info("Set {} at index {}", e, index);
        return true;
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

                currentNode = currentNode.nextNode;
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
                sb.append(" => ");
            }
        }

        sb.append(" ]");
        return sb.toString();
    }

    private static class Node<E> {

        private E element;
        private Node<E> nextNode;

        public Node (E e) {
            this(e, null);
        }

        public Node(E element, Node<E> nextNode) {
            this.element = element;
            this.nextNode = nextNode;
        }
    }
}