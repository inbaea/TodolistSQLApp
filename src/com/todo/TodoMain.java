package com.todo;

import java.io.IOException;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() throws IOException {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
			
			case "ls_cate":
				TodoUtil.listCate(l);
				break;

			case "ls_name_asc":
				System.out.println("��������� �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "title", 1);
				break;

			case "ls_name_desc":
				System.out.println("���񿪼����� �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "title", 0);
				isList = true;
				break;
				
			case "ls_date":
				System.out.println("��¥������ �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.println("��¥�������� �����Ͽ����ϴ�.");
				TodoUtil.listAll(l, "due_date", 0);
				break;
				
			case "help":
				Menu.displaymenu();
				break;
			
			case "find":
				String item_name = sc.nextLine().trim();
				TodoUtil.FindItem(l, item_name);
				break;
				
			case "find_cate":
				String item_nam = sc.nextLine().trim();
				TodoUtil.FindCate(l, item_nam);
				break;
			
			case "comp":
				TodoUtil.CompleteItem(l);
				break;
				
			case "ls_comp":
				TodoUtil.ListComp(l);
				break;
			
			case "due_time":
				int time = sc.nextInt();
				TodoUtil.DueTime(l, time);
				break;

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("��Ȯ�� ��ɾ �Է����ּ���. (���� - help)");
				break;
			}
		} while (!quit);
	}
}
