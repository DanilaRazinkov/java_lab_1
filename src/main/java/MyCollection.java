public class MyCollection<T>{

    /**
     * Размер коллекции
     */
    private int size = 0;

    /**
     * Первый элемент
     */
    private Node<T> first = null;

    /**
     * Последний элемент
     */
    private Node<T> last = null;

    public MyCollection() {
    }

    /**
     * Добавляяет элемент в коллекцию
     * @param el элемен, который необходимо добавить в коллекцию
     * @return добавлен элемент или произошла ошибка
     */
    public boolean add(T el) {
        addLast(el);
        return true;
    }

    /**
     * Добавляет новый элемент на определенную позицию
     * @param index индекс, на которую добавляется новый элемент
     * @param el добавляемый элемент
     * @return true если элемент добавлен. false - индекс не подходит
     */
    public boolean add(int index, T el) {
        if(checkPosition(index)) {
            if(index == size)
                addLast(el);
            else
                addBefore(el, getNode(index));
            return true;
        }
        return false;
    }

    /**
     * Возвращает ссылку на элемент на позиции index
     * @param index позиция элемента
     * @return элемент на позиции index
     */
    public T get(int index) {
        checkIndex(index);
        return getNode(index).elem;
    }

    /**
     * Удаляет элемент на позиции index и возвращает его значение
     * @param index - индекс удаляемого элемента
     * @return значение удаляемого элемета
     */
    public T remove(int index) {
        checkIndex(index);
        return removeEl(getNode(index));
    }


    /**
     * Добавляет элемент в конец коллекции
     * @param el элемент, который необходимо добавить в конец коллекции
     */
    private void addLast(T el) {
        Node<T> oldLast = last;
        Node<T> newNode = new Node<>(el, last, null);
        last = newNode;
        if(oldLast == null)
            first = newNode;
        else
            oldLast.next = newNode;
        size++;
    }

    /**
     * Добавляет новый элемент перед необходимым
     * @param el новый элемент
     * @param node нода, перед которой необходимо добавить новый элемент
     */
    private void addBefore(T el, Node<T> node) {
        Node<T> prev = node.prev;
        Node<T> newNode = new Node<>(el, prev, node);
        node.prev = newNode;
        if(prev == null)
            first = newNode;
        else
            prev.next = newNode;
        size++;
    }

    /**
     * Находит и возвращает элемент, находящийся на необходимой позиции
     * @param index индекс искомого элемента
     * @return ссылка на искомую ноду
     */
    private Node<T> getNode(int index) {
        Node<T> temp;
        if(index < (size >> 1)) {
            temp = first;
            for (int i = 0; i < index; i++)
                temp = temp.next;
        } else {
            temp = last;
            for(int i = size - 1; i > index; i--)
                temp = temp.prev;
        }
        return temp;
    }

    /**
     * Удаляет передаваемый элемент из коллекции
     * @param del удаляемый элемент
     * @return значение удаляемого элемента
     */
    private T removeEl(Node<T> del) {
        T el = del.elem;
        Node<T> prev = del.prev;
        Node<T> next = del.next;
        if(prev == null)
            first = next;
        else {
            prev.next = next;
            del.next = null;
        }

        if(next == null)
            last = prev;
        else {
            next.prev = prev;
            del.prev = null;
        }

        del.elem = null;
        size--;
        return el;
    }

    /**
     * Создает строку из элементов коллекции
     * @return строку, соответсвующую элементам коллекции
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(size >= 1) {
            Node<T> now = first;
            stringBuilder.append('[').append(now.elem.toString());
            now = now.next;
            while (now != null) {
                stringBuilder.append(", ").append(now.elem.toString());
                now = now.next;
            }
            stringBuilder.append(']');
        } else
            stringBuilder.append("[]");
        return stringBuilder.toString();
    }


    /**
     * Проверяет, что индекс подходит для данной коллекции, не выводит за границы
     * @param index индекс, котороый необходимо проверить на корректность
     * @throws IndexOutOfBoundsException - если индекс выводит за границы
     */
    private void checkIndex(int index) {
        if(!(index >= 0 && index < size))
            throw new IndexOutOfBoundsException("Index: " + index + "; Size: ");
    }

    /**
     * Проверяет, что индекс подходит для добавления элемента
     * @param index индекс, котороый необходимо проверить на корректность
     * @return true если подходит. false - отрицательный или выходит за границы
     */
    private boolean checkPosition(int index) {
        return index >= 0 && index <= size;
    }

    private static class Node<T> {
        private T elem;
        private Node<T> next;
        private Node<T> prev;

        public Node(T elem, Node<T> prev, Node<T> next) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }
    }
}
