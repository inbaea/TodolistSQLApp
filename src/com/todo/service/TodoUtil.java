package com.todo.service;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.text.SimpleDateFormat;

import com.sun.net.httpserver.Filter.Chain;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {

	public static void createItem(TodoList l) {

		String category, title, memo, due_date, colleague;
		int priority;
		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸��߰�]\n" + "���� > ");
		title = sc.next();
		sc.nextLine();
		if (TodoList.isDuplicate(l, title)) {
			System.out.printf("�ߺ��� ������ ���Ұ��Դϴ�.");
			return;
		}
		System.out.print("ī�װ� > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("���� > ");
		memo = sc.nextLine().trim();
		System.out.print("���� > ");
		colleague = sc.nextLine().trim();
		System.out.print("�߿䵵(1 ~ 5) > ");
		priority = sc.nextInt();
		sc.nextLine();
		System.out.print("�������� > ");
		due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(category, title, memo, due_date, 0, colleague, priority);
		if (l.addItem(t) > 0) {
			System.out.println("�߰��Ǿ����ϴ�.");
		}
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ���� �Է����ּ���. > ");
		String index = sc.nextLine().trim();
		ArrayList<Integer> num_list = new ArrayList<Integer>();
		String[] array = index.split(" ");
		for (String s : array) {
			if (s.equals(" "))
				continue;
			else
				num_list.add(Integer.parseInt(s));
		}

		ArrayList<Integer> id_list = new ArrayList<Integer>();
		for (TodoItem item : l.getList()) {
			int a = item.getId();
			id_list.add(a);
		}

		for (int b : num_list) {
			if (!id_list.contains(b)) {
				System.out.println("\n���� ��ȣ�� �Է��ϼ̽��ϴ�!");
				return;
			}
		}

		for (TodoItem item : l.getList()) {
			for (int a : num_list) {
				if (a == item.getId())
					System.out.println(item.toString());
			}
		}
		while (true) {
			System.out.print("\n���� �����Ͻðڽ��ϱ�?(y or n) > ");
			String yorn = sc.nextLine().trim();
			if (yorn.equals("y")) {
				if (l.deleteItem(num_list) > 0)
					System.out.println("\n�����Ǿ����ϴ�.");
				break;
			} else if (yorn.equals("n")) {
				System.out.println("\n��ҵǾ����ϴ�.");
				break;
			} else
				System.out.println("\n�ٽ� �Է��ϼ���.");
		}
	}

	public static void updateItem(TodoList l) {

		String new_title, new_memo, new_category, new_due_date, new_colleague;
		int new_priority;
		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ�� �Է����ּ��� > ");
		int index = sc.nextInt();

		if (index < 0 || index > l.getCount()) {
			System.out.println("���� ��ȣ�Դϴ�!");
			return;
		}

		for (TodoItem item : l.getList()) {
			if (item.getId() == index)
				System.out.println(item.toString());
		}

		System.out.print("�� ���� > ");
		new_title = sc.next().trim();
		System.out.print("�� ī�װ� > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("�� ���� > ");
		new_memo = sc.nextLine().trim();
		System.out.print("�� ���� > ");
		new_colleague = sc.nextLine().trim();
		System.out.print("�� �׸��� �߿䵵 > ");
		new_priority = sc.nextInt();
		sc.nextLine();
		System.out.print("�� �������� > ");
		new_due_date = sc.nextLine().trim();
		TodoItem t = new TodoItem(new_category, new_title, new_memo, new_due_date, 0, new_colleague, new_priority);
		t.setId(index);
		if (l.updateItem(t) > 0)
			System.out.println("�׸��� �����Ǿ����ϴ�.");
	}

	public static void listAll(TodoList l) {
		System.out.printf("\n" + "[��ü ���, �� %d��]" + "\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void listCate(TodoList l) {
		int count = 0;

		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ��ֽ��ϴ�.\n", count);
	}

	public static void FindItem(TodoList l, String item_name) {

		int count = 0;
		for (TodoItem item : l.getList(item_name)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.", count);
	}

	public static void FindCate(TodoList l, String item_name) {

		int count = 0;
		for (TodoItem item : l.getListCategory(item_name)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}

	public static void listAll(TodoList l, String order, int ordering) {
		System.out.printf("[��ü���, �� %d��]\n", l.getCount());
		for (TodoItem item : l.getOrderList(order, ordering)) {
			System.out.println(item.toString());
		}

	}

	public static void CompleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);

		System.out.print("\n�Ϸ�üũ �� �׸���� �Է����ּ���. >");
		String comp = sc.nextLine().trim();

		ArrayList<Integer> comp_list = new ArrayList<Integer>();
		String[] array = comp.split(" ");
		for (String s : array) {
			if (s.equals(" "))
				continue;
			else
				comp_list.add(Integer.parseInt(s));
		}

		ArrayList<Integer> id_list = new ArrayList<Integer>();
		for (TodoItem item : l.getList()) {
			int a = item.getId();
			id_list.add(a);
		}

		for (int b : comp_list) {
			if (!id_list.contains(b)) {
				System.out.println("\n���� ��ȣ�� �Է��ϼ̽��ϴ�!");
				return;
			}
		}

		if (l.completeItem(comp_list) > 0) {
			System.out.println("�Ϸ� üũ�Ͽ����ϴ�.");
		} else {
			System.out.println("�̹� �Ϸ�� �׸��Դϴ�.");
			return;
		}
	}

	public static void ListComp(TodoList l) {
		int count = 0;
		for (TodoItem item : l.getList()) {
			if (item.getIs_completed() == 1) {
				System.out.println(item.toString());
				count++;
			}
		}
		System.out.printf("\n�� %d���� �׸��� �Ϸ�Ǿ����ϴ�.", count);
	}

	public static void DueTime(TodoList l, int time) {
		String item_time = null;
		for (TodoItem item : l.getList()) {
			if (item.getId() == time) {
				item_time = item.getDue_date();
				System.out.println(item.toString());
			}
		}

		ArrayList<Integer> id_list = new ArrayList<Integer>();
		for (TodoItem item : l.getList()) {
			int a = item.getId();
			id_list.add(a);
		}
		if (!id_list.contains(time)) {
			System.out.println("\n���� ��ȣ�� �Է��ϼ̽��ϴ�!");
			return;
		}

		SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd/kk/mm");
		String now_date = f.format(new Date());
		String[] items = now_date.split("/");
		String[] due_items = item_time.split("/");

		int[] now_times = new int[5];
		int[] due_times = new int[5];

		for (int i = 0; i < 5; i++) {
			now_times[i] = Integer.parseInt(items[i]);
		}
		for (int i = 0; i < 5; i++) {
			if (i == 3 || i == 4) {
				due_times[i] = 0;
				continue;
			}
			due_times[i] = Integer.parseInt(due_items[i]);
		}
		due_times[2]++;

		if (now_times.length == due_times.length) {
			for (int a = now_times.length; a > 0; a--) {
				due_times[a - 1] = due_times[a - 1] - now_times[a - 1];
			}
		}

		if (due_times[4] < 0) {
			due_times[3]--;
			due_times[4] = due_times[4] + 60;
		}
		if (due_times[3] < 0) {
			due_times[2]--;
			due_times[3] = due_times[3] + 24;
		}
		if (due_times[2] < 0) {
			due_times[1]--;
			due_times[2] = due_times[2] + 31;
		}
		if (due_times[1] < 0) {
			due_times[0]--;
			due_times[1] = due_times[1] + 12;
		}
		if (due_times[0] < 0) {
			System.out.println("\n�̹� ������ ���� �׸��Դϴ�!");
			return;
		}
		if (due_times[0] >= 0) {
			System.out.printf("\n[�ش� �׸��� ���������� %d�� %d�� %d�� %d�ð� %d�� ���ҽ��ϴ�.]\n", due_times[0], due_times[1],
					due_times[2], due_times[3], due_times[4]);
		}
		return;
	}
}
