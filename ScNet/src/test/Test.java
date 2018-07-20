package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import Model.*;
import Service.*;

public class Test {

	/**
	 * Post will be created for the last user performed action (register/ deActivate/ reActivate)
	 * Comment will be created for the last post submitted
	 * 6 options
	 * 1 - register user
	 * 2 - de-Activate user
	 * 3 - re-Activate user
	 * 4 - submitPost
	 * 5 - submitComment
	 * 6 - to retrieve and print user
	 * 0 - exit;
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = null;
		Post post = null;
		String input = "-1";
		Comment comment = null;
		String name = null;
		String email = null;
		String pwd = null;
		String postText = null;
		String commentText = null;
		Test t = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ISocialNetwork scNet = new SocialNetworkService();
		while (!input.equals("0")) {
			System.out.println("Please select an option below");
			System.out.println("1 - register user");
			System.out.println("2 - de-Activate user");
			System.out.println("3 - re-Activate user");
			System.out.println("4 - submitPost");
			System.out.println("5 - submitComment");
			System.out.println("6 - retrieve and print User");
			System.out.println("0 - exit");
			try {
				input = br.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			switch(input) {
			case "0": break;
			case "1": 
				try {
					System.out.println("Enter Name: ");
					name = br.readLine();
					System.out.println("Enter Email: ");
					email = br.readLine();
					System.out.println("Enter Password: ");
					pwd = br.readLine();
					user = new User(email,name,pwd);
					user = scNet.registerUser(user);
					user = scNet.getUser(user.getEmail(), 0);
					
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			case "2":
				if(user == null) {System.out.println("No user selected"); break;}
				System.out.println(scNet.deActivateUser(user));
				break;
			case "3":
				if(user == null) {System.out.println("No user selected"); break;}
				System.out.println(scNet.reActivateUser(user));
				break;
			case "4":
				if(user == null) {System.out.println("No user selected"); break;}
				
				try {
					System.out.println("Enter Post: ");
					postText = br.readLine();
					post = new Post(user, postText);
					post = scNet.submitPost(post);
					user.addPost(post);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "5":
				if(post == null) {System.out.println("No post selected"); break;}
				try {
					System.out.println("Enter Comment: ");
					commentText = br.readLine();
					comment = new Comment(user, post, commentText);
					comment = scNet.commentPost(comment);
					post.addComment(comment);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case "6":
				try {
					System.out.println("Enter Email: ");
					email = br.readLine();
					user = scNet.getUser(email, 0);
					t= new Test();
					t.printUser(user);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	private void printUser(User user) {
		if(user != null) {
			System.out.print("User: \r\n" + user.toString());
			System.out.println("\r\nPost:");
			for(Post p : user.getPost()) {
				System.out.println(p.toString());
				System.out.println("\r\nComment:");
				for(Comment c : p.getComment()) {
					System.out.println(c.toString());
				}
			}
		}
	}
}
