package board;

import java.util.ArrayList;
import java.util.Scanner;
import board.util.MyUtil;

public class Board {

	ArrayList<Article> articles = new ArrayList<>();
	ArrayList<Member> members = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	
	Member loginedMember = null;
	int no = 4;
	
	public Board() {
		makeTestData();
	}
	public void runBoard() {		

		while(true) {
			
			if (loginedMember == null) {
				System.out.print("��ɾ �Է����ּ��� : ");
			} else {
				System.out.print("��ɾ �Է����ּ���[" + loginedMember.nickname + "(" + loginedMember.loginId + ")] : ");
			}
			
			String cmd = sc.nextLine();

			if (cmd.equals("help")) {
				
				printHelp();
				
			}
			else if (cmd.equals("add")) {

				addArticle();
				
			} 
			else if (cmd.equals("list")) {
				
				list(articles);

			} else if (cmd.equals("update")) {

				updateArticle();
				
			}
			else if (cmd.equals("delete")) {
				
				deleteArticle();
				
			}
			else if (cmd.equals("search")) {
				
				searchArticle();
				
			}
			else if (cmd.equals("read")) {
				
				read();
				
			}
			else if (cmd.equals("signup")) {
				
				signup();
				
			}
			else if (cmd.equals("login")) {
				
				login();
				
			}
		}	
	}
	
	private void login() {
		System.out.print("���̵� : ");
		String loginId = sc.nextLine();
		
		System.out.print("��й�ȣ : ");
		String loginPw = sc.nextLine();
		
		boolean isExistLoginId = false;
		
		for(int i=0; i < members.size(); i++) {
			Member member = members.get(i);
			if(member.loginId.equals(loginId) && member.loginPw.equals(loginPw)) {
				System.out.println(member.nickname + "�� ȯ���մϴ�!");
				loginedMember = member;
				isExistLoginId = true;
				break;
			}
		}
		
		if(isExistLoginId == false) {
			System.out.println("��й�ȣ�� Ʋ�Ȱų� �߸��� ȸ�������Դϴ�.");
		}
	}
	
	private void signup() {
		System.out.print("���̵� �Է����ּ��� : ");
		String loginId = sc.nextLine();
		
		System.out.print("��й�ȣ�� �Է����ּ��� : ");
		String loginPw = sc.nextLine();
		
		System.out.print("�г����� �Է����ּ��� : ");
		String nickname = sc.nextLine();
		
		Member member = new Member(loginId, loginPw, nickname);
		members.add(member);
		
		System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
	}
	
	private void makeTestData() {
		articles.add(new Article(1, "��������1", "����1", MyUtil.getCurrentDate("yyyy-MM-dd"), "�͸�", 0));
		articles.add(new Article(2, "��������2", "����2", MyUtil.getCurrentDate("yyyy-MM-dd"), "�͸�", 0));
		articles.add(new Article(3, "��������3", "����3", MyUtil.getCurrentDate("yyyy-MM-dd"), "�͸�", 0));
	}
	
	private void read() {
		System.out.print("�󼼺����� �Խù� ��ȣ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		int targetIndex = getIndexOfArticleNo(targetNo);
		
		if(targetIndex == -1) {
			System.out.println("���� �Խù��Դϴ�.");
		} else {
			Article article = articles.get(targetIndex);
			
			article.hit++;
			
			System.out.println("==== " + article.id + "�� �Խù� ====");
			System.out.println("��ȣ : " + article.id);
			System.out.println("���� : " + article.title);
			System.out.println("=========================");
			System.out.println("���� : " + article.body);
			System.out.println("=========================");
			System.out.println("�ۼ��� : " + article.writer);
			System.out.println("��ϳ�¥ : " + article.regDate);
			System.out.println("��ȸ�� : " + article.hit);
			System.out.println("=========================");
		}
	}
	
	public void searchArticle() {
		System.out.print("�˻��� : ");
		String keyword = sc.nextLine();
		
		ArrayList<Article> searchArticles = new ArrayList<>();
		
		for(int i=0; i<articles.size(); i++) {
			if(articles.get(i).title.contains(keyword)) {
				searchArticles.add(articles.get(i));
			}
		}
		
		list(searchArticles);
	}
	
	public void deleteArticle() {
		System.out.print("������ �Խù� ��ȣ:");
		int targetNo = Integer.parseInt(sc.nextLine());

		int targetIndex = getIndexOfArticleNo(targetNo);

		if(targetIndex == -1) {
			System.out.println("���� �Խù��Դϴ�.");
		} else {
			articles.remove(targetIndex);
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");

			list(articles);
		}
	}
	
	public void updateArticle() {
		System.out.print("������ �Խù� ��ȣ:");
		int targetNo = Integer.parseInt(sc.nextLine());

		int targetIndex = getIndexOfArticleNo(targetNo);

		if(targetIndex == -1) {
			System.out.println("���� �Խù��Դϴ�.");
		} else {
			System.out.print("������ : ");
			String title = sc.nextLine();
			System.out.print("������ : ");
			String body = sc.nextLine();

			Article article = new Article(targetNo, title, body, "2021.11.11", "�͸�", 0);
			articles.set(targetIndex, article);
			
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");

			list(articles);
		}
	}
	
	public void addArticle() {
		System.out.print("������ �Է����ּ��� : ");
		String title = sc.nextLine();
		System.out.print("������ �Է����ּ��� : ");
		String body = sc.nextLine();
		
		String currentDate = MyUtil.getCurrentDate("yyyy-MM-dd");
		Article article = new Article(no, title, body, currentDate, "�͸�", 0);
		articles.add(article);

		System.out.println("�Խù��� ����Ǿ����ϴ�.");
		no++;
	}
	
	public void printHelp() {
		System.out.println("add : �Խù� ���");
		System.out.println("list : �Խù� ��� ��ȸ");
		System.out.println("update : �Խù� ����");
		System.out.println("delete : �Խù� ����");
		System.out.println("search : �Խù� �˻�");
		System.out.println("read : �Խù� �б�");
		System.out.println("signup : ȸ������");
		System.out.println("login : �α���");
	}
	
	public int getIndexOfArticleNo(int targetNo) {
		
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if(article.id == targetNo) {
				return i;
			}
		}
		
		return -1;
		
	}

	public void list(ArrayList<Article> list) {
		for(int i = 0; i < list.size(); i++) {
			Article article = list.get(i);

			System.out.println("��ȣ : " + article.id);
			System.out.println("���� : " + article.title);
			System.out.println("�ۼ��� : " + article.writer);
			System.out.println("��ϳ�¥ : " + article.regDate);
			System.out.println("��ȸ�� : " + article.hit);
			System.out.println("=========================");					
		}
	}
}