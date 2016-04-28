/* Set.java */

import list.*;

import java.util.Comparator;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
  /* Fill in the data fields here. */

    protected List list;
    protected int num;
   // protected ListNode listNode;


    /**
     * Set ADT invariants:
     *  1)  The Set's elements must be precisely the elements of the List.
     *  2)  The List must always contain Comparable elements, and those elements
     *      must always be sorted in ascending order.
     *  3)  No two elements in the List may be equals().
     **/

    /**
     *  Constructs an empty Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public Set() {
        list = new DList();

        // Your solution here.
    }

    /**
     *  cardinality() returns the number of elements in this Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public int cardinality() {
        // Replace the following line with your solution.
        return num;
    }

    /**
     *  insert() inserts a Comparable element into this Set.
     *
     *  Sets are maintained in sorted order.  The ordering is specified by the
     *  compareTo() method of the java.lang.Comparable interface.
     *
     *  Performance:  runs in O(this.cardinality()) time.
     **/
    public void insert(Comparable c) {
        boolean flag = false;
        try {
            if (list.isEmpty()) {
                list.insertFront(c);
                flag = true;
            }
            else {
                ListNode n = list.front();
                while(n != list.back()) {
                    if (c.compareTo(n.item()) < 0) {
                        n.insertBefore(c);
                        flag = true;
                        break;
                    }
                    else if (c.compareTo(n.item()) == 0) {
                        flag = false;
                        break;
                    }
                    n = n.next();
                }
                if (n == list.back() && (c.compareTo(n.item()) < 0)) {
                    n.insertBefore(c);
                    flag = true;
                }
                else if (n == list.back() && (c.compareTo(n.item()) == 0)){
                    flag = false;
                }
                else if (n == list.back() && (c.compareTo(n.item()) > 0)) {
                    n.insertAfter(c);
                    flag = true;
                }
            }
        }
        catch (InvalidNodeException lbe) {
            System.err.println ("Caught InvalidNodeException that should not happen."
            );
            System.err.println ("Aborting the testing code.");
        }

        if (flag) {
            num++;
        }
        else {
        }

        // Your solution here.
    }

    /**
     *  union() modifies this Set so that it contains all the elements it
     *  started with, plus all the elements of s.  The Set s is NOT modified.
     *  Make sure that duplicate elements are not created.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Your implementation should NOT copy elements of s or "this", though it
     *  will copy _references_ to the elements of s.  Your implementation will
     *  create new _nodes_ for the elements of s that are added to "this", but
     *  you should reuse the nodes that are already part of "this".
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
     **/
    public void union(Set s) {
        try {
            //空集
            if (s.list.isEmpty()) {}

            else {

                ListNode ns = s.list.front();
                ListNode nt = this.list.front();

                // 第一种情况: s最小大于等于this最大
                if (((Comparable)s.list.front().item()).compareTo(this.list.back().item()) >= 0) {
                    if (((Comparable)s.list.front().item()).compareTo(this.list.back().item()) == 0 && s.num == 1) {
                        return;
                    }
                    else if (((Comparable)s.list.front().item()).compareTo(this.list.back().item()) > 0 && s.num == 1) {
                        this.list.back().insertAfter(s.list.back().item());
                        return;
                    }
                    else {
                        if (((Comparable)s.list.front().item()).compareTo(this.list.back().item()) == 0) {
                            ns = ns.next();
                        }
                        while(ns != s.list.back()) {
                            this.list.back().insertAfter(ns.item());
                            ns = ns.next();
                        }
                        this.list.back().insertAfter(s.list.back().item());
                        return;
                    }
                }

                //第二种情况:s最大 小于等于 this最小
                else if (((Comparable)s.list.back().item()).compareTo(this.list.front().item()) <= 0) {
                    if (((Comparable)s.list.back().item()).compareTo(this.list.front().item()) == 0 && s.num ==1) {
                        return;
                    }
                    else if (((Comparable)s.list.back().item()).compareTo(this.list.front().item()) < 0 && s.num ==1) {
                        this.list.front().insertBefore(s.list.front().item());
                        return;
                    }
                    else {
                        if (((Comparable)s.list.back().item()).compareTo(this.list.front().item()) == 0) {
                            this.list.front().remove();
                        }
                        this.list.front().insertBefore(ns.item());
                        ns = ns.next();
                        while(ns != s.list.back()) {
                            this.list.front().insertAfter(ns.item());
                            ns = ns.next();
                        }
                        this.list.front().insertAfter(s.list.back().item());
                        return;
                    }
                }

                //第三种情况,有交叉
                else {
                    do {
                        if (((Comparable)ns.item()).compareTo(nt.item()) > 0) {
                            nt = nt.next();
                            System.out.println("1");
                        }
                        else if (((Comparable)ns.item()).compareTo(nt.item()) < 0) {
                            nt.insertBefore(ns.item());
                            ns = ns.next();
                            System.out.println("2");
                        }
                        else if (((Comparable)ns.item()).compareTo(nt.item()) == 0) {
                            ns = ns.next();
                            nt = nt.next();
                            System.out.println("3");
                        }
//                    System.out.println("hello");
                    } while(ns != s.list.back() && nt != this.list.back());

                    if (ns == s.list.back() && nt == this.list.back()) {
                        if (((Comparable)ns.item()).compareTo(nt.item()) < 0) {
                            nt.insertBefore(ns.item());
                            return;
                        }
                        else if (((Comparable)ns.item()).compareTo(nt.item()) > 0) {
                            nt.insertAfter(ns.item());
                            return;
                        }
                        else {
                            return;
                        }
                    }

                    if (ns == s.list.back() && nt != this.list.back()) {

                        while(((Comparable)ns.item()).compareTo(nt.item()) >= 0) {
                            if (((Comparable)ns.item()).compareTo(nt.item()) == 0) {
                                return;
                            }
                            nt = nt.next();
                        }
                        nt.insertBefore(ns.item());
                    }

                    if (ns != s.list.back() && nt == this.list.back()) {
                        while(ns != s.list.front()) {
                            if (((Comparable)ns.item()).compareTo(this.list.back().item()) < 0) {
                                this.list.back().insertBefore(ns.item());
                                ns = ns.next();
                            }
                            else if (((Comparable)ns.item()).compareTo(this.list.back().item()) > 0) {
                                this.list.back().insertAfter(ns.item());
                                ns = ns.next();
                            }
                            else if (((Comparable)ns.item()).compareTo(this.list.back().item()) == 0) {
                                ns = ns.next();
                            }
                        }
                    }
                }
            }
        }
        catch (InvalidNodeException lbe) {
            System.err.println ("Caught InvalidNodeException that should not happen."
            );
            System.err.println ("Aborting the testing code.");
        }

        // Your solution here.
    }

    /**
     *  intersect() modifies this Set so that it contains the intersection of
     *  its own elements and the elements of s.  The Set s is NOT modified.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Do not construct any new ListNodes during the execution of intersect.
     *  Reuse the nodes of "this" that will be in the intersection.
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT CONSTRUCT ANY NEW NODES.
     *  DO NOT ATTEMPT TO COPY ELEMENTS.
     **/
    public void intersect(Set s) {

        try {
            if (s.list.isEmpty()) {
                this.list = s.list;
                return;
            }

            else {

                ListNode ns = s.list.front();
                ListNode nt = this.list.front();
                ListNode tmp = nt;

                do {
                    if (((Comparable)ns.item()).compareTo(nt.item()) < 0) {
                        ns = ns.next();
                        //this.list = new DList();
                    }
                    else if (((Comparable)ns.item()).compareTo(nt.item()) > 0) {
                        tmp = nt.next();
                        nt.remove();
                        nt = tmp;
                    }
                    else {
                        nt = nt.next();
                        ns = ns.next();
                    }
                } while(ns != s.list.back() && nt != this.list.back());

                if (ns == s.list.back() && nt == this.list.back()) {
                    if (((Comparable)ns.item()).compareTo(nt.item()) == 0) {
                        return;
                    }
                    else {
                        nt.remove();
                        return;
                    }
                }

                else if (ns == s.list.back() && nt != this.list.back()) {
                    while(nt != this.list.back()) {
                        if (((Comparable)ns.item()).compareTo(nt.item()) != 0) {
                            tmp = nt.next();
                            nt.remove();
                            nt = tmp;
                        }
                        else {
                            return;
                        }
                    }
                    if (((Comparable)ns.item()).compareTo(nt.item()) != 0) {
                        nt.remove();
                        return;
                    }
                    else{
                        return;
                    }
                }

                else if (ns != s.list.back() && nt == this.list.back()) {
                    while(ns != s.list.back()) {
                        if (((Comparable)ns.item()).compareTo(nt.item()) != 0) {
                            ns = ns.next();
                        }
                        else {
                            return;
                        }
                    }
                    if (((Comparable)ns.item()).compareTo(nt.item()) != 0) {
                        nt.remove();
                        return;
                    }
                    else {
                        return;
                    }
                }
            }
        }
        catch (InvalidNodeException lbe) {
            System.err.println ("Caught InvalidNodeException that should not happen."
            );
            System.err.println ("Aborting.");
        }

        // Your solution here.
    }

    /**
     *  toString() returns a String representation of this Set.  The String must
     *  have the following format:
     *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
     *            between them.
     *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
     *            "{" or after "}"; two spaces before and after each element.
     *            Elements are printed with their own toString method, whatever
     *            that may be.  The elements must appear in sorted order, from
     *            lowest to highest according to the compareTo() method.
     *
     *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
     *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
     *            DEVIATIONS WILL LOSE POINTS.
     **/
    public String toString() {
        String res = "{ ";
        try {
            if (list.isEmpty()) {
                res = res + " }";
            }
            else {
                ListNode tmp = list.front();
                while(tmp != list.back()) {
                    res = res + tmp.item() + " ";
                    tmp = tmp.next();
                }
                res = res + list.back().item() + " }";
            }
        }
        catch (InvalidNodeException lbe) {
            System.err.println ("Caught InvalidNodeException that should not happen."
            );
            System.err.println ("Aborting the testing code.");
        }

        return res;

        // Replace the following line with your solution.
//        return "";
    }

    public static void main(String[] argv) {
        Set s = new Set();
        s.insert(new Integer(3));
        s.insert(new Integer(4));
        s.insert(new Integer(3));
        System.out.println("Set s = " + s);

        Set s2 = new Set();
        s2.insert(new Integer(4));
        s2.insert(new Integer(5));
        s2.insert(new Integer(5));
        System.out.println("Set s2 = " + s2);

        Set s3 = new Set();
        s3.insert(new Integer(5));
        s3.insert(new Integer(3));
        s3.insert(new Integer(8));
        System.out.println("Set s3 = " + s3);

        s.union(s2);
        System.out.println("After s.union(s2), s = " + s);

        s.intersect(s3);
        System.out.println("After s.intersect(s3), s = " + s);

        System.out.println("s.cardinality() = " + s.cardinality());


        //空集测试
        Set s4 = new Set();
        System.out.println("Empty Set s4 = " + s4);

        System.out.println("s4.cardinality() = " + s4.cardinality());

        s4.union(s4);
        System.out.println("After s4.union(s4), s4 = " + s4);

        s4.intersect(s4);
        System.out.println("After s4.intersect(s4), s4 = " + s4);

        s3.union(s4);
        System.out.println("After s3.union(s4), s3 = " + s3);

        s3.intersect(s4);
        System.out.println("After s3.intersect(s4), s3 = " + s3);
        // You may want to add more (ungraded) test code here.
    }
}
