package people;

import interfaceForProject.Func2;

import java.util.ArrayList;
import java.util.List;


/**
 * Добавила параметр N для того, чтобы решить проблему с наследованием,
 * дерево содержит свой же потомок, чтобы класс был универсальным и была возможность
 * от него наследоваться в дальнейшем.
 *
 * @param <T>
 * @param <N> наследник
 */
public class TreeFamily<T, N extends TreeFamily<T, N>> {

    N parent;
    public ArrayList<N> children;
    public T data;

    public TreeFamily() {

    }

    public TreeFamily(N parent, ArrayList<N> children, T data) {
        this.parent = parent;
        this.children = children;
        this.data = data;
    }


    /**
     * Интерфейс для древа, работает как компоратор
     *
     * @param <T>
     * @param <N>
     */
    public interface TypeAdapter<T, N> {
        N newInstance(); // метод создания новых узлов

        boolean isChildOf(T parentNoteData, T childNoteData); // метод показывающий, что один объект является дочерним другого

        boolean isTopLevelItem(T data); // метод возвращает true, если нет предков
    }

    public static <T, N extends TreeFamily<T, N>> N makeTree(List<T> datas, TypeAdapter<T, N> typeAdapter) {
        N root = typeAdapter.newInstance(); // узел, который будет родительским для всех групп
        root.children = new ArrayList<>();
        for (T top : FuncUtils.filter(datas, typeAdapter::isTopLevelItem)) {
            root.children.add(extractNode(top, root, datas, typeAdapter));
        }
        return root;
    }

    /**
     * @param data        данные в текущем узле
     * @param parent      узел родитель
     * @param datas список с данными
     * @param typeAdapter
     * @param <T>
     * @param <N>
     * @return
     */
    protected static <T, N extends TreeFamily<T, N>> N extractNode(T data, N parent, List<T> datas, TypeAdapter<T, N> typeAdapter) {
        N node = typeAdapter.newInstance();
        node.data = data;
        node.parent = parent;

        List<T> directChildren = FuncUtils.filter(datas, d -> typeAdapter.isChildOf(data, d));
        if (!directChildren.isEmpty()) {
            node.children = new ArrayList<>();
            for (T child : directChildren) {
                node.children.add(extractNode(child, node, datas, typeAdapter));
            }
        }
        return node;
    }

    /**
     *
     * @param reducer (свёртка) вычисление значения определенного типа по всем элементам дерева,
     *                с сохраниением промежуточного результата
     * @param initial этот параметр будет хранить промежуточный результат
     * @return
     *  Func2<N,R,R> имеет два параметра N,R и возвращает R
     */
    public <R> R reduce(Func2<N,R,R> reducer, R initial){
        R value = reducer.apply((N)this,initial);
        if(children != null){
            for(N node:children){
                value = node.reduce(reducer,value);
            }
        }
        return value;

    }



    @Override
    public String toString() {
        return parent + "-->" + children;
    }
}

