package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("< TodoList 명령어 사용법 >");
        System.out.println("1. 항목 추가 -> add");
        System.out.println("2. 항목 삭제 -> del");
        System.out.println("3. 항목 수정 -> edit");
        System.out.println("4. 전체 목록 -> ls");
        System.out.println("5. 카테고리별 목록 -> ls_cate");
        System.out.println("6. 특정단어 찾기 -> find + 단어");
        System.out.println("7. 특정단어로 구성된 카테고리 찾기 -> find_cate + 단어");
        System.out.println("8. 제목순으로 정렬 -> ls_name_asc");
        System.out.println("9. 제목역순으로 정렬 -> ls_name_desc");
        System.out.println("10. 생성시간별로 정렬 -> ls_date");
        System.out.println("11. 생성시간별로 정렬 -> ls_date_desc");
        System.out.println("12. 완료 체크 추가 -> comp");
        System.out.println("13. 완료 항목 출력 -> ls_comp");
        System.out.println("14. 만기 시간 출력 -> due_time + 번호");
        System.out.println("15. 종료 -> exit");
    }

	public static void prompt() {
		System.out.print("\nCommand > ");
	}
}