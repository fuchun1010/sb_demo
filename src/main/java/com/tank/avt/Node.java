package com.tank.avt;

import lombok.Data;
import lombok.val;

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

  public Node(final String value) {
    this.value = value;
    this.code = this.calculateCode();
  }

  public Node(final String value, int code) {
    this(value);
    this.code = code;

  }

  @Override
  public String toString() {
    val tips = String.format("value = %s, code = %d", this.value, this.code);
    return tips;
  }


  public Node addNode(final Node target) {
    Node root = this;
    root = this.addNode(root, target);
    return root;
  }


  @Override
  public int hashCode() {
    return this.calculateCode();
  }

  @Override
  public boolean equals(Object obj) {
    boolean isNode = obj instanceof Node;
    if (!isNode) {
      return false;
    }
    return this.code == ((Node) obj).getCode();
  }

  private Node addNode(Node node, Node newNode) {
    boolean isLeft = node.getCode() > newNode.getCode();

    if (isLeft) {
      // left path
      System.out.println("process left path");
      Node rs = this.searchLeftNodes(node.left, newNode, node);
      if (rs.equals(node)) {
        if (rs.left != null) {
          //TODO 有问题
          if (rs.left.getCode() < newNode.getCode()) {
            newNode.right = rs.right;
            rs.right = null;
            newNode.left = rs;
            return newNode;
          }
          newNode.left = rs.left;
          Node rightNode = rs.right;
          newNode.right = rightNode;
          rightNode.left = rs;
          return newNode;
        }

        Node tmp = rs.left;
        rs.left = newNode;
        newNode.left = tmp;
      } else {
        //not self

        if (rs.left == null) {
          rs.left = newNode;
        } else if (rs.code > newNode.code) {
          System.out.println("problem");
          newNode.left = rs.left;
          node.left = newNode;
          newNode.right = rs;
          rs.left = null;
        } else {
          Node tmp = rs.left;
          rs.left = newNode;
          newNode.left = tmp;
        }

      }
    } else {
      // right path
      Node rs = this.searchRightNodes(node.right, newNode, node);
      if (rs.equals(node)) {
        rs.right = newNode;
      } else {
        if (newNode.code < rs.code) {

          if (rs.right == null) {
            //TODO issue
            //rs.left = newNode;
            newNode.right = rs;
            node.right = newNode;
            System.out.println("right path 100");

          } else {

            Node tmp = rs.left;
            rs.left = newNode;
            newNode.left = tmp;

          }
        } else {
          Node tmp = node.right;
          node.right.right = newNode;
          node.right.left = node;
          node.right = null;
          return tmp;
        }

      }
      return node;
    }

    return node;
  }


  private Node searchRightNodes(Node node, Node newNode, Node parent) {
    if (node == null) {
      return parent;
    }
    boolean isOk = newNode.getCode() > node.getCode();
    if (newNode.code == 77) {
      System.out.println("...");
    }
    if (isOk) {
      Node rs = this.searchRightNodes(node.right, newNode, node);
      if (rs != null) {
        return rs;
      }
    } else {
      Node xx = this.searchLeftNodes(node.left, newNode, node);
      if (xx != null) {
        return xx;
      } else {
        return node;
      }
      //return node;
    }
    return null;
  }

  private Node searchLeftNodes(Node node, Node newNode, Node parent) {
    if (node == null) {
      return parent;
    }

    boolean isLeft = node.getCode() > newNode.getCode();
    if (isLeft) {
      Node rs = this.searchLeftNodes(node.left, newNode, node);
      if (rs != null) {
        return rs;
      }
    } else {

      return parent;
    }

    return null;
  }

  private int calculateCode() {
    return Objects.hashCode(value) >>> 16;
  }


}
