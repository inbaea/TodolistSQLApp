package com.todo.service;

import java.io.*;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String category, title, desc, due_date;
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
		System.out.print("\nī�װ� > ");
		category = sc.nextLine().trim();
		System.out.print( "\n���� > ");
		desc = sc.nextLine().trim();
		System.out.print("\n�������� > ");
		due_date = sc.nextLine().trim();
		
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		int listnum = 0;
		int i = 1;
		for (TodoItem item : l.getList())
			listnum++;
		
		
		System.out.print("\n������ �׸��� ��ȣ > ");
		int listno = sc.nextInt();
		
		
		if (listno < 0 || listnum < listno) {
			System.out.println("\n" + "�ش� ��ȣ�� �Ҵ�� �׸��� �������� �ʽ��ϴ�.");
			return;
		}
		for (TodoItem item : l.getList()) {
			if(i == listno)
			System.out.println(i + "." + item.toString());
			i++;
		}
		i = 1;
		boolean exit_while = true;
		while(exit_while) {
		System.out.print("�� �׸��� �����Ͻðڽ��ϱ�?(y/n) > ");
		String YorN = sc.next();
		if(YorN.equals("y")) {
		System.out.println("\n"
				+ "==========�׸� ����");
		
		for (TodoItem item : l.getList()) {
			if (i == listno) {
				l.deleteItem(item);
				break;
					}
			i++;
				}
		break;
			}
		else if(YorN.equals("n")) {
			System.out.println("\n" + "��ҵǾ����ϴ�.");
			break;
		}
		else exit_while = true;
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		int listnum = 0;
		int i = 1;
		for (TodoItem item : l.getList())
			listnum++;
		
		System.out.print("\n"
				+ "==========�׸� ����\n"
				+ "������ �ʿ��� �׸��� ��ȣ > ");
		int listno = sc.nextInt();
		if ( listno < 0 || listnum < listno) {
			System.out.println("\n" + "�ش� ��ȣ�� �Ҵ�� �׸��� �����ϴ�.");
			return;
		}
		
		for (TodoItem item : l.getList()) {
			if(i == listno)
			System.out.println(i + "." + item.toString());
			i++;
		}
		i = 1;


		System.out.print("�� ���� > ");
		String new_title = sc.next().trim();
		sc.nextLine();
		if (l.isDuplicate(new_title)) {
			System.out.println("\n" + "�ߺ��� ������ ���Ұ����մϴ�.");
			return;
		}
		
		System.out.print("�� ī�װ� > ");
		String new_category = sc.next().trim();
		sc.nextLine();
		
		System.out.print("���ο� ���� > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("�� �������� > ");
		String new_duedate = sc.next().trim();
		
		for (TodoItem item : l.getList()) {
			if (i == listno) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_duedate);
				l.addItem(t);
				System.out.println("\n" + "�׸��� �����Ǿ����ϴ�.");
			}
			i++;
		}

	}

	public static void listAll(TodoList l) {
		int listnum = 0;
		for (TodoItem item : l.getList())
			listnum++;
		System.out.printf("\n" + "[��ü ���, �� %d��]" + "\n", listnum);
		listnum = 1;
		for (TodoItem item : l.getList()) {
			System.out.printf("%d.", listnum);
			listnum++;
			System.out.println(item.toString());
		}
	}
	
	public static void listCate(TodoList l) {
		 ArrayList<String> array = new ArrayList<>();
		 
		for (TodoItem item : l.getList()) {
			if(!array.contains(item.getCategory()))
				array.add(item.getCategory());
		}
		for (int i = 0; i < array.size(); i++) {
			System.out.print(array.get(i));
			if (i != array.size()-1) {
				System.out.print(" / ");
			}
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ��ֽ��ϴ�.", array.size());
	}

	
	public static void FindItem(TodoList l, String item_name) {
		
		int itemnum = 0;
		int i = 1;
		for (TodoItem item : l.getList()) {
			if(item.getTitle().contains(item_name) || item.getDesc().contains(item_name) || item.getCategory().contains(item_name)) {
			
				System.out.println(i + "." + item.toString());
				itemnum++;
				}
			i++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.", itemnum);
}
	
	public static void FindCate(TodoList l, String item_name) {
		
		int itemnum = 0;
		int i = 1;
		for (TodoItem item : l.getList()) {
			if(item.getCategory().contains(item_name)) {
				
				System.out.println(i + "." + item.toString());
				itemnum++;
				}
			i++;
		}
		System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.", itemnum);
}
	
	public static void loadList(TodoList l, String filename) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String str;
		int data=0;
		
		while((str = br.readLine())!=null) {
			StringTokenizer tk = new StringTokenizer(str, "##");
			
			String category = tk.nextToken();
			String title = tk.nextToken();
			String desc = tk.nextToken();
			String due_date = tk.nextToken();
			String current_date = tk.nextToken();
			
			data++;
			TodoItem t = new TodoItem(category, title, desc, due_date, current_date);
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
