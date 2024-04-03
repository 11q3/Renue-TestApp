package org.example.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    private static class TrieNode {
        int value;
        boolean isEndOfWord;
        List<TrieNode> children;
        int codePoint;

        public TrieNode() {
            children = new ArrayList<>();
            value = -1;
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

    public void insert(String key, int value) {
        TrieNode currentNode = root;
        for (int i = 0; i < key.length(); ++i) {
            int codePoint = Character.codePointAt(key, i);
            TrieNode childNode = currentNode.getChild(codePoint);
            if (childNode == null) {
                childNode = new TrieNode();
                currentNode.addChild(codePoint, childNode);
            }
            currentNode = childNode;
        }
        currentNode.value = value;
        currentNode.isEndOfWord = true;
        currentNode.codePoint = -1;
    }

    public List<Integer> search(String key) {
        TrieNode currentNode = root;
        for (int i = 0; i < key.length(); ++i) {
            int codePoint = Character.codePointAt(key, i);
            TrieNode childNode = currentNode.getChild(codePoint);

            if (childNode == null) {
                return getValues(currentNode);
            }
            currentNode = childNode;
        }
        return getValues(currentNode);
    }

    private List<Integer> getValues(TrieNode node) {
        List<Integer> values = new ArrayList<>();
        if (node.isEndOfWord) {
            values.add(node.value);
        }
        for (TrieNode child : node.children) {
            values.addAll(getValues(child));
        }
        return values;
    }
}