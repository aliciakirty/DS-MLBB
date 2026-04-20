package ds.mlbb;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreeMLBBTutorial {

    static class ItemNode {
        String name;
        String note;
        List<ItemNode> children;

        ItemNode(String name, String note) {
            this.name = name;
            this.note = note;
            this.children = new ArrayList<>();
        }

        void addChild(ItemNode child) {
            children.add(child);
        }
    }

    // Print tree, recursion
    static void printTree(ItemNode node, int level) {
        if (node == null) return;

        String indent = " ".repeat(level);
        System.out.println(indent + "- " + node.name + " -> " + node.note);

        for (ItemNode child : node.children) {
            printTree(child, level + 1);
        }
    }

    // Print all paths, prints all possible root-to-leaf paths
    static void printAllBuildPaths(ItemNode node, List<String> path) {
        if (node == null) return;

        path.add(node.name);

        if (node.children.isEmpty()) {
            System.out.println(String.join(" -> ", path));
        } else {
            for (ItemNode child : node.children) {
                printAllBuildPaths(child, path);
            }
        }

        path.remove(path.size() - 1);
    }

    // Count nodes
    static int countNodes(ItemNode node) {
        if (node == null) return 0;

        int total = 1;
        for (ItemNode child : node.children) {
            total += countNodes(child);
        }
        return total;
    }

    // Count leaves
    static int countLeaves(ItemNode node) {
        if (node == null) return 0;
        if (node.children.isEmpty()) return 1;

        int total = 0;
        for (ItemNode child : node.children) {
            total += countLeaves(child);
        }
        return total;
    }

    // Height
    static int height(ItemNode node) {
        if (node == null) return 0;
        if (node.children.isEmpty()) return 1;

        int maxChildHeight = 0;
        for (ItemNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, height(child));
        }
        return 1 + maxChildHeight;
    }

    // Find path
    static boolean findPath(ItemNode node, String target, List<String> path) {
        if (node == null) return false;

        path.add(node.name);

        if (node.name.equalsIgnoreCase(target)) {
            return true;
        }

        for (ItemNode child : node.children) {
            if (findPath(child, target, path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    // Task 2: count occurrences
    static int countItemOccurrences(ItemNode node, String target) {
        if (node == null) return 0;

        int count = node.name.equalsIgnoreCase(target) ? 1 : 0;

        for (ItemNode child : node.children) {
            count += countItemOccurrences(child, target);
        }

        return count;
    }

    // Task 3: paths ending with Immortality
    static void printPathsToImmortality(ItemNode node, List<String> path) {
        if (node == null) return;

        path.add(node.name);

        if (node.children.isEmpty() && node.name.equalsIgnoreCase("Immortality")) {
            System.out.println(String.join(" -> ", path));
        }

        for (ItemNode child : node.children) {
            printPathsToImmortality(child, path);
        }

        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {

    // Root
    ItemNode root = new ItemNode("Start Build", "Choose your first major purchase path");

    // First branches
    ItemNode windtalker = new ItemNode("Windtalker", "Fast farming / tempo path");
    ItemNode dhs = new ItemNode("Demon Hunter Sword", "Anti-tank DPS path");

    root.addChild(windtalker);
    root.addChild(dhs);

    // Windtalker branch
    ItemNode berserker = new ItemNode("Berserker's Fury", "Higher burst follow-up");
    ItemNode haas = new ItemNode("Haas' Claws", "Lifesteal sustain option");

    windtalker.addChild(berserker);
    windtalker.addChild(haas);

    ItemNode malefic = new ItemNode("Malefic Roar", "Break tougher enemies later");
    ItemNode won = new ItemNode("Wind of Nature", "Defensive option versus physical danger");

    berserker.addChild(malefic);
    berserker.addChild(won);

    ItemNode bod = new ItemNode("Blade of Despair", "Maximize late damage");
    malefic.addChild(bod);

    ItemNode immortality = new ItemNode("Immortality", "Final safety item");
    won.addChild(immortality);

    // DHS branch
    ItemNode golden = new ItemNode("Golden Staff", "Attack speed synergy");
    ItemNode wt2 = new ItemNode("Windtalker", "Hybrid attack speed path");

    dhs.addChild(golden);
    dhs.addChild(wt2);

    ItemNode corrosion = new ItemNode("Corrosion Scythe", "Slow + DPS combo");
    golden.addChild(corrosion);

    // ===== OUTPUT =====

    System.out.println("=== TREE STRUCTURE ===");
    printTree(root, 0);

    System.out.println("\n=== ALL BUILD PATHS ===");
    printAllBuildPaths(root, new ArrayList<>());

    System.out.println("\n=== TREE STATISTICS ===");
    System.out.println("Total Nodes: " + countNodes(root));
    System.out.println("Leaf Nodes: " + countLeaves(root));
    System.out.println("Tree Height: " + height(root));

        try ( // User input
                Scanner sc = new Scanner(System.in)) {
            System.out.print("\nEnter item to search: ");
            String target = sc.nextLine();
            
            List<String> path = new ArrayList<>();
            if (findPath(root, target, path)) {
                System.out.println("Path: " + String.join(" -> ", path));
            } else {
                System.out.println("Item not found.");
            }
            
            // Task 2
            System.out.println("Occurrences of " + target + ": " +
                    countItemOccurrences(root, target));
            
            // Task 3
            System.out.println("\nPaths ending with Immortality:");
            printPathsToImmortality(root, new ArrayList<>());
        }
}
}