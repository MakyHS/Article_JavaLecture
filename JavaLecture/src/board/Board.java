package board;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

	Scanner sc = new Scanner(System.in);
	
	ArrayList<Article> articles = new ArrayList<>();
	int No = 1;
	
	public void runBoard() {	
		
		System.out.println("<<�Խù� ���� ���α׷�>>");
		
		while(true) {
		
			System.out.println("");
			
			System.out.print("��ɾ �Է����ּ��� : ");
			String cmd = sc.nextLine();
			
			System.out.println("");
			
			if(cmd.equals("help")) {
				System.out.println("[��ɾ� ����]");
				cmdHelp();
			}
			else if(cmd.equals("add")) {
				System.out.println("[�Խù� ���]");
				cmdAdd();
			}
			else if(cmd.equals("list")) {
				System.out.println("[�Խù� ��� ��ȸ]");
				list();
			}
			else if(cmd.equals("update")) {
				System.out.println("[�Խù� ����]");
				cmdUpdate();
			}
			else if(cmd.equals("delete")) {
				System.out.println("[�Խù� ����]");
				cmdDelete();
			}
			else if(cmd.equals("search")) {
				System.out.println("[�Խù� �˻�]");
				cmdSearch();
			}
		
		}

	}
	
	public void cmdHelp() {
		System.out.println("add    : �Խù� ���");
		System.out.println("list   : �Խù� ��� ��ȸ");
		System.out.println("update : �Խù� ����");
		System.out.println("delete : �Խù� ����");
		System.out.println("search : �Խù� �˻�");
	}
	
	public void cmdAdd() {
		
		System.out.print("������ �Է����ּ��� : ");
		String title = sc.nextLine();
		System.out.print("������ �Է����ּ��� : ");
		String body = sc.nextLine();
		
		Article article = new Article(No, title, body);
		articles.add(article);
		
		No++;
	}
	
	public void list() {
		
		for(int i=0; i<articles.size(); i++) {
			System.out.println("��ȣ : " + articles.get(i).id);
			System.out.println("���� : " + articles.get(i).title);
			System.out.println("========================");
		}
		
	}
	
	public void cmdUpdate() {
		System.out.print("������ �Խù� ��ȣ : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		int exist = getIndexOfArticleNo(targetNo);
		
		if(exist == -1) {
			System.out.println("���� �Խù� ��ȣ�Դϴ�.");
		} else {
			System.out.print("������ : ");
			String title = sc.nextLine();
			System.out.print("������ : ");
			String body = sc.nextLine();
			
			Article article = new Article(No, title, body);
			articles.set(exist, article);
			
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			
			list();
		}
	}
	
	public void cmdDelete() {
		System.out.print("������ �Խù� ��ȣ : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		int exist = getIndexOfArticleNo(targetNo);
		
		if(exist == -1) {
			System.out.println("���� �Խù� ��ȣ�Դϴ�.");
		} else {
			articles.remove(exist);
			
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			
			list();
		}
	}
	
	public void cmdSearch() {
		System.out.println("�˻� Ű���带 �Է����ּ��� : ");
		String keyword = sc.nextLine();
		
		for(int i=0; i<articles.size(); i++) {
			if(articles.get(i).title.contains(keyword)) {
				System.out.println("��ȣ : " + articles.get(i).id);
				System.out.println("���� : " + articles.get(i).title);
				System.out.println("========================");
			}
		}
	}
	
	public int getIndexOfArticleNo(int targetNo) {

		for(int i=0; i<articles.size(); i++) {
			if(targetNo == articles.get(i).id) {
				return i;
			}
		}

		return -1;
		
	}
	

}
