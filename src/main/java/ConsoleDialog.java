import java.util.Scanner;

public class ConsoleDialog {
    private Scanner in;

    ConsoleDialog() {
        in = new Scanner(System.in);
    }

    public String getNameTable() {
        System.out.println("Введите название таблицы: ");
        String NameDB = in.nextLine();
        return NameDB;
    }

    public String getNameBook() {
        System.out.println("Введите название книги, если хотите закончить - не вводите ничего: ");
        String BooksName = in.nextLine();
        return BooksName;
    }

    public String getAuthor() {
        System.out.println("Введите автора, если хотите закончить - не вводите ничего: ");
        String BooksAuthor = in.nextLine();
        return BooksAuthor;
    }

    public int getIndexBook() {
        System.out.println("Введите индекс книги, если отрицательный - выход: ");
        int index = in.nextInt();
        return index;
    }

}
