package board;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("<게시판 프로그램>");
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> postNumber = new ArrayList<>();
		ArrayList<String> postTitle = new ArrayList<>();
		ArrayList<String> postContent = new ArrayList<>();
		int Number = 1;
		
		System.out.println("");
		
		while(true) {
			System.out.print("명령어를 입력해주세요 : ");
			String ord = sc.nextLine();
			
			System.out.println("");
			
			if(ord.equals("add")) {
				System.out.print("제목을 입력해주세요 : ");
				String inputTitle = sc.nextLine();
				
				System.out.print("내용을 입력해주세요 : ");
				String inputContent = sc.nextLine();
				
				postNumber.add(Number);
				postTitle.add(inputTitle);
				postContent.add(inputContent);
				
				Number++;
				
				System.out.println("게시물이 저장되었습니다.");
				System.out.println("=========================");
			}
			else if(ord.equals("list")) {
				for(int i=0; i<postNumber.size(); i++) {
					System.out.println("번호 : " + (postNumber.get(i) + 1));
					System.out.println("제목 : " + postTitle.get(i));
					System.out.println("내용 : " + postContent.get(i));
					System.out.println("=========================");
				}
			}
			else if(ord.equals("update")) {
				System.out.print("수정할 게시물 번호 : ");
				int inputNumber = Integer.parseInt(sc.nextLine());
				
				int searchNumber = -1;
				
				for(int i=0; i<postNumber.size(); i++) {
					if((inputNumber) == (postNumber.get(i) + 1)) {
						searchNumber = i;
					}
				}
				
				if(searchNumber == -1) {
					System.out.println("해당 게시물이 없습니다.");
				} else {
					System.out.print("새 제목 : ");
					postTitle.set(searchNumber, sc.nextLine());

					System.out.print("새 내용 : ");
					postContent.set(searchNumber, sc.nextLine());
					
					System.out.println("수정이 완료되었습니다.");
				}
				
				System.out.println("====================");
			}
		}
	}

}
