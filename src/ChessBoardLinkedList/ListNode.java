package ChessBoardLinkedList;

import Model.Cell;

public class ListNode {
    private ListNode north;
    private ListNode south;
    private ListNode east;
    private ListNode west;
    private ListNode northWest;
    private ListNode northEast;
    private ListNode southWest;
    private ListNode southEast;
    private ListNode upLeft;
    private ListNode leftUp;
    private ListNode downLeft;
    private ListNode leftDown;
    private ListNode rightUp;
    private ListNode upRight;
    private ListNode rightDown;
    private ListNode downRight;
    private Cell cellData;
    private char columnLetter;

    public ListNode(Cell cellData) {
        this.cellData = cellData;
        north = null;
        south = null;
        east = null;
        west = null;
        northWest = null;
        northEast = null;
        southWest = null;
        southEast = null;
        upLeft = null;
        leftUp = null;
        downLeft = null;
        leftDown = null;
        rightUp = null;
        upRight = null;
        rightDown = null;
        downRight = null;
    }

    public ListNode getNorth() {
        return north;
    }

    public void setNorth(ListNode north) {
        this.north = north;
    }

    public ListNode getSouth() {
        return south;
    }

    public void setSouth(ListNode south) {
        this.south = south;
    }

    public ListNode getEast() {
        return east;
    }

    public void setEast(ListNode east) {
        this.east = east;
    }

    public ListNode getWest() {
        return west;
    }

    public void setWest(ListNode west) {
        this.west = west;
    }

    public ListNode getNorthWest() {
        return northWest;
    }

    public void setNorthWest(ListNode northWest) {
        this.northWest = northWest;
    }

    public ListNode getNorthEast() {
        return northEast;
    }

    public void setNorthEast(ListNode northEast) {
        this.northEast = northEast;
    }

    public ListNode getSouthWest() {
        return southWest;
    }

    public void setSouthWest(ListNode southWest) {
        this.southWest = southWest;
    }

    public ListNode getSouthEast() {
        return southEast;
    }

    public void setSouthEast(ListNode southEast) {
        this.southEast = southEast;
    }

    public ListNode getUpLeft() {
        return upLeft;
    }

    public void setUpLeft(ListNode upLeft) {
        this.upLeft = upLeft;
    }

    public ListNode getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(ListNode leftUp) {
        this.leftUp = leftUp;
    }

    public ListNode getDownLeft() {
        return downLeft;
    }

    public void setDownLeft(ListNode downLeft) {
        this.downLeft = downLeft;
    }

    public ListNode getLeftDown() {
        return leftDown;
    }

    public void setLeftDown(ListNode leftDown) {
        this.leftDown = leftDown;
    }

    public ListNode getRightUp() {
        return rightUp;
    }

    public void setRightUp(ListNode rightUp) {
        this.rightUp = rightUp;
    }

    public ListNode getUpRight() {
        return upRight;
    }

    public void setUpRight(ListNode upRight) {
        this.upRight = upRight;
    }

    public ListNode getRightDown() {
        return rightDown;
    }

    public void setRightDown(ListNode rightDown) {
        this.rightDown = rightDown;
    }

    public ListNode getDownRight() {
        return downRight;
    }

    public void setDownRight(ListNode downRight) {
        this.downRight = downRight;
    }
}
