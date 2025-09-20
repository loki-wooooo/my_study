class UserData {
    UserData(String name) {
        this.name = name;
    }

    String name;
    UserData prev;
    UserData next;
}

//Node(UserData)를 관리함
class MyList {
    protected int counter = 0;
    protected UserData head = new UserData("DummyHead");
    protected UserData tail = new UserData("DummyTail");

    MyList() {
        head.next = tail;
        tail.prev = head;
    }

    public int size() {
        return counter;
    }

    public boolean appendNode(String name) {
        UserData newUser = new UserData(name);

        // 임계영역 start
        newUser.prev = tail.prev;
        newUser.next = tail;
        tail.prev.next = newUser;
        tail.prev = newUser;
        ++counter;
        // 임계영역 end

        return true;
    }

    public boolean isEmpty() {
        if (head.next == tail)
            return true;

        return false;
    }

    public void printAll() {
        System.out.println("-----------------------");
        System.out.println("Counter: " + counter);
        UserData tmp = head.next;
        while (tmp != tail) {
            System.out.println(tmp.name);
            tmp = tmp.next;
        }
        System.out.println("-----------------------");
    }

    public UserData removeAtHead() {
        if (isEmpty())
            return null;

        // 임계영역 start
        UserData node = head.next;
        node.next.prev = head;
        head.next = node.next;
        --counter;
        // 임계영역 end

        return node;
    }
}

public class Main {
    public static void main(String[] args) {
        MyList db = new MyList();

        db.appendNode("tester01");
        db.appendNode("tester02");
        db.appendNode("tester03");
        db.printAll();

        db.removeAtHead();
        db.printAll();
        db.removeAtHead();
        db.printAll();
        db.removeAtHead();
        db.printAll();
    }
}
