/**
 * Some examples of tests. You'll want to write more tests.
 */
public class ExampleTests {

    private static interface Animal {
        public String speak();
    }
    private static class Dog implements Animal {
        @Override
        public String speak() {
            return "woof";
        }
    }

    private static class Cat implements Animal {
        @Override
        public String speak() {
            return "meow";
        }
    }

    public static void main(String[] args) {

        // Hash table
        HashTable<Integer, Animal> animals = HashTableFactory.create();
        animals.put(1, new Dog());
        animals.put(1032, new Cat());
        animals.put(1, new Cat());
        System.out.println("Expecting 'woof': " + animals.get(1).speak());
        System.out.println("Expecting 'meow': " + animals.get(1032).speak());
        
        // Graph building

        Graph graph = GraphFactory.create(6);
        Node a = NodeFactory.create("a");
        Node b = NodeFactory.create("b");
        Node c = NodeFactory.create("c");
        Node d = NodeFactory.create("d");
        Node e = NodeFactory.create("e");
        Node f = NodeFactory.create("f");
        Node g = NodeFactory.create("g");
        Node h = NodeFactory.create("h");
        Node i = NodeFactory.create("i");
        Node j = NodeFactory.create("j");
        graph.addNodes(a, b, c, d, e, f);
        graph.addEdge(a, b);
        graph.addEdge(b, c);
        graph.addEdge(c, d);
        graph.addEdge(b, d);
        graph.addEdge(a,c);
        graph.addEdge(d,e);
        graph.addEdge(b, e);
        graph.addEdge(c,e);
        graph.addEdge(e, f);
              
        System.out.println("Expecting unique ids from nodes c, b, a:");
        System.out.println("ID for c: " + graph.lookupNode("c").getId());
        System.out.println("ID for b: " + graph.lookupNode("b").getId());
        System.out.println("ID for a: " + graph.lookupNode("a").getId());

        // Graph analysis
        System.out.println("Expecting an acyclic graph with sorted output: a, b, c");
        graph.analyze();
    }

}