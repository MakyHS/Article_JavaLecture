package board;

public class Article {
	
	int id;
	String title;
	String body;
	String regDate;
	int memberId;
	String nickname;
	int hit;
	
	public Article(int id, String title, String body, String regDate, int memberId, String nickname, int hit) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.memberId = memberId;
		this.nickname = nickname;
		this.hit = hit;
	}
	
}
