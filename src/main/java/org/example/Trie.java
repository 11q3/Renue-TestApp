package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word, int airportId) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TrieNode());
        }
        node.airportId = airportId;
    }

    public List<Integer> search(String query) {
        TrieNode node = root;
        for (char c : query.toCharArray()) {
            TrieNode child = node.children.get(c);
            if (child == null) {
                return new ArrayList<>();
            }
            node = child;
        }
        return getSortedAirports(node);
    }

    private List<Integer> getSortedAirports(TrieNode node) {
        List<Integer> airports = new ArrayList<>();
        if (node.airportId != 0) {
            airports.add(node.airportId);
        }
        for (TrieNode child : node.children.values()) {
            airports.addAll(getSortedAirports(child));
        }
        return airports;
    }

    private static class TrieNode {
        int airportId;
        Map<Character, TrieNode> children = new HashMap<>();
    }
}