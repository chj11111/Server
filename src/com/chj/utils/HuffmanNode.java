package com.chj.utils;

import java.util.PriorityQueue;
import java.util.HashMap;

public class HuffmanNode implements Comparable<HuffmanNode> {
    char data;
    int frequency;
    HuffmanNode left, right;

    static PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>();

    static HashMap<Character, String> huffmanCodes = new HashMap<>();

    public HuffmanNode(char data, int frequency) {
        this.data = data;
        this.frequency = frequency;
    }

    public HuffmanNode(){
        HashMap<Character, Integer> frequencyMap = new HashMap<>();

        char[] symbols = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
                'u', 'v', 'w', 'x', 'y', 'z', ' ','1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '\\'};
        int[] freq = {12, 3, 12, 45, 5, 12, 23, 213, 44, 55, 14, 78, 90, 11, 122, 89, 22, 12, 11, 44, 57, 90, 99, 78, 76, 101, 108,
                34, 37, 78, 8, 2, 4, 8, 9, 10, 123, 29};

        for (int i = 0; i < symbols.length; i++) frequencyMap.put(symbols[i], freq[i]);


        for (char c : frequencyMap.keySet()) {
            priorityQueue.add(new HuffmanNode(c, frequencyMap.get(c)));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode mergedNode = new HuffmanNode('\0', left.frequency + right.frequency);
            mergedNode.left = left;
            mergedNode.right = right;

            priorityQueue.add(mergedNode);
        }

        generateCodes(priorityQueue.peek(), "", huffmanCodes);
    };

    @Override
    public int compareTo(HuffmanNode o) {
        return this.frequency - o.frequency;
    }


    public String EncodeTxT(String txt) {

        String encode_txt = "";

        for (int i = 0; i < txt.length(); i++) {
            encode_txt += huffmanCodes.get(txt.charAt(i));
        }

        return encode_txt;
    }

    public String DecodeTxT(String txt) {

        String decodedText = "";

        HuffmanNode root = priorityQueue.peek();

        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == '0') {
                root = root.left;
            } else root = root.right;

            if (root.data != '\0') {
                decodedText += root.data;
                root = priorityQueue.peek();
            }
        }

        return decodedText;
    }

        public void generateCodes(HuffmanNode root, String code, HashMap<Character, String> huffmanCodes) {
        if (root != null) {
            if (root.data != '\0') {
                huffmanCodes.put(root.data, code);
            }

            generateCodes(root.left, code + "0", huffmanCodes);
            generateCodes(root.right, code + "1", huffmanCodes);
        }

    }
}