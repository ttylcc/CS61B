package list;

import java.util.concurrent.locks.Lock;

/**
 * Created by jade on 4/25/16.
 */
public class LockDList extends DList {

    protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, (LockDListNode) prev, (LockDListNode) next);
    }

    public void lockNode (DListNode node) {
        ((LockDListNode)node).lk = true;
    }

    public void remove(DListNode node) {
        if (!((LockDListNode)node).lk) {
//            node.prev.next = node.next;
//            node.next.prev = node.prev;
//            size--;
            super.remove(node);
        }
        else {
            return;
        }

        // Your solution here.
    }

}
