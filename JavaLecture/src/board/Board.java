package board;

import java.util.ArrayList;
import java.util.Scanner;

import board.util.MyUtil;

public class Board {

	Scanner sc = new Scanner(System.in);
	
	ArrayList<Article> articles = new ArrayList<>();
	ArrayList<Member> members = new ArrayList<>();
	
	Member loginedMember = null;
	
	int articleNo = 4;
	int memberNo = 3;
	
	public Board() {
		makeTestData();
	}
	
	public void runBoard() {	
		
		System.out.println("<<�Խù� ���� ���α׷�>>");
		
		while(true) {
		
			System.out.println("");
			
			if(loginedMember == null) {
				System.out.print("��ɾ �Է����ּ��� : ");
			} else {
				System.out.print("��ɾ �Է����ּ���[" + loginedMember.nickname + "(" + loginedMember.id + ")] : ");
			}
			
			String cmd = sc.nextLine();
			
			System.out.println("");
			
			if(cmd.equals("help")) {
				System.out.println("[��ɾ� ����]");
				cmdHelp();
			}
			else if(cmd.equals("add")) {
				if(isLoginCheck() == true) {
					System.out.println("[�Խù� ���]");
					cmdAdd();
				}
			}
			else if(cmd.equals("list")) {
				System.out.println("[�Խù� ��� ��ȸ]");
				list(articles);
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
			else if(cmd.equals("read")) {
				System.out.println("[�Խù� �󼼺���]");
				cmdRead();
			}
			else if(cmd.equals("singup")) {
				System.out.println("[ȸ������]");
				cmdSignup();
			}
			else if(cmd.equals("singin")) {
				System.out.println("[�α���]");
				cmdSignin();
			}
			else if(cmd.equals("logout")) {
				if(isLoginCheck() == true) {
					System.out.println("[�α׾ƿ�]");
					cmdLogout();
				}
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
	
	public void cmdLogout() {
		loginedMember = null;
		System.out.println("�α׾ƿ� �Ǽ̽��ϴ�.");
	}
	
	public void cmdSignin() {
		System.out.print("���̵� : ");
		String userID = sc.nextLine();
		System.out.print("��й�ȣ : ");
		String userPW = sc.nextLine();
		
		boolean exist = false;
		
		for(int i=0; i<members.size(); i++) {
			if((userID.equals(members.get(i).id))
					&&(userPW.equals(members.get(i).pw))) {
				Member member = getMemberByMemberNo(i);
				System.out.println(member.nickname + "�� ȯ���մϴ�.");
				exist = true;
				members.add(member);
				loginedMember = member;
			}
		}
		
		if(exist == false) {
			System.out.println("��й�ȣ�� Ʋ�Ȱų� �߸��� ȸ�������Դϴ�.");
		}
		
	}
	
	public void cmdSignup() {
		System.out.println("==== ȸ�� ������ �����մϴ� ====");
		System.out.print("���̵� �Է����ּ��� : ");
		String userID = sc.nextLine();
		System.out.print("��й�ȣ�� �Է����ּ��� : ");
		String userPW = sc.nextLine();
		System.out.print("�г����� �Է����ּ��� : ȫ�浿");
		String userNickname = sc.nextLine();
		
		Member member = new Member(memberNo, userID, userPW, userNickname);
		members.add(member);
		memberNo++;
		
		System.out.println("");
		System.out.println("==== ȸ�������� �Ϸ�Ǿ����ϴ�. ====");
	}
	
	public void cmdRead() {
		System.out.print("�󼼺����� �Խù� ��ȣ�� �Է����ּ��� : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		Article article = getArticleOfArticleNo(targetNo);
		
		if(article == null) {
			System.out.println("���� �Խù� ��ȣ�Դϴ�.");
		} else {
			System.out.println("==== " + targetNo + "�� �Խù� ====");
			System.out.println("��ȣ : " + article.id);
			System.out.println("���� : " + article.title);
			System.out.println("------------------------");
			System.out.println("���� : " + article.body);
			System.out.println("------------------------");
			System.out.println("�ۼ��� : " + article.memberId);
			System.out.println("��ϳ�¥ : " + article.regDate);
			System.out.println("========================");
			
			article.hit++;
		}
	}
	
	public void cmdHelp() {
		System.out.println("add    : �Խù� ���");
		System.out.println("delete : �Խù� ����");
		System.out.println("list   : �Խù� ��� ��ȸ");
		System.out.println("update : �Խù� ����");
		System.out.println("search : �Խù� �˻�");
		System.out.println("read   : �Խù� �󼼺���");
		System.out.println("signup : ȸ������");
	}
	
	public void cmdAdd() {
		
		System.out.print("������ �Է����ּ��� : ");
		String title = sc.nextLine();
		System.out.print("������ �Է����ּ��� : ");
		String body = sc.nextLine();
		
		String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");
		Article article = new Article(articleNo, title, body, currentDate, loginedMember.No, loginedMember.nickname, 0);
		articles.add(article);
		
		articleNo++;
	}
	
	public void list(ArrayList<Article> list) {
		
		for(int i=0; i<articles.size(); i++) {
			Article article = list.get(i);
			
			System.out.println("��ȣ : " + article.id);
			System.out.println("���� : " + article.title);
			System.out.println("�ۼ��� : " + article.memberId);
			System.out.println("��ϳ�¥ : " + article.regDate);
			System.out.println("��ȸ�� : " + article.hit);
			System.out.println("========================");
		}
		
	}
	
	public void cmdUpdate() {
		System.out.print("������ �Խù� ��ȣ : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		Article article = getArticleOfArticleNo(targetNo);
		
		if(article == null) {
			System.out.println("���� �Խù� ��ȣ�Դϴ�.");
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
	
	public void cmdDelete() {
		System.out.print("������ �Խù� ��ȣ : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		Article article = getArticleOfArticleNo(targetNo);
		
		if(article == null) {
			System.out.println("���� �Խù� ��ȣ�Դϴ�.");
		} else {
			article = null;
			
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			
			list(articles);
		}
	}
	
	private void makeTestData() {
		String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");
		
		articles.add(new Article(1, "�ȳ��ϼ���", "����1�Դϴ�.", currentDate, 1, "ȫ�浿", 0));
		articles.add(new Article(2, "�ݰ����ϴ�.", "����2�Դϴ�.", currentDate, 2, "�̼���", 0));
		articles.add(new Article(3, "�ȳ�ȳ�", "����3�Դϴ�.", currentDate, 1, "ȫ�浿", 0));
		
		members.add(new Member(1, "hong123", "1234", "ȫ�浿"));
		members.add(new Member(2, "LeeLee", "1234", "�̼���"));
	}
	
	private void cmdSearch() {
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
	
	public Article getArticleOfArticleNo(int targetNo) {

		Article targetArticle = null;
		
		for(int i=0; i<articles.size(); i++) {
			Article currentArticle = articles.get(i);
			if(targetNo == currentArticle.id) {
				targetArticle = currentArticle;
				break;
			}
		}

		if(targetArticle != null) {
			Member writer = getMemberByMemberNo(targetArticle.memberId);
			targetArticle.nickname = writer.nickname;			
		}
		
		return targetArticle;
	}
	
	public Member getMemberByMemberNo(int memberId) {

		for(int i=0; i<members.size(); i++) {
			if(memberId == members.get(i).No) {
				return members.get(i);
			}
		}

		return null;
	}

}
