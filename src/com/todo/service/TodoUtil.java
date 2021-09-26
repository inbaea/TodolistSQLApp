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
				+ "==========항목 생성\n"
				+ "제목 > ");
		
		
		title = sc.next().trim();
		if (list.isDuplicate(title)) {
			System.out.printf("중복된 제목은 사용불가입니다.");
			return;
		}
		sc.nextLine();
		System.out.print("\n카테고리 > ");
		category = sc.nextLine().trim();
		System.out.print( "\n내용 > ");
		desc = sc.nextLine().trim();
		System.out.print("\n마감일자 > ");
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
		
		
		System.out.print("\n제거할 항목의 번호 > ");
		int listno = sc.nextInt();
		
		
		if (listno < 0 || listnum < listno) {
			System.out.println("\n" + "해당 번호에 할당된 항목이 존재하지 않습니다.");
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
		System.out.print("위 항목을 삭제하시겠습니까?(y/n) > ");
		String YorN = sc.next();
		if(YorN.equals("y")) {
		System.out.println("\n"
				+ "==========항목 삭제");
		
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
			System.out.println("\n" + "취소되었습니다.");
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
				+ "==========항목 수정\n"
				+ "수정이 필요한 항목의 번호 > ");
		int listno = sc.nextInt();
		if ( listno < 0 || listnum < listno) {
			System.out.println("\n" + "해당 번호에 할당된 항목이 없습니다.");
			return;
		}
		
		for (TodoItem item : l.getList()) {
			if(i == listno)
			System.out.println(i + "." + item.toString());
			i++;
		}
		i = 1;


		System.out.print("새 제목 > ");
		String new_title = sc.next().trim();
		sc.nextLine();
		if (l.isDuplicate(new_title)) {
			System.out.println("\n" + "중복된 제목은 사용불가능합니다.");
			return;
		}
		
		System.out.print("새 카테고리 > ");
		String new_category = sc.next().trim();
		sc.nextLine();
		
		System.out.print("새로운 내용 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 마감기한 > ");
		String new_duedate = sc.next().trim();
		
		for (TodoItem item : l.getList()) {
			if (i == listno) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category, new_title, new_description, new_duedate);
				l.addItem(t);
				System.out.println("\n" + "항목이 수정되었습니다.");
			}
			i++;
		}

	}

	public static void listAll(TodoList l) {
		int listnum = 0;
		for (TodoItem item : l.getList())
			listnum++;
		System.out.printf("\n" + "[전체 목록, 총 %d개]" + "\n", listnum);
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
		System.out.printf("\n총 %d개의 카테고리가 등록되어있습니다.", array.size());
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
		System.out.printf("총 %d개의 항목을 찾았습니다.", itemnum);
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
		System.out.printf("총 %d개의 항목을 찾았습니다.", itemnum);
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
