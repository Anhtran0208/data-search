public class Book{
	private String title;
	private String author;
	private int pageCount;
	private int yearPublished;
	private String genres;

	public Book(String tit, String auth, int pagec, int year, String gen){

		title = tit;
		author = auth;
		pageCount = pagec;
		yearPublished = year;
		genres = gen;
	}

	public String getTitle(){
		return title;
	}

	public String getAuthor(){
		return author;
	}

	public int getPageCount(){
		return pageCount;
	}

	public int getYearPublished(){
		return yearPublished;
	}

	public String getGenres(){
		return genres;
	}

	public String toString(){
		return "Title: "+title+"\n"+
		"Author: "+author+"\n"+
		"Page Count: "+pageCount+"\n"+
		"Year Published: "+yearPublished+"\n"+
		"Genres: "+genres+"\n";

	}



}
