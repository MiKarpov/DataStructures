package com.mikhailkarpov.list;

import java.util.Iterator;

public class SinglyLinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private int size;

    public void addToFront(E e) {
        Node<E> node = new Node<>(e);
        node.setNext(head);
        head = node;
        size++;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public E removeFromFront() {
        if (isEmpty())
            return null;

        E removedElement = head.getElement();
        head = head.next;
        size--;

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

                currentNode = currentNode.getNext();
                return nextNode.getElement();
            }
        };
    }

    private class Node<E> {

        private final E element;
        private Node<E> next;

        public Node (E e) {
            this.element = e;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}