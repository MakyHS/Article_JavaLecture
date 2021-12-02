package board;

import java.util.ArrayList;
import java.util.Scanner;

public class Board {

	Scanner sc = new Scanner(System.in);
	
	ArrayList<Article> articles = new ArrayList<>();
	int No = 1;
	
	public void runBoard() {	
		
		System.out.println("<<게시물 관리 프로그램>>");
		
		while(true) {
		
			System.out.println("");
			
			System.out.print("명령어를 입력해주세요 : ");
			String cmd = sc.nextLine();
			
			System.out.println("");
			
			if(cmd.equals("help")) {
				System.out.println("[명령어 모음]");
				cmdHelp();
			}
			else if(cmd.equals("add")) {
				System.out.println("[게시물 등록]");
				cmdAdd();
			}
			else if(cmd.equals("list")) {
				System.out.println("[게시물 목록 조회]");
				list();
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
		
		}

	}
	
	public void cmdHelp() {
		System.out.println("add    : 게시물 등록");
		System.out.println("list   : 게시물 목록 조회");
		System.out.println("update : 게시물 수정");
		System.out.println("delete : 게시물 삭제");
		System.out.println("search : 게시물 검색");
	}
	
	public void cmdAdd() {
		
		System.out.print("제목을 입력해주세요 : ");
		String title = sc.nextLine();
		System.out.print("내용을 입력해주세요 : ");
		String body = sc.nextLine();
		
		Article article = new Article(No, title, body);
		articles.add(article);
		
		No++;
	}
	
	public void list() {
		
		for(int i=0; i<articles.size(); i++) {
			System.out.println("번호 : " + articles.get(i).id);
			System.out.println("제목 : " + articles.get(i).title);
			System.out.println("========================");
		}
		
	}
	
	public void cmdUpdate() {
		System.out.print("수정할 게시물 번호 : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		int exist = getIndexOfArticleNo(targetNo);
		
		if(exist == -1) {
			System.out.println("없는 게시물 번호입니다.");
		} else {
			System.out.print("새제목 : ");
			String title = sc.nextLine();
			System.out.print("새내용 : ");
			String body = sc.nextLine();
			
			Article article = new Article(No, title, body);
			articles.set(exist, article);
			
			System.out.println("수정이 완료되었습니다.");
			
			list();
		}
	}
	
	public void cmdDelete() {
		System.out.print("삭제할 게시물 번호 : ");
		int targetNo = Integer.parseInt(sc.nextLine());
		
		int exist = getIndexOfArticleNo(targetNo);
		
		if(exist == -1) {
			System.out.println("없는 게시물 번호입니다.");
		} else {
			articles.remove(exist);
			
			System.out.println("삭제가 완료되었습니다.");
			
			list();
		}
	}
	
	public void cmdSearch() {
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
	
	public int getIndexOfArticleNo(int targetNo) {

		for(int i=0; i<articles.size(); i++) {
			if(targetNo == articles.get(i).id) {
				return i;
			}
		}

		return -1;
		
	}
	

}
