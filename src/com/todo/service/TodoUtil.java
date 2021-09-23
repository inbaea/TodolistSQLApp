package com.todo.service;

import java.io.*;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "==========�׸� ����\n"
				+ "���� > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("�ߺ��� ������ ���Ұ��Դϴ�.");
			return;
		}
		sc.nextLine();
		System.out.print("\n" + "���� > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\n������ �׸��� ���� > ");
		String title = sc.next();
		if (!l.isDuplicate(title)) {
			System.out.println("\n" + "������ �������� �ʽ��ϴ�.");
			return;
		}
		
		System.out.print("\n"
				+ "==========�׸� ����\n");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "==========�׸� ����\n"
				+ "������ �ʿ��� �׸��� ���� > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("\n" + "������ �������� �ʽ��ϴ�.");
			return;
		}

		System.out.print("\n" + "���ο� ���� > ");
		String new_title = sc.next().trim();
		sc.nextLine();
		if (l.isDuplicate(new_title)) {
			System.out.println("\n" + "�ߺ��� ������ ���Ұ����մϴ�.");
			return;
		}
		
		System.out.print("\n" + "���ο� ���� > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("\n" + "�׸��� �����Ǿ����ϴ�.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("\n" + "[��ü ���]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void loadList(TodoList l, String filename) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String str;
		int data=0;
		
		while((str = br.readLine())!=null) {
			StringTokenizer tk = new StringTokenizer(str, "##");
			
			String title = tk.nextToken();
			String desc = tk.nextToken();
			String current_date = tk.nextToken();
			
			data++;
			TodoItem t = new TodoItem(title, desc, current_date);
			l.addItem(t);
		}
		
		if(data!=0)
		System.out.printf("%d���� �׸��� �о����ϴ�.\n", data);
		br.close();
	}
	
	public static void saveList(TodoList l, String filename) throws IOException {
			
			FileWriter writer = new FileWriter(filename);
	
			for (TodoItem item : l.getList()) {
				String str = item.toSaveString();
				writer.write(str);
			}
			
			System.out.println("�ؽ�Ʈ�� ������ �Ϸ�Ǿ����ϴ�!");
			writer.close();										
	}
}
