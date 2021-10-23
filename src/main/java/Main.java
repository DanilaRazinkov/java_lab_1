import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        MyCollection<Integer> collection = new MyCollection<>();//Для демонстрации функционала создается коллекция определенного типа
        char mot;
        do{
            System.out.println("Выберите действие: \n1 - Добавить элемент \n2 - Добавить элемент на позицию i" +
                    "\n3 - получить элемент на позиции i\n4 - удалить элемент\ne - выход");
            System.out.println("Коллекция(int): " + collection);
            mot = in.next().charAt(0);
            try{
            switch (mot) {
                case '1':
                    System.out.print("Введите элемент:");
                    if (!collection.add(in.nextInt()))
                        System.out.println("Ошибка элемент не добавлен");
                    break;
                case '2':
                    System.out.print("Введите позицию добавления:");
                    int i = in.nextInt();
                    System.out.print("Введите элемент:");
                    if (!collection.add(i, in.nextInt()))
                        System.out.println("Ошибка элемент не добавлен");
                    break;
                case '3':
                    System.out.print("Введите позицию элемента:");
                    System.out.println("Элемент = " + collection.get(in.nextInt()));
                    break;
                case '4':
                    System.out.print("Введите позицию элемента:");
                    System.out.println("Удаленный элемент = " + collection.remove(in.nextInt()));
                    break;
                }
            }catch (InputMismatchException e) {
                System.out.println("Введенные данные неправильного типа");
                in.next();
            }catch (IndexOutOfBoundsException e) {
                System.out.println("Индекс выходит за границы коллекции");
            }
        } while(mot != 'e');
    }
}
