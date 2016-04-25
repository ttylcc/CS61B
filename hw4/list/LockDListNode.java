package list;

/**
 * Created by jade on 4/25/16.
 */
public class LockDListNode extends DListNode {

    protected boolean lk;

    LockDListNode (Object i, LockDListNode p, LockDListNode n) {
        super(i, p, n);
        lk = false;
    }

}
