package board;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("<�Խ��� ���α׷�>");
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> postNumber = new ArrayList<>();
		ArrayList<String> postTitle = new ArrayList<>();
		ArrayList<String> postContent = new ArrayList<>();
		int Number = 1;
		
		System.out.println("");
		
		while(true) {
			System.out.print("��ɾ �Է����ּ��� : ");
			String ord = sc.nextLine();
			
			System.out.println("");
			
			if(ord.equals("add")) {
				System.out.print("������ �Է����ּ��� : ");
				String inputTitle = sc.nextLine();
				
				System.out.print("������ �Է����ּ��� : ");
				String inputContent = sc.nextLine();
				
				postNumber.add(Number);
				postTitle.add(inputTitle);
				postContent.add(inputContent);
				
				Number++;
				
				System.out.println("�Խù��� ����Ǿ����ϴ�.");
				System.out.println("=========================");
			}
			else if(ord.equals("list")) {
				for(int i=0; i<postNumber.size(); i++) {
					System.out.println("��ȣ : " + (postNumber.get(i) + 1));
					System.out.println("���� : " + postTitle.get(i));
					System.out.println("���� : " + postContent.get(i));
					System.out.println("=========================");
				}
			}
			else if(ord.equals("update")) {
				System.out.print("������ �Խù� ��ȣ : ");
				int inputNumber = Integer.parseInt(sc.nextLine());
				
				int searchNumber = -1;
				
				for(int i=0; i<postNumber.size(); i++) {
					if((inputNumber) == (postNumber.get(i) + 1)) {
						searchNumber = i;
					}
				}
				
				if(searchNumber == -1) {
					System.out.println("�ش� �Խù��� �����ϴ�.");
				} else {
					System.out.print("�� ���� : ");
					postTitle.set(searchNumber, sc.nextLine());

					System.out.print("�� ���� : ");
					postContent.set(searchNumber, sc.nextLine());
					
					System.out.println("������ �Ϸ�Ǿ����ϴ�.");
				}
				
				System.out.println("====================");
			}
		}
	}

}
