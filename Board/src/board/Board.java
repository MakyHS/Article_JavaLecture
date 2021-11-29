package board;

import java.util.ArrayList;
import java.util.Scanner;

import board.util.MyUtil;

public class Board {

	ArrayList<Article> articles = new ArrayList<>();
	ArrayList<Member> members = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	
	Member loginedMember = null;
	int articleNo = 4;
	int memberNo = 3;
	
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

				if(isLoginCheck() == true) {
					addArticle();
				}
				
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
			else if (cmd.equals("logout")) {
				
				logout();
				
			}
		}	
	}
	
	private boolean isLoginCheck() {
		if(loginedMember == null) {
			System.out.println("�α����� �ʿ��� ����Դϴ�.");
			return false;
		}
		
		return true;
	}
	
	private void logout() {
		loginedMember = null;
		System.out.println("�α׾ƿ� �Ǽ̽��ϴ�.");
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
		
		Member member = new Member(memberNo, loginId, loginPw, nickname);
		members.add(member);
		
		System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
	}
	
	private void makeTestData() {
		String currentDate = MyUtil.getCurrentDate("yyyy-MM-dd");
		
		articles.add(new Article(1, "��������1", "����1", currentDate, 1, 0));
		articles.add(new Article(2, "��������2", "����2", currentDate, 2, 0));
		articles.add(new Article(3, "��������3", "����3", currentDate, 1, 0));
		
		members.add(new Member(1, "hong123", "h1234", "ȫ�浿"));
		members.add(new Member(2, "lee123", "1234", "�̼���"));
		
		loginedMember = members.get(0);
	}
	
	private void read() {
		System.out.print("�󼼺����� �Խù� ��ȣ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		Article article = getArticleByNo(targetNo);
		
		if(article == null) {
			System.out.println("���� �Խù��Դϴ�.");
		} else {
			
			article.hit++;
			
			System.out.println("==== " + article.id + "�� �Խù� ====");
			System.out.println("��ȣ : " + article.id);
			System.out.println("���� : " + article.title);
			System.out.println("=========================");
			System.out.println("���� : " + article.body);
			System.out.println("=========================");
			System.out.println("�ۼ��� : " + article.nickname);
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

		Article article = getArticleByNo(targetNo);

		if(article == null) {
			System.out.println("���� �Խù��Դϴ�.");
		} else {
			articles.remove(article);
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");

			list(articles);
		}
	}
	
	public void updateArticle() {
		System.out.print("������ �Խù� ��ȣ:");
		int targetNo = Integer.parseInt(sc.nextLine());

		Article article = getArticleByNo(targetNo);

		if(article == null) {
			System.out.println("���� �Խù��Դϴ�.");
		} else {
			System.out.print("������ : ");
			String title = sc.nextLine();
			System.out.print("������ : ");
			String body = sc.nextLine();

			article.title = title;
			article.body = body;
			
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
		Article article = new Article(articleNo, title, body, currentDate, loginedMember.id, 0);
		articles.add(article);

		System.out.println("�Խù��� ����Ǿ����ϴ�.");
		articleNo++;
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
	
	
	public Article getArticleByNo(int targetNo) {
		
		Article targetArticle = null;
		
		for(int i = 0; i < articles.size(); i++) {
			Article currentArticle = articles.get(i);
			if(currentArticle.id == targetNo) {
				targetArticle = currentArticle;
				break;
			}
		}
		
		targetArticle = setArticleNickname(targetArticle);
		
		return targetArticle;
		
	}
	
	private Article setArticleNickname(Article article) {
		
		if(article != null) {
			Member member = getMemberByMemberNo(article.memberId);
			article.nickname = member.nickname;
		}
		
		return article;
	}
	
	private Member getMemberByMemberNo(int memberId) {
		
		Member targetMember = null;
		
		for(int i=0; i<members.size(); i++) {
			Member currentMember = members.get(i);
			if(memberId == currentMember.id) {
				targetMember = currentMember;
				break;
			}
		}
		
		return targetMember;
	}

	public void list(ArrayList<Article> list) {
		for(int i = 0; i < list.size(); i++) {
			Article article = list.get(i);

			System.out.println("��ȣ : " + article.id);
			System.out.println("���� : " + article.title);
			System.out.println("�ۼ��� : " + article.memberId);
			System.out.println("��ϳ�¥ : " + article.regDate);
			System.out.println("��ȸ�� : " + article.hit);
			System.out.println("=========================");					
		}
	}
}