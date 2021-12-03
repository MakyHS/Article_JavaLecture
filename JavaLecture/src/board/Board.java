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
		
		System.out.println("<<게시물 관리 프로그램>>");
		
		while(true) {
		
			System.out.println("");
			
			if(loginedMember == null) {
				System.out.print("명령어를 입력해주세요 : ");
			} else {
				System.out.print("명령어를 입력해주세요[" + loginedMember.nickname + "(" + loginedMember.id + ")] : ");
			}
			
			String cmd = sc.nextLine();
			
			System.out.println("");
			
			if(cmd.equals("help")) {
				System.out.println("[명령어 모음]");
				cmdHelp();
			}
			else if(cmd.equals("add")) {
				if(isLoginCheck() == true) {
					System.out.println("[게시물 등록]");
					cmdAdd();
				}
			}
			else if(cmd.equals("list")) {
				System.out.println("[게시물 목록 조회]");
				list(articles);
			}
			else if(cmd.equals("update")) {
				System.out.println("[게시물 수정]");
				cmdUpdate();
			}
			else if(cmd.equals("delete")) {
				System.out.println("[게시물 삭제]");
				cmdDelete();
			}
			else if(cmd.equals("search")) {
				System.out.println("[게시물 검색]");
				cmdSearch();
			}
			else if(cmd.equals("read")) {
				System.out.println("[게시물 상세보기]");
				cmdRead();
			}
			else if(cmd.equals("singup")) {
				System.out.println("[회원가입]");
				cmdSignup();
			}
			else if(cmd.equals("singin")) {
				System.out.println("[로그인]");
				cmdSignin();
			}
			else if(cmd.equals("logout")) {
				if(isLoginCheck() == true) {
					System.out.println("[로그아웃]");
					cmdLogout();
				}
			}
		
		}

	}
		
	private boolean isLoginCheck() {
		if(loginedMember == null) {
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		}
		
		return true;
	}
	
	public void cmdLogout() {
		loginedMember = null;
		System.out.println("로그아웃 되셨습니다.");
	}
	
	public void cmdSignin() {
		System.out.print("아이디 : ");
		String userID = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPW = sc.nextLine();
		
		boolean exist = false;
		
		for(int i=0; i<members.size(); i++) {
			if((userID.equals(members.get(i).id))
					&&(userPW.equals(members.get(i).pw))) {
				Member member = getMemberByMemberNo(i);
				System.out.println(member.nickname + "님 환영합니다.");
				exist = true;
				members.add(member);
				loginedMember = member;
			}
		}
		
		if(exist == false) {
			System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
		}
		
	}
	
	public void cmdSignup() {
		System.out.println("==== 회원 가입을 진행합니다 ====");
		System.out.print("아이디를 입력해주세요 : ");
		String userID = sc.nextLine();
		System.out.print("비밀번호를 입력해주세요 : ");
		String userPW = sc.nextLine();
		System.out.print("닉네임을 입력해주세요 : 홍길동");
		String userNickname = sc.nextLine();
		
		Member member = new Member(memberNo, userID, userPW, userNickname);
		members.add(member);
		memberNo++;
		
		System.out.println("");
		System.out.println("==== 회원가입이 완료되었습니다. ====");
	}
	
	public void cmdRead() {
		System.out.print("상세보기할 게시물 번호를 입력해주세요 : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		Article article = getArticleOfArticleNo(targetNo);
		
		if(article == null) {
			System.out.println("없는 게시물 번호입니다.");
		} else {
			System.out.println("==== " + targetNo + "번 게시물 ====");
			System.out.println("번호 : " + article.id);
			System.out.println("제목 : " + article.title);
			System.out.println("------------------------");
			System.out.println("내용 : " + article.body);
			System.out.println("------------------------");
			System.out.println("작성자 : " + article.memberId);
			System.out.println("등록날짜 : " + article.regDate);
			System.out.println("========================");
			
			article.hit++;
		}
	}
	
	public void cmdHelp() {
		System.out.println("add    : 게시물 등록");
		System.out.println("delete : 게시물 삭제");
		System.out.println("list   : 게시물 목록 조회");
		System.out.println("update : 게시물 수정");
		System.out.println("search : 게시물 검색");
		System.out.println("read   : 게시물 상세보기");
		System.out.println("signup : 회원가입");
	}
	
	public void cmdAdd() {
		
		System.out.print("제목을 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.print("내용을 입력해주세요 : ");
		String body = sc.nextLine();
		
		String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");
		Article article = new Article(articleNo, title, body, currentDate, loginedMember.No, loginedMember.nickname, 0);
		articles.add(article);
		
		articleNo++;
	}
	
	public void list(ArrayList<Article> list) {
		
		for(int i=0; i<articles.size(); i++) {
			Article article = list.get(i);
			
			System.out.println("번호 : " + article.id);
			System.out.println("제목 : " + article.title);
			System.out.println("작성자 : " + article.memberId);
			System.out.println("등록날짜 : " + article.regDate);
			System.out.println("조회수 : " + article.hit);
			System.out.println("========================");
		}
		
	}
	
	public void cmdUpdate() {
		System.out.print("수정할 게시물 번호 : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		Article article = getArticleOfArticleNo(targetNo);
		
		if(article == null) {
			System.out.println("없는 게시물 번호입니다.");
		} else {
			System.out.print("새제목 : ");
			String title = sc.nextLine();
			System.out.print("새내용 : ");
			String body = sc.nextLine();
			
			article.title = title;
			article.body = body;
			
			System.out.println("수정이 완료되었습니다.");
			
			list(articles);
		}
	}
	
	public void cmdDelete() {
		System.out.print("삭제할 게시물 번호 : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		Article article = getArticleOfArticleNo(targetNo);
		
		if(article == null) {
			System.out.println("없는 게시물 번호입니다.");
		} else {
			article = null;
			
			System.out.println("삭제가 완료되었습니다.");
			
			list(articles);
		}
	}
	
	private void makeTestData() {
		String currentDate = MyUtil.getCurrentDate("yyyy.MM.dd");
		
		articles.add(new Article(1, "안녕하세요", "내용1입니다.", currentDate, 1, "홍길동", 0));
		articles.add(new Article(2, "반갑습니다.", "내용2입니다.", currentDate, 2, "이순신", 0));
		articles.add(new Article(3, "안녕안녕", "내용3입니다.", currentDate, 1, "홍길동", 0));
		
		members.add(new Member(1, "hong123", "1234", "홍길동"));
		members.add(new Member(2, "LeeLee", "1234", "이순신"));
	}
	
	private void cmdSearch() {
		System.out.println("검색 키워드를 입력해주세요 : ");
		String keyword = sc.nextLine();
		
		for(int i=0; i<articles.size(); i++) {
			if(articles.get(i).title.contains(keyword)) {
				System.out.println("번호 : " + articles.get(i).id);
				System.out.println("제목 : " + articles.get(i).title);
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
