A. Observation Questions
Answer the following questions in your lab report:

1. What is the root node in this program?
= Start Build, karena Start Build merupakan awal dari seluruh node yang ada.
2. Which nodes are leaf nodes?
= Blade of Despair, Immortality, Haas' Claws, Corrosion Scythe, dan Windtalker (dari cabang Demon Hunter Sword).
3. Why is children stored as a List<ItemNode> instead of a single variable?
= dikarenakan banyaknya cabang yang ada di dalam program ini sehingga strukturnya bisa lebih fleksibel lagi dan dapat menampung banyak jalur yang memungkinkan.
4. What is the difference between a linear structure and a tree structure in this example?
= linear structure pada umumnya hanya memiliki 1 jalur tetap berbeda dengan tree structure yang satu node dapat memiliki beberapa jalur berbeda/ edge yang lebih dari 1.
5. How does recursion help when working with trees?
= rekursi mengubah tugas kompleks yang melibatkan hierarki bertingkat menjadi serangkaian langkah kecil yang serupa dan lebih mudah dikelola/ menyelesaikan masalah dengan pola yang sama berulang-ulang pada bagian yang lebih kecil.
6. What path is printed when searching for Corrosion Scythe?
= Start Build → Demon Hunter Sword → Golden Staff → Corrosion Scythe

B. Take Home Tasks
Complete the following tasks after the main tutorial:

TASK 1 Add a new branch from Start Build using a different first item.
= saya menambahkan Blade Armor path.
TASK 2 Create a method named countItemOccurrences() to count how many times a specific item appears in the tree.
= if (node == null) return 0;

        int count = node.name.equalsIgnoreCase(target) ? 1 : 0;

        for (ItemNode child : node.children) {
            count += countItemOccurrences(child, target);
        }

        return count;
TASK 3 Create a method that prints only the paths ending with Immortality.
= static void printPathsToImmortality(ItemNode node, List<String> path) {
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
TASK 4 Modify the program so the user can input the target item name using Scanner.
= Scanner sc = new Scanner(System.in)) {
            System.out.print("\nEnter item to search: ");
            String target = sc.nextLine();
TASK 5 Add one more level to the tree and observe whether the height changes.
=   ItemNode divine = new ItemNode("Divine Glaive", "Extra late game magic penetration");
    corrosion.addChild(divine);
jika saya menambahkan "ItemNode divine" seperti diatas maka outputnya nntinya akan berubah menjadi (Tree Height: 6)
karena ukuran tree sebelumnya dari 5: Start Build → Windtalker → Berserker's Fury → Malefic Roar → Blade of Despair
jika ditambahkan node baru akan berubah menjadi 6: Start Build → Demon Hunter Sword → Golden Staff → Corrosion Scythe → Divine Glaive

C. Reflection
Write a short reflection of 1–2 paragraphs about the following:

Why is a tree more suitable than an array for representing item purchase possibilities?
What part of this lab helped you understand recursion better?
What challenges did you face when tracing the tree structure?
= struktur tree sendiri lebih cocok digunakan dibandingkan array dikarenakan banyaknya percabangan dalam pembelian item yang tersedia. dalam satu build sendiri memiliki beberapa cara untuk mencapainya. bagian yang sangat membantu bagi saya adalah mencetak semua jalur dari root ke leaf karena kita dapat memahami lebih bagaimana fungsi tersebut berfungsi dalam menelusuri setiap cabangnya. challenge yang saya hadapi adalah dalam mengelola pathnya karena saya harus betul-betul memahami bagaimana fungsi rekursi berfungsi kemudian baru bisa di implementasikan.
