import java.util.Scanner;

public class Menu {
    private DBUtil dbu;
    private static Scanner in;

    Menu() {
        in = new Scanner(System.in);
        dbu = new DBUtil();
        this.choose();
    }

    public void choose() {
        int index = 0;

        while (true) {
            System.out.println("Введите номер операции, которую хотите выполнить: ");
            System.out.println(
                    "1 - создание БД\n" +
                            "2 - добавление в таблицу\n" +
                            "3 - просмотр таблицы\n" +
                            "4 - просмотр таблицы по заданному условию\n" +
                            "5 - удаление из таблицы\n" +
                            "6 - удаление таблицы\n" +
                            "7 - получить фамилию автора книги\n" +
                            "8 - получить название книги\n" +
                            "0 - выход\n");
            index = in.nextInt();

            switch (index) {
                case 1: {
                    dbu.createTable();
                    break;
                }
                case 2: {
                    dbu.addToDataBase();
                    break;
                }
                case 3: {
                    dbu.selectAllDataBase();
                    break;
                }
                case 4: {
                    dbu.selectDataBaseByCondition();
                    break;
                }
                case 5: {
                    dbu.deleteFromDataBase();
                    break;
                }
                case 6: {
                    dbu.deleteTableFromDataBase();
                    break;
                }
                case 7: {
                    System.out.println(dbu.getAuthorsNameFromTable());
                    break;
                }
                case 8: {
                    System.out.println(dbu.getBooksNameFromTable());
                    break;
                }
                default:
                    break;
            }
            if (index > 8 || index < 1) break;
        }
        dbu.endWorkWithDataBase();
    }
}