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
				System.out.print("명령어를 입력해주세요 : ");
			} else {
				System.out.print("명령어를 입력해주세요[" + loginedMember.nickname + "(" + loginedMember.loginId + ")] : ");
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
			System.out.println("로그인이 필요한 기능입니다.");
			return false;
		}
		
		return true;
	}
	
	private void logout() {
		loginedMember = null;
		System.out.println("로그아웃 되셨습니다.");
	}
	
	private void login() {
		System.out.print("아이디 : ");
		String loginId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String loginPw = sc.nextLine();
		
		boolean isExistLoginId = false;
		
		for(int i=0; i < members.size(); i++) {
			Member member = members.get(i);
			if(member.loginId.equals(loginId) && member.loginPw.equals(loginPw)) {
				System.out.println(member.nickname + "님 환영합니다!");
				loginedMember = member;
				isExistLoginId = true;
				break;
			}
		}
		
		if(isExistLoginId == false) {
			System.out.println("비밀번호를 틀렸거나 잘못된 회원정보입니다.");
		}
	}
	
	private void signup() {
		System.out.print("아이디를 입력해주세요 : ");
		String loginId = sc.nextLine();
		
		System.out.print("비밀번호를 입력해주세요 : ");
		String loginPw = sc.nextLine();
		
		System.out.print("닉네임을 입력해주세요 : ");
		String nickname = sc.nextLine();
		
		Member member = new Member(memberNo, loginId, loginPw, nickname);
		members.add(member);
		
		System.out.println("회원가입이 완료되었습니다.");
	}
	
	private void makeTestData() {
		String currentDate = MyUtil.getCurrentDate("yyyy-MM-dd");
		
		articles.add(new Article(1, "예시제목1", "내용1", currentDate, 1, 0));
		articles.add(new Article(2, "예시제목2", "내용2", currentDate, 2, 0));
		articles.add(new Article(3, "예시제목3", "내용3", currentDate, 1, 0));
		
		members.add(new Member(1, "hong123", "h1234", "홍길동"));
		members.add(new Member(2, "lee123", "1234", "이순신"));
		
		loginedMember = members.get(0);
	}
	
	private void read() {
		System.out.print("상세보기할 게시물 번호");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		Article article = getArticleByNo(targetNo);
		
		if(article == null) {
			System.out.println("없는 게시물입니다.");
		} else {
			
			article.hit++;
			
			System.out.println("==== " + article.id + "번 게시물 ====");
			System.out.println("번호 : " + article.id);
			System.out.println("제목 : " + article.title);
			System.out.println("=========================");
			System.out.println("내용 : " + article.body);
			System.out.println("=========================");
			System.out.println("작성자 : " + article.nickname);
			System.out.println("등록날짜 : " + article.regDate);
			System.out.println("조회수 : " + article.hit);
			System.out.println("=========================");
		}
	}
	
	public void searchArticle() {
		System.out.print("검색어 : ");
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
		System.out.print("삭제할 게시물 번호:");
		int targetNo = Integer.parseInt(sc.nextLine());

		Article article = getArticleByNo(targetNo);

		if(article == null) {
			System.out.println("없는 게시물입니다.");
		} else {
			articles.remove(article);
			System.out.println("삭제가 완료되었습니다.");

			list(articles);
		}
	}
	
	public void updateArticle() {
		System.out.print("수정할 게시물 번호:");
		int targetNo = Integer.parseInt(sc.nextLine());

		Article article = getArticleByNo(targetNo);

		if(article == null) {
			System.out.println("없는 게시물입니다.");
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
	
	public void addArticle() {
		System.out.print("제목을 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.print("내용을 입력해주세요 : ");
		String body = sc.nextLine();
		
		String currentDate = MyUtil.getCurrentDate("yyyy-MM-dd");
		Article article = new Article(articleNo, title, body, currentDate, loginedMember.id, 0);
		articles.add(article);

		System.out.println("게시물이 저장되었습니다.");
		articleNo++;
	}
	
	public void printHelp() {
		System.out.println("add : 게시물 등록");
		System.out.println("list : 게시물 목록 조회");
		System.out.println("update : 게시물 수정");
		System.out.println("delete : 게시물 삭제");
		System.out.println("search : 게시물 검색");
		System.out.println("read : 게시물 읽기");
		System.out.println("signup : 회원가입");
		System.out.println("login : 로그인");
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

			System.out.println("번호 : " + article.id);
			System.out.println("제목 : " + article.title);
			System.out.println("작성자 : " + article.memberId);
			System.out.println("등록날짜 : " + article.regDate);
			System.out.println("조회수 : " + article.hit);
			System.out.println("=========================");					
		}
	}
}