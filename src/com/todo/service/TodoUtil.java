package com.todo.service;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {

	public static void createItem(TodoList l) {

		String category, title, memo, due_date;
		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸��߰�]\n" + "���� > ");
		title = sc.next();
		if (TodoList.isDuplicate(l, title)) {
		 System.out.printf("�ߺ��� ������ ���Ұ��Դϴ�.");
		 return;
		}
		System.out.print("ī�װ� > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("���� > ");
		memo = sc.nextLine().trim();
		System.out.print("�������� > ");
		due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(category, title, memo, due_date);
		if (l.addItem(t) > 0) {
			System.out.println("�߰��Ǿ����ϴ�.");
		}
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
	
		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ�� �Է��Ͻʽÿ� > ");
		int index = sc.nextInt();
		if(index < 0 || index > l.getCount()) {
			System.out.println("���� ��ȣ�Դϴ�!");
			return;
		}
		if (l.deleteItem(index) > 0)
			System.out.println("�����Ǿ����ϴ�.");
	}

	public static void updateItem(TodoList l) {

		String new_title, new_memo, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ�� �Է����ּ��� > ");
		int index = sc.nextInt();
		
		if(index < 0 || index > l.getCount()) {
			System.out.println("���� ��ȣ�Դϴ�!");
			return;
		}

		System.out.print("�� ���� > ");
		new_title = sc.next().trim();
		System.out.print("�� ī�װ� > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("���ο� ���� > ");
		new_memo = sc.nextLine().trim();
		System.out.print("�� �������� > ");
		new_due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(new_category, new_title, new_memo, new_due_date);
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

	public static void CompleteItem(TodoList l, int comp) {
		if(comp < 0 || comp > l.getCount()) {
			System.out.println("���� ��ȣ�Դϴ�!");
			return;
		}
		
		if (l.completeItem(comp) > 0) {
			System.out.println("�Ϸ� üũ�Ͽ����ϴ�.");
			}
		else{
			System.out.println("�̹� �Ϸ�� �׸��Դϴ�.");
			return;
		}
	}

	public static void ListComp(TodoList l) {
		int count = 0;
		for (TodoItem item : l.getList()) {
			if(item.getIs_completed() == 1) {
			System.out.println(item.toString());
			count++;
			}
		}
		System.out.printf("\n�� %d���� �׸��� �Ϸ�Ǿ����ϴ�.", count);
	}
}
