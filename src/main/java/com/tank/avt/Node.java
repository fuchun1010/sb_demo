package com.tank.avt;

import lombok.Data;

/**
 * @author fuchun
 */
@Data
public class Node {

  private String value;
  private Node left;
  private Node right;
  private int code;


  public Node(final String value, int code) {
    this.value = value;
    this.code = code;
    this.right = null;
    this.left = null;
  }

  @Override
  public String toString() {
    return String.format("value = %s, code = %d", this.value, this.code);
  }

  public Node addNode(Node newNode) {

    boolean isLeft = this.code > newNode.code;
    Node targetNode = null;
    if (isLeft) {
      if (this.left == null) {
        this.left = newNode;
      } else {
        targetNode = this.searchLeftTree(this.left, newNode);
      }
    } else {
      if (this.right == null) {
        this.right = newNode;
      } else {
        targetNode = this.searchRightTree(this.right, newNode);
      }
    }

    boolean isExists = targetNode != null;
    if (isExists) {
      if (targetNode.left == null) {
        targetNode.left = newNode;
      } else {
        targetNode.right = newNode;
      }
    }

    return this;
  }


  private Node searchLeftTree(Node leftNode, Node targetNode) {

    int leftValue = leftNode.code;
    int targetValue = targetNode.code;
    if (leftValue > targetValue) {
      if (leftNode.left == null) {
        return leftNode;
      } else {
        Node tmp = this.searchLeftTree(leftNode.left, targetNode);
        if (tmp != null) {
          return tmp;
        }
      }
    } else {
      if (leftNode.right == null) {
        return leftNode;
      } else {
        Node tmp = this.searchRightTree(leftNode.right, targetNode);
        if (tmp != null) {
          return tmp;
        }
      }
    }

    return null;
  }

  private Node searchRightTree(Node rightNode, Node targetNode) {
    int rightValue = rightNode.code;
    int targetValue = targetNode.code;
    if (targetValue > rightValue) {
      if (rightNode.right == null) {
        return rightNode;
      } else {
        Node tmp = this.searchRightTree(rightNode.right, targetNode);
        if (tmp != null) {
          return tmp;
        }
      }
    } else {
      if (rightNode.left == null) {
        return rightNode;
      } else {
        Node tmp = this.searchLeftTree(rightNode.left, targetNode);
        if (tmp != null) {
          return tmp;
        }
      }
    }

    return null;
  }


}
