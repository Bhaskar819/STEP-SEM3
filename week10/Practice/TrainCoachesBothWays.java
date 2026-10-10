package Week10.Practice;

public class TrainCoachesBothWays {

    static class Node {
        String data;
        Node prev;
        Node next;

        Node(String data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;

        void addLast(String name) {
            Node newNode = new Node(name);

            if (head == null) {
                head = tail = newNode;
                return;
            }

            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        void remove(String name) {
            Node current = head;

            while (current != null) {
                if (current.data.equals(name)) {

                    if (current.prev != null) {
                        current.prev.next = current.next;
                    } else {
                        head = current.next;
                    }

                    if (current.next != null) {
                        current.next.prev = current.prev;
                    } else {
                        tail = current.prev;
                    }

                    return;
                }

                current = current.next;
            }
        }

        void printForward() {
            System.out.print("Forward: ");

            Node current = head;

            while (current != null) {
                System.out.print(current.data);

                if (current.next != null) {
                    System.out.print(" <-> ");
                }

                current = current.next;
            }

            System.out.println();
        }

        void printBackward() {
            System.out.print("Backward: ");

            Node current = tail;

            while (current != null) {
                System.out.print(current.data);

                if (current.prev != null) {
                    System.out.print(" <-> ");
                }

                current = current.prev;
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList train = new DoublyLinkedList();

        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        train.printForward();
        train.printBackward();
    }
}