package ChessBoardLinkedList;

import Model.Cell;

public class LinkedList2D {
    private ListNode a1;

    public LinkedList2D() {
        boolean isCellWhite = true;
        a1 = new ListNode(new Cell(isCellWhite, 'a', 1));

        ListNode prev = a1;
        ListNode under = a1;
        for (int x = 0; x < 7; x++) {
            ListNode temp;
            for (int y = 98; y < 105; y++) {
                isCellWhite = !isCellWhite;
                temp = new ListNode(new Cell(isCellWhite, Character.toString(y).charAt(0), x + 1));
                prev.setEast(temp);
                temp.setWest(prev);
                prev = temp;
            }
            temp = new ListNode(new Cell(isCellWhite, 'a', x + 2));
            under.setNorth(temp);
        }
    }

}
