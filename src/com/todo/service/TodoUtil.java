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
				+ "==========항목 생성\n"
				+ "제목 > ");
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("중복된 제목은 사용불가입니다.");
			return;
		}
		sc.nextLine();
		System.out.print("\n" + "내용 > ");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("\n제거할 항목의 제목 > ");
		String title = sc.next();
		if (!l.isDuplicate(title)) {
			System.out.println("\n" + "제목이 존재하지 않습니다.");
			return;
		}
		
		System.out.print("\n"
				+ "==========항목 삭제\n");
		
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
				+ "==========항목 수정\n"
				+ "수정이 필요한 항목의 제목 > ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("\n" + "제목이 존재하지 않습니다.");
			return;
		}

		System.out.print("\n" + "새로운 제목 > ");
		String new_title = sc.next().trim();
		sc.nextLine();
		if (l.isDuplicate(new_title)) {
			System.out.println("\n" + "중복된 제목은 사용불가능합니다.");
			return;
		}
		
		System.out.print("\n" + "새로운 내용 > ");
		String new_description = sc.nextLine().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("\n" + "항목이 수정되었습니다.");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("\n" + "[전체 목록]");
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
		System.out.printf("%d개의 항목을 읽었습니다.\n", data);
		br.close();
	}
	
	public static void saveList(TodoList l, String filename) throws IOException {
			
			FileWriter writer = new FileWriter(filename);
	
			for (TodoItem item : l.getList()) {
				String str = item.toSaveString();
				writer.write(str);
			}
			
			System.out.println("텍스트에 저장이 완료되었읍니다!");
			writer.close();										
	}
}
