package dev.alnat.practice.tasks.lazyking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Реализация будет через дерево
 *
 * При добавлении нового узла мы либо добавим его к Королю (к корню), либо найдем уже где-то и тогда просто добавим всех его вассалов.
 *  И всех вассалов этого вассала аналогично: если нода где-то есть, то перенесем ее со всеми потоками к этому новому вассалу
 *  А если не найдена -- просто добавим как 'под вассала'.
 *
 * Created by @author AlNat on 30.01.2022.
 * Licensed by Apache License, Version 2.0
 */
public class LazyKing {
    private static final List<String> pollResults = List.of(
            "служанка Аня",
            "управляющий Семен Семеныч: крестьянин Федя, доярка Нюра",
            "дворянин Кузькин: управляющий Семен Семеныч, жена Кузькина, экономка Лидия Федоровна",
            "экономка Лидия Федоровна: дворник Гена, служанка Аня",
            "доярка Нюра",
            "кот Василий: человеческая особь Катя",
            "дворник Гена: посыльный Тошка",
            "киллер Гена",
            "зажиточный холоп: крестьянка Таня",
            "секретарь короля: зажиточный холоп, шпион Т",
            "шпион Т: кучер Д",
            "посыльный Тошка: кот Василий",
            "аристократ Клаус",
            "просветленный Антон"
    );

    public static void main(String... args) {
        UnluckyVassal unluckyVassal = new UnluckyVassal();

        unluckyVassal.printReportForKing(pollResults);
    }

}

class UnluckyVassal {

    public void printReportForKing(List<String> pollResults) {
        final MagicTools.MiracleTree tree = MagicTools.getMagicTree("король");

        for (String s : pollResults) {
            var arr = s.split(":");

            final String vassalName = arr[0].trim();
            List<String> vassalsOfVassal = Collections.emptyList();

            if (arr.length == 2) {
                vassalsOfVassal = Arrays.stream(arr[1].split(",")).map(String::strip).collect(Collectors.toList());
            }

            tree.addNewNode(vassalName, vassalsOfVassal);
        }

        tree.printTree();
    }

}


class MagicTools {

    private MagicTools() {
    }

    public static MiracleTree getMagicTree(String rootName) {
        return new MiracleTree(rootName);
    }


    /**
     * Простая реализация дерева, рекурсивные обходы
     * Может быть заменена на любую стандартную библиотеку для работы с деревьями
     */
    public static class MiracleTree {

        private final Node root;

        private record Node(String name, List<Node> children) {

            public void appendToChild(Collection<Node> nodeCollection) {
                children.addAll(nodeCollection);
            }

            public void appendToChild(Node node) {
                children.add(node);
            }
        }


        public MiracleTree(String rootNodeName) {
            this.root = new Node(rootNodeName, new LinkedList<>());
        }

        /**
         * Добавление новой ноды
         */
        public void addNewNode(String nodeName, List<String> subNodeName) {
            // Сперва пытаемся найти\создать всех потомков
            //  каждый найденый потомок переходит к новой ноде
            // В рамках задачи ок т.к. иначе он был бы прицеплен к Королю
            var subNodeList = subNodeName.stream()
                    .map(this::fetchOrCreateNode)
                    .collect(Collectors.toList());

            // А теперь создаем корневую ноду
            Node newNode;
            var nodeOpt = findNode(nodeName);
            if (nodeOpt.isEmpty()) { // Если такой ноды раньше не было -- добавляем его к корню
                newNode = new Node(nodeName, subNodeList);
                root.appendToChild(newNode);
            } else { // Иначе просто добавляем всех новых потомков
                nodeOpt.get().appendToChild(subNodeList);
            }
        }

        private Node fetchOrCreateNode(String nodeName) {
            var subNodeOpt = extractNode(nodeName);
            return subNodeOpt.orElseGet(() -> new Node(nodeName, new LinkedList<>()));
        }


        private Optional<Node> extractNode(String vassalName) {
            return extractNodeFromSubTree(root, vassalName);
        }

        private Optional<Node> extractNodeFromSubTree(Node root, String vassalName) {
            for (Node sub : root.children()) {
                if (sub.name().equalsIgnoreCase(vassalName)) {
                    root.children().remove(sub);
                    return Optional.of(sub);
                }
                extractNodeFromSubTree(sub, vassalName);
            }

            return Optional.empty();
        }


        private Optional<Node> findNode(String nodeName) {
            return findNodeFromSubTree(root, nodeName);
        }

        private Optional<Node> findNodeFromSubTree(Node root, String nodeName) {
            if (root.name().equalsIgnoreCase(nodeName)) {
                return Optional.of(root);
            }

            for (Node sub : root.children()) {
                var v = findNodeFromSubTree(sub, nodeName);
                if (v.isPresent()) {
                    return v;
                }
            }

            return Optional.empty();
        }


        public void printTree() {
            printFromSubTree(root, 0, 3);
        }

        public void printTree(int step) {
            printFromSubTree(root, 0, step);
        }

        private void printFromSubTree(Node root, int spaceCount, int step) {
            root.children().sort(Comparator.comparing(Node::name)); // Сортируем по имени
            System.out.println(" ".repeat(spaceCount) + root.name());
            for (Node sub : root.children()) {
                printFromSubTree(sub, spaceCount + step, step);
            }
        }
    }

}
