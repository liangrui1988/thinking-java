package org.rui.collection2.queues;

import java.util.PriorityQueue;

/**
 * 优先级队列 主要和次要的优先级排序 该列表的排序顺序也是通过实现Comparable而进行控制的
 * 
 * @author lenovo
 * 
 */
public class ToDoList extends PriorityQueue<ToDoList.ToDoItem> {

	static class ToDoItem implements Comparable<ToDoItem> {
		private char primary;// 主要的
		private int secondary;// 二
		private String item;

		public ToDoItem(String item, char primary, int secondary) {
			this.primary = primary;
			this.secondary = secondary;
			this.item = item;
		}

		public int compareTo(ToDoItem o) {
			if (primary > o.primary)// 先比较主要的
				return +1;
			if (primary == o.primary)
				if (secondary > o.secondary) // 再比较次要的
					return +1;
				else if (secondary == o.secondary)
					return 0;
			return -1;
		}

		public String toString() {
			return Character.toString(primary) + secondary + ": " + item;
		}
	}

	public void add(String td, char pri, int sec) {
		super.add(new ToDoItem(td, pri, sec));
	}

	public static void main(String[] args) {
		ToDoList to = new ToDoList();
		to.add("Empty Trash", 'C', 4);
		to.add("Feed dog", 'A', 2);
		to.add("Feed bird", 'B', 7);
		to.add("Mow lawn", 'C', 3);
		to.add("Water lawn", 'A', 1);
		to.add("Feed cat", 'B', 1);

		while (!to.isEmpty()) {
			System.out.println(to.remove());
		}
	}
}
/**
 * output: A1: Water lawn A2: Feed dog B1: Feed cat B7: Feed bird C3: Mow lawn
 * C4: Empty Trash
 */
