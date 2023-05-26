package com.qiqi.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @projectName: Test
 * @package: com.qiqi.algorithm
 * @className: Day12
 * @author: Eric
 * @description: TODO
 * @date: 2023/5/26 14:52
 * @version: 1.0
 */
public class Day12 {



    class LRUCache {
        class Node{
            int key;
            int value;
            Node pre;
            Node last;
            public Node(int key ,int value){
                this.key = key;
                this.value = value;
                pre = null;
                last = null;
            }
            public Node(){}

        }

        int size;
        HashMap<Integer,Node> map;
        Node head;
        Node last;
        public LRUCache(int capacity) {
            size = capacity;
            map = new HashMap<>();
            head = new Node();
            last = new Node();
            head.last = last;
            last.pre = head;
        }

        public int get(int key) {
            if (map.containsKey(key)){
                Node cur = map.get(key);
                moveToHead(cur);
                return cur.value;
            }else{
                return -1;
            }
        }

        private void moveToHead(Node cur) {
            deleteNode(cur);
            addHead(cur);
        }

        private void deleteNode(Node cur) {
            cur.last.pre = cur.pre;
            cur.pre.last = cur.last;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)){
                Node cur = map.get(key);
                cur.value = value;
                moveToHead(cur);
            }else{
                if (map.size() == size){
                    deleteLast();
                    map.remove(last.pre.key);
                }
                Node cur = new Node(key, value);
                map.put(key,cur);
                addHead(cur);
            }
        }

        private void addHead(Node cur) {
            head.last.pre = cur;
            cur.last = head.last;
            cur.pre = head;
            head.last = cur;
        }

        private void deleteLast() {
            map.remove(last.pre.key);
            last.pre = last.pre.pre;
            last.pre.pre.last = last;
            last.pre.pre = null;
            last.pre.last = null;
        }
    }


    class LRUCache1 {
        class Node{
            int key;
            int value;
            Node pre;
            Node next;
            public Node(int k,int v){
                key =k;
                value = v;
                pre = null;
                next = null;
            }
            public Node(){}
        }

        int size;
        Node head;
        Node tail;
        HashMap<Integer,Node> map;
        public LRUCache1(int capacity) {
            map = new HashMap<>();
            size = capacity;
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null){
                return -1;
            }
            deleteNode(node);
            addHead(node);
            return node.value;
        }

        private void addHead(Node node) {
            node.next = head.next;
            head.next.pre = node;
            node.pre = head;
            head.next = node;
        }

        private void deleteNode(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)){
                Node node = map.get(key);
                node.value = value;
                deleteNode(node);
                addHead(node);
            }else{
                if (size == map.size()){
                    Node node = tail.pre;
                    deleteNode(node);
                    map.remove(node.key);
                }
                Node node = new Node(key,value);
                map.put(key,node);
                addHead(node);
            }
        }
    }
    class LRUCache2 {
        class Node{
            int key;
            int value;
            Node pre;
            Node next;

            public Node(int k,int v){
                key = k;
                value = v;
            }
            public Node(){}
        }


        int size;
        Node head;
        Node tail;
        HashMap<Integer,Node> map;
        public LRUCache2(int capacity) {
            size = capacity;
            map = new HashMap<>();
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;
            deleteNode(node);
            addHead(node);
            return node.value;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)){
                Node cur = map.get(key);
                cur.value = value;
                deleteNode(cur);
                addHead(cur);
            }else{
                if (size == map.size()){
                    Node pre = tail.pre;
                    deleteNode(pre);
                    map.remove(pre.key);
                }
                Node cur = new Node(key,value);
                map.put(key,cur);
                addHead(cur);
            }
        }

        public void deleteNode(Node cur){
            cur.pre.next = cur.next;
            cur.next.pre = cur.pre;
        }
        public void addHead(Node cur){
            cur.next = head.next;
            head.next.pre = cur;
            cur.pre = head;
            head.next = cur;
        }

    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            int n = map.getOrDefault(num,0)+1;
            map.put(num,n);
        }
        PriorityQueue<Map.Entry<Integer,Integer>> queue = new PriorityQueue<>((a,b)->b.getValue() - a.getValue());
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            queue.add(integerIntegerEntry);
        }
        int [] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }


}
