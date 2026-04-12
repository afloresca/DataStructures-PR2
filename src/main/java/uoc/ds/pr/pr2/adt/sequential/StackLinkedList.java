package uoc.ds.pr.pr2.adt.sequential;

import edu.uoc.ds.adt.helpers.Position;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.Stack;
import edu.uoc.ds.traversal.Iterator;

/*
This class implements dynamic stack. that is a Listed Link stack implementation.

 */
public class StackLinkedList <E> extends LinkedList<E> implements Stack<E> {
    Position top;
    @Override
    public void push(E e) {
        top = super.insertBeginning(e);
    }

    @Override
    public E pop() {
        return super.deleteFirst();
    }

    @Override
    public E peek() {
        return (E) top.getElem();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public Iterator<E> values() {
        return super.values();
    }
}
