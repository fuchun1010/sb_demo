package com.tank.avt;

import lombok.Data;

import java.util.Objects;

/**
 * @author fuchun
 */
@Data
public class Node {

  private String value;
  private Node left;
  private Node right;
  private int code;
  private Node parent;

  public Node(final String value, int code) {
    this.value = value;
    this.code = code;
    this.right = null;
    this.left = null;
    this.parent = null;
  }

  @Override
  public String toString() {
    return String.format("value = %s, code = %d", this.value, this.code);
  }

  public Node addNode(Node newNode) {

    boolean isLeft = this.code > newNode.code;

    if (isLeft) {
      Node leftNode = this.searchLeftTree(this.left, newNode);
      if (leftNode == null) {
        this.left = newNode;
        newNode.parent = this;
      }
    } else {
      Node rightNode = this.searchRightTree(this.right, newNode);
      if (rightNode == null) {
        this.right = newNode;
        newNode.parent = this;
      }
    }

    return this;
  }

  private boolean isTree() {
    return !Objects.isNull(this.left) && !Objects.isNull(this.right);
  }

  private Node searchLeftTree(Node leftNode, Node targetNode) {
    if (targetNode.code == 1) {
      System.out.println("debug");
    }
    if (leftNode == null) {
      return null;
    } else {
      int leftValue = leftNode.code;
      int targetValue = targetNode.code;
      if (leftValue > targetValue) {
        if (leftNode.left == null) {
          //TODO ADD
          leftNode.left = targetNode;
          targetNode.parent = left;
          System.out.println("add node");
        }
        Node rs = this.searchLeftTree(leftNode.left, targetNode);
        if (rs != null) {
          return rs;
        }
      } else {

        //TODO 这个地方需要看看是否是一定需要这样调用才行
        Node rs = this.searchRightTree(leftNode.right, targetNode);
        if (rs != null) {
          return rs;
        } else {
          leftNode.right = targetNode;
          targetNode.parent = leftNode;
          return leftNode;
        }

      }
    }
    return null;
  }

  private Node searchRightTree(Node rightNode, Node targetNode) {

    if (rightNode == null) {
      return null;
    }
    //6
    int rightValue = rightNode.code;
    //8
    int targetValue = targetNode.code;
    if (targetValue > rightValue) {
      Node rs = this.searchRightTree(rightNode.right, targetNode);
      if (rs != null) {
        return rs;
      } else {
        System.out.println("....rigfht.....");
      }
    } else {
      //左旋转


      //TODO parentNode是root节点的时候需要特殊处理
      System.out.println("左旋转");

      Node parentNode = rightNode.parent;

      Node grandFather = parentNode.parent;

      //将右节点的父节点作为新节点的左节点
      targetNode.left = parentNode;
      parentNode.parent = targetNode;

      //将
      targetNode.right = rightNode;
      rightNode.parent = targetNode;

      grandFather.left = targetNode;
      targetNode.parent = grandFather;

      System.out.println("。。。旋转结束。。。");


      return targetNode;
    }
    return null;
  }


}
