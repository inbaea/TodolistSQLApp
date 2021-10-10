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

		System.out.print("[항목추가]\n" + "제목 > ");
		title = sc.next();
		if (TodoList.isDuplicate(l, title)) {
		 System.out.printf("중복된 제목은 사용불가입니다.");
		 return;
		}
		System.out.print("카테고리 > ");
		category = sc.next();
		sc.nextLine();
		System.out.print("내용 > ");
		memo = sc.nextLine().trim();
		System.out.print("마감일자 > ");
		due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(category, title, memo, due_date);
		if (l.addItem(t) > 0) {
			System.out.println("추가되었습니다.");
		}
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
	
		System.out.print("[항목 삭제]\n" + "삭제할 항목의 번호를 입력하십시오 > ");
		int index = sc.nextInt();
		if(index < 0 || index > l.getCount()) {
			System.out.println("없는 번호입니다!");
			return;
		}
		if (l.deleteItem(index) > 0)
			System.out.println("삭제되었습니다.");
	}

	public static void updateItem(TodoList l) {

		String new_title, new_memo, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);

		System.out.print("[항목 수정]\n" + "수정할 항목의 번호를 입력해주세요 > ");
		int index = sc.nextInt();
		
		if(index < 0 || index > l.getCount()) {
			System.out.println("없는 번호입니다!");
			return;
		}

		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
		System.out.print("새 카테고리 > ");
		new_category = sc.next();
		sc.nextLine();
		System.out.print("새로운 내용 > ");
		new_memo = sc.nextLine().trim();
		System.out.print("새 마감기한 > ");
		new_due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(new_category, new_title, new_memo, new_due_date);
		t.setId(index);
		if (l.updateItem(t) > 0)
			System.out.println("항목이 수정되었습니다.");
	}

	public static void listAll(TodoList l) {
		System.out.printf("\n" + "[전체 목록, 총 %d개]" + "\n", l.getCount());
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
		System.out.printf("\n총 %d개의 카테고리가 등록되어있습니다.\n", count);
	}

	public static void FindItem(TodoList l, String item_name) {

		int count = 0;
		for (TodoItem item : l.getList(item_name)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.", count);
	}

	public static void FindCate(TodoList l, String item_name) {

		int count = 0;
		for (TodoItem item : l.getListCategory(item_name)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void listAll(TodoList l, String order, int ordering) {
		System.out.printf("[전체목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderList(order, ordering)) {
			System.out.println(item.toString());
		}
		
	}

	public static void CompleteItem(TodoList l, int comp) {
		if(comp < 0 || comp > l.getCount()) {
			System.out.println("없는 번호입니다!");
			return;
		}
		
		if (l.completeItem(comp) > 0) {
			System.out.println("완료 체크하였습니다.");
			}
		else{
			System.out.println("이미 완료된 항목입니다.");
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
		System.out.printf("\n총 %d개의 항목이 완료되었습니다.", count);
	}
}
