package com.tank.avt;

import lombok.val;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class NodeSample {

  public void test() {
    val words = Arrays.asList(
        new Node("a", 3),
        new Node("a", 8),
        new Node("a", 11),
        new Node("a", 14),
        new Node("a", 7),
        new Node("a", 13),
        new Node("a", 10));

    val test2 = Arrays.asList(new Node("a", 6),
        new Node("a", 13),
        new Node("a", 21),
        new Node("a", 10),
        new Node("a", 2));
    Queue<Node> queue = new ArrayDeque<>();

    //words.stream().forEach(queue::add);
    test2.stream().forEach(queue::add);

    Node root = queue.poll();
    while (!queue.isEmpty()) {
      val target = queue.poll();
      root = root.addNode(target);
    }

    System.out.println(root);
  }
}
