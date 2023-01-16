package app;


import interfaceForProject.FormatChange;
import people.DataOfHuman;
import people.TreeFamily;

import java.io.IOException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {

        ArrayList<DataOfHuman> items = new ArrayList<>();

        items.add(new DataOfHuman(1, "Анна", 0));
        items.add(new DataOfHuman(2, "Оксана", 1));
        items.add(new DataOfHuman(3, "Эльвира", 2));
        items.add(new DataOfHuman(4, "Диана", 2));
        items.add(new DataOfHuman(5, "Иосиф", 1));
        items.add(new DataOfHuman(6, "Владимир", 5));
        items.add(new DataOfHuman(7, "Роман", 5));
        items.add(new DataOfHuman(8, "Василий", 1));
        items.add(new DataOfHuman(9, "Надежда", 8));
        items.add(new DataOfHuman(10, "Наталья", 8));
        items.add(new DataOfHuman(11, "Петр", 1));
        items.add(new DataOfHuman(12, "Назар", 11));
        items.add(new DataOfHuman(13, "Зенек", 11));
        items.add(new DataOfHuman(14, "Мария", 1));
        items.add(new DataOfHuman(15, "Оля", 14));
        items.add(new DataOfHuman(16, "Андрей", 14));
        items.add(new DataOfHuman(17, "Анна", 1));
        items.add(new DataOfHuman(18, "Мария", 17));
        items.add(new DataOfHuman(19, "Оксана", 17));
        items.add(new DataOfHuman(20, "Михаил", 1));
        items.add(new DataOfHuman(21, "Степан", 20));
        items.add(new DataOfHuman(22, "Татьяна", 20));

        DataNode tree = DataNode.makeTree(items, new TreeFamily.TypeAdapter<DataOfHuman, DataNode>() {
            @Override
            public DataNode newInstance() {
                return new DataNode();
            }

            @Override
            public boolean isChildOf(DataOfHuman parentNoteData, DataOfHuman childNoteData) {
                return parentNoteData.id == childNoteData.parentId;
            }

            @Override
            public boolean isTopLevelItem(DataOfHuman data) {
                return data.parentId == 0;
            }
        });
        /**
         * Метод вывода всех участников на консоль
        */
        String names = tree.reduce((node,names1) -> {
            if(node.data!=null){
                if(names1.length() > 0 ){
                    names1=names1 + ", ";
                }
                names1 = names1 +"id№" + node.data.id +" "+ node.data.name;
            }
            return names1;
        },"");
        System.out.println("Список всех: " + names);
        /**
        * Вызов метода для перевода в текстовый формат
        */
        FormatChange file = new FormatChange();
        file.getFormatTxt(Collections.singletonList(items),"output.txt");

        /**
         * Использование метода reduce для поиска связи по id, первый родитель, остальные дети
         */
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер id для поиска связи: ");
        int numId = in.nextInt();

        String str = tree.reduce((node,names1)->{
            if(node.data!=null){
                if(node.data.id==numId){
                    names1 = names1 + node.data.name + " - родитель: ";
                }
                if(node.data.parentId==numId){
                    names1 = names1 + node.data.name + " ребенок, ";
                }
            }
            return names1;
        },"");
        System.out.println("У id№ "+ numId +" " + str);

    }

    static class DataNode extends TreeFamily<DataOfHuman, DataNode> {
        @Override
        public String toString() {
            return data.name;
        }

    }

}

