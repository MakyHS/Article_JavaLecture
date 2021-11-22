package board;

public class Article {
	
	int id;
	String title;
	String body;
	String regDate;
	String writer;
	int hit;
	
	public Article(int id, String title, String body, String regDate, String writer, int hit) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.writer = writer;
		this.hit = hit;
	}
}
