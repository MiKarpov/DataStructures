package com.mikhailkarpov.list;

import java.util.Iterator;

public class DoubleLinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public void addFirst(E e) {
        Node<E> node = new Node<>(e);
        node.nextElement = head;

        if (isEmpty()) {
            tail = node;
        } else {
            head.previousElement = node;
        }

        head = node;
        size++;
    }

    public void addLast(E e) {
        Node<E> node = new Node<>(e);
        node.previousElement = tail;

        if (isEmpty()) {
            head = node;
        } else {
            tail.nextElement = node;
        }

        tail = node;
        size++;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public E removeFirst() {
        if (isEmpty())
            return null;

        Node<E> removedNode = head;

        if (head.nextElement == null) { // If removing the only element in the list, then the list is empty now
            head = null;
            tail = null;
        } else {                        // Adjusting head
            head = head.nextElement;
            head.previousElement = null;
        }
        size--;

        return removedNode.element;
    }

    public E removeLast() {
        if (isEmpty())
            return null;

        Node<E> removedNode = tail;

        if (tail.previousElement == null) { // If removing the only element in the list, then the list is empty now
            head = null;
            tail = null;
        } else {                            // Adjusting tail
            tail = tail.previousElement;
            tail.nextElement = null;
        }
        size--;

        return removedNode.element;
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

    private class Node<E> {

        private final E element;
        private Node<E> nextElement;
        private Node<E> previousElement;

        public Node (E e) {
            this.element = e;
        }
    }
}