package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("< TodoList ��ɾ� ���� >");
        System.out.println("1. �׸� �߰� -> add");
        System.out.println("2. �׸� ���� -> del");
        System.out.println("3. �׸� ���� -> edit");
        System.out.println("4. ��ü ��� -> ls");
        System.out.println("5. ī�װ��� ��� -> ls_cate");
        System.out.println("6. Ư���ܾ� ã�� -> find + �ܾ�");
        System.out.println("7. Ư���ܾ�� ������ ī�װ� ã�� -> find_cate + �ܾ�");
        System.out.println("8. ��������� ���� -> ls_name_asc");
        System.out.println("9. ���񿪼����� ���� -> ls_name_desc");
        System.out.println("10. �����ð����� ���� -> ls_date");
        System.out.println("11. �����ð����� ���� -> ls_date_desc");
        System.out.println("12. �Ϸ� üũ �߰� -> comp");
        System.out.println("13. �Ϸ� �׸� ��� -> ls_comp");
        System.out.println("14. ���� �ð� ��� -> due_time + ��ȣ");
        System.out.println("15. ���� -> exit");
    }

	public static void prompt() {
		System.out.print("\nCommand > ");
	}
}