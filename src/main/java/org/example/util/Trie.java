package org.example.util;

import java.util.*;

public class Trie {
    Runtime rn = Runtime.getRuntime();
    private static class TrieNode {
        boolean isEndOfWord;
        List<TrieNode> children;
        int codePoint;
        List<Short> values;

        public TrieNode() {
            children = new ArrayList<>();
            values = new ArrayList<>();
            isEndOfWord = false;
            codePoint = -1;
        }

        public void addChild(int codePoint, TrieNode node) {
            node.codePoint = codePoint;
            children.add(node);
        }

        public TrieNode getChild(int codePoint) {
            for (TrieNode node : children) {
                if (node.codePoint == codePoint) {
                    return node;
                }
            }
            return null;
        }
    }

    private final TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String key, List<Short> values) {
        TrieNode currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            int codePoint = key.codePointAt(i);
            TrieNode childNode = currentNode.getChild(codePoint);
            if (childNode == null) {
                childNode = new TrieNode();
                currentNode.addChild(codePoint, childNode);
            }
            currentNode = childNode;
        }
        currentNode.values = values;
        currentNode.isEndOfWord = true;
    }

    public List<Short> search(String key) {
        TrieNode currentNode = root;
        for (int i = 0; i < key.length(); i++) {
            int codePoint = key.codePointAt(i);
            TrieNode childNode = currentNode.getChild(codePoint);
            if (childNode == null) {
                return null;
            }
            currentNode = childNode;
        }
        return getValues(currentNode);
    }

    private List<Short> getValues(TrieNode node) {
        List<Short> values = new LinkedList<>();
        if (node.isEndOfWord) {
            values.addAll(node.values);
        }
        for (TrieNode child : node.children) {
            values.addAll(getValues(child));
        }
        return values;
    }
}