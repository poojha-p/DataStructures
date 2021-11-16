public class Main {

    public static void main(String[] args) {
        LinkedList list = new LinkedList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.removeAtIndex(1);
        System.out.println("List 1: " + list.toString());
        System.out.println();

        LinkedList list2 = new LinkedList<Integer>();
        list2.add(1);
        list2.add(2);
        System.out.println("List 2: " + list2.toString());
        System.out.println("List2 = List1? " + list.isEqual(list2));
        System.out.println();

        list2.remove(1);
        list2.add(3);
        System.out.println("List 2: " + list2.toString());
        System.out.println("List2 = List1? " + list.isEqual(list2));

        LinkedList list3 = new LinkedList<Integer>();
        list3.add(1);
        list3.add(2);
        list3.add(3);
        list3.add(2);
        list3.add(1);
        System.out.println("List 3: " + list3.toString());
        System.out.println("Symmetrical? " + list3.isSymmetrical());
        System.out.println("List 3 Contains cycle? " + list3.containsCycle());
        System.out.println();

        LinkedList list4 = new LinkedList<Integer>();
        list4.add(1);
        list4.add(2);
        list4.add(3);
        list4.add(2);
        System.out.println("List 4: "  + list4.toString());
        System.out.println("Symmetrical? " + list4.isSymmetrical());
        list4.reverse();
        System.out.println("Reversed: " + list4.toString());
        System.out.println();

        /**int counter = 0;
        
        Node temp = null;
        Node temp2 = (Node) list5.getHead();
        while (temp2.hasNext()) {
            temp2 = temp2.getNext();
            if (counter == 2) {
                temp = temp2;
            }
            counter++;
        }

        temp2.setNext(temp);**/

        LinkedList list5 = new LinkedList<Integer>();
        list5.add(1);
        list5.add(2);
        list5.add(3);
        System.out.println("List 5: " + list5.toString());
        list5.multiply(2);
        System.out.println("Multiply: "  + list5.toString());
        list5.removeRepeats();
        System.out.println("Without repeats: " + list5.toString());
        System.out.println();

        System.out.println("List 3: " + list3.toString());
        System.out.println("List 5: " + list5.toString());
        list5.merge(list3);
        System.out.println("List 5 merges List 3: " + list5.toString());
    }
}
