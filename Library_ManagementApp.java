package Library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Library_ManagementApp {

    private JFrame welcome_screen, addBook, removeBook, issueBook, viewBook , viewStudents;
    private JTextField bookName, bookId,bookName2, bookId2,bookName3, bookId3, studentName, regNo;
    private JButton add_book, remove_book, issue_book, view_book , view_student;


    public Library_ManagementApp(){
        createWelcomeScreen();
        createAddBookScreen();
        createIssueBookScreen();
        createRemoveBookScreen();
    }

    public void createWelcomeScreen(){
        welcome_screen = new JFrame("Online Library Management System");
        welcome_screen.setSize(800,700);
        welcome_screen.setLocationRelativeTo(null);
        welcome_screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel welcomePanel = new JPanel(new GridLayout(8,2));
        welcomePanel.setBackground(new Color(249,174,76));
        JLabel welcomeLabel = new JLabel("Welcome to Library Management",SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Comic Sans",Font.BOLD,24));
        welcomePanel.add(welcomeLabel);

        add_book = new JButton("Add Book");
        welcomePanel.add(add_book);
        add_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome_screen.setVisible(false);
                addBook.setVisible(true);
            }
        });
        issue_book = new JButton("Issue/Return Book");
        welcomePanel.add(issue_book);
        issue_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome_screen.setVisible(false);
                issueBook.setVisible(true);
            }
        });
        remove_book = new JButton("Remove Book");
        welcomePanel.add(remove_book);
        remove_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome_screen.setVisible(false);
                removeBook.setVisible(true);
            }
        });
        view_book = new JButton("View Book");
        welcomePanel.add(view_book);
        view_book.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome_screen.setVisible(false);
                createViewBookScreen();
                viewBook.setVisible(true);
            }
        });
        view_student = new JButton("View Students");
        welcomePanel.add(view_student);
        view_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome_screen.setVisible(false);
                createViewStudentScreen();
                viewStudents.setVisible(true);
            }
        });

        add_book.setBackground(new Color(255,208,145));
        issue_book.setBackground(new Color(255,208,145));
        remove_book.setBackground(new Color(255,208,145));
        view_book.setBackground(new Color(255,208,145));
        view_student.setBackground(new Color(255,208,145));

        welcome_screen.add(welcomePanel);
        welcome_screen.setVisible(true);

    }

    public void createAddBookScreen(){
        addBook = new JFrame("Add Book");
        addBook.setSize(800,700);
        addBook.setLocationRelativeTo(null);
        addBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel addBookPanel = new JPanel(new GridLayout(8,2));
        addBookPanel.setBackground(new Color(255,204,204));
        JLabel Label1 = new JLabel("Adding Book",SwingConstants.CENTER);
        Label1.setFont(new Font("Comic Sans",Font.BOLD,24));
        addBookPanel.add(Label1);

        JLabel bookname = new JLabel("Enter Book Name:");
        bookName = new JTextField();
        addBookPanel.add(bookname);
        addBookPanel.add(bookName);

        JLabel bookid = new JLabel("Enter Book ID:");
        bookId = new JTextField();
        addBookPanel.add(bookid);
        addBookPanel.add(bookId);

        JButton Add = new JButton("Add Book");
        addBookPanel.add(Add);

        JLabel complete = new JLabel("");
        addBookPanel.add(complete);

        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    add_Book();
                    complete.setText("Book added successfully");
                    bookName.setText("");
                    bookId.setText("");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {//to remove the text after some delay
                        @Override
                        public void run() {
                            complete.setText("");
                        }
                    }, 3000);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        JButton back = new JButton("Go Back");
        addBookPanel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook.setVisible(false);
                welcome_screen.setVisible(true);
                complete.setText("");
            }
        });
        Add.setBackground(new Color(255,153,153));
        back.setBackground(new Color(255,153,153));
        addBook.add(addBookPanel);
    }

    public void createIssueBookScreen(){
        issueBook = new JFrame("Issue Book");
        issueBook.setSize(800,700);
        issueBook.setLocationRelativeTo(null);
        issueBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel issueBookPanel = new JPanel(new GridLayout(13,2));
        issueBookPanel.setBackground(new Color(229,204,255));
        JLabel Label1 = new JLabel("Issuing Book",SwingConstants.CENTER);
        Label1.setFont(new Font("Comic Sans",Font.BOLD,24));
        issueBookPanel.add(Label1);

        JLabel bookname = new JLabel("Enter Book Name:");
        bookName2 = new JTextField();
        issueBookPanel.add(bookname);
        issueBookPanel.add(bookName2);

        JLabel bookid = new JLabel("Enter Book ID:");
        bookId2 = new JTextField();
        issueBookPanel.add(bookid);
        issueBookPanel.add(bookId2);

        JLabel name = new JLabel("Enter Student Name:");
        studentName = new JTextField();
        issueBookPanel.add(name);
        issueBookPanel.add(studentName);

        JLabel reg_no = new JLabel("Enter Student Registration No:");
        regNo = new JTextField();
        issueBookPanel.add(reg_no);
        issueBookPanel.add(regNo);

        JButton Issue = new JButton("Issue Book");
        JLabel complete = new JLabel("");
        issueBookPanel.add(Issue);
        Timer timer = new Timer();
        Issue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(issue_Book()){
                    complete.setText("Book issued successfully");
                    bookName2.setText("");
                    bookId2.setText("");
                    studentName.setText("");
                    regNo.setText("");
                    timer.schedule(new TimerTask() {//to remove the text after some delay
                        @Override
                        public void run() {
                            complete.setText("");
                        }
                    }, 3000);
                }
                else{
                    complete.setText("Book not available / Cannot issue!");
                    timer.schedule(new TimerTask() {//to remove the text after some delay
                        @Override
                        public void run() {
                            complete.setText("");
                        }
                    }, 3000);
                }
            }
        });

        JButton Return = new JButton("Return Book");
        issueBookPanel.add(Return);

        issueBookPanel.add(complete);
        Return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(return_Book()){
                    complete.setText("Book returned successfully");
                    bookName2.setText("");
                    bookId2.setText("");
                    studentName.setText("");
                    regNo.setText("");
                    timer.schedule(new TimerTask() {//to remove the text after some delay
                        @Override
                        public void run() {
                            complete.setText("");
                        }
                    }, 3000);
                }
                else{
                    complete.setText("Book not Issued / Wrong student info!");
                    timer.schedule(new TimerTask() {//to remove the text after some delay
                        @Override
                        public void run() {
                            complete.setText("");
                        }
                    }, 3000);
                }
            }
        });

        JButton back = new JButton("Go Back");
        issueBookPanel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                issueBook.setVisible(false);
                welcome_screen.setVisible(true);
            }
        });
        Issue.setBackground(new Color(204,153,255));
        Return.setBackground(new Color(204,153,255));
        back.setBackground(new Color(204,153,255));
        issueBook.add(issueBookPanel);
    }

    public void createRemoveBookScreen(){
        removeBook = new JFrame("Remove Book");
        removeBook.setSize(800,700);
        removeBook.setLocationRelativeTo(null);
        removeBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel removeBookPanel = new JPanel(new GridLayout(8,2));
        removeBookPanel.setBackground(new Color(204,229,255));
        JLabel Label1 = new JLabel("Removing Book",SwingConstants.CENTER);
        Label1.setFont(new Font("Comic Sans",Font.BOLD,24));
        removeBookPanel.add(Label1);

        JLabel bookname = new JLabel("Enter Book Name:");
        bookName3 = new JTextField();
        removeBookPanel.add(bookname);
        removeBookPanel.add(bookName3);

        JLabel bookid = new JLabel("Enter Book ID:");
        bookId3 = new JTextField();
        removeBookPanel.add(bookid);
        removeBookPanel.add(bookId3);

        JButton Remove = new JButton("Remove Book");
        removeBookPanel.add(Remove);

        JLabel complete = new JLabel("");
        removeBookPanel.add(complete);

        Remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(remove_Book()) {
                    bookName3.setText("");
                    bookId3.setText("");
                    complete.setText("Removed the book successfully!");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {//to remove the text after some delay
                        @Override
                        public void run() {
                            complete.setText("");
                        }
                    }, 3000);
                }
                else if (!remove_Book()){
                    complete.setText("No such book found!");
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {//to remove the text after some delay
                        @Override
                        public void run() {
                            complete.setText("");
                        }
                    }, 3000);
                }
            }
        });

        JButton back = new JButton("Go Back");
        removeBookPanel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBook.setVisible(false);
                welcome_screen.setVisible(true);
                complete.setText("");
            }
        });
        Remove.setBackground(new Color(153,204,255));
        back.setBackground(new Color(153,204,255));
        removeBook.add(removeBookPanel);
    }

    public void createViewBookScreen(){
        viewBook = new JFrame("View Books");
        viewBook.setSize(800,700);
        viewBook.setLayout(new BorderLayout());
        viewBook.setLocationRelativeTo(null);
        viewBook.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel viewBookPanel = new JPanel();
        viewBookPanel.setBackground(new Color(204,255,204));
        viewBookPanel.setLayout(new BoxLayout(viewBookPanel,BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(viewBookPanel);//makes the panel scrolable

        JLabel Label1 = new JLabel("Viewing Books");
        Label1.setFont(new Font("Comic Sans",Font.BOLD,24));
        viewBookPanel.add(Label1);

        JLabel label3 = new JLabel("Book ID"+"  "+"Book Name");
        label3.setFont(new Font("Comic Sans",Font.PLAIN,17));
        viewBookPanel.add(label3, BorderLayout.CENTER);

        JLabel label2 = new JLabel("<html>" + view_Books() + "</html>");//wrapping the text in html format to use <br>
        label2.setFont(new Font("Comic Sans",Font.PLAIN,20));
        viewBookPanel.add(label2, BorderLayout.CENTER);

        JButton back = new JButton("Go Back");
        viewBookPanel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome_screen.setVisible(true);
                viewBook.dispose();//destroys the frame
            }
        });
        back.setBackground(new Color(153,255,153));
        viewBook.add(scrollPane, BorderLayout.CENTER);

    }
    public void createViewStudentScreen(){
        viewStudents = new JFrame("View Students");
        viewStudents.setSize(800,700);
        viewStudents.setLayout(new BorderLayout());
        viewStudents.setLocationRelativeTo(null);
        viewStudents.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel viewStudentPanel = new JPanel();
        viewStudentPanel.setBackground(new Color(204,204,255));
        viewStudentPanel.setLayout(new BoxLayout(viewStudentPanel,BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(viewStudentPanel);//makes the panel scrolable

        JLabel Label1 = new JLabel("Viewing Students");
        Label1.setFont(new Font("Comic Sans",Font.BOLD,24));
        viewStudentPanel.add(Label1);

        JLabel label3 = new JLabel("Reg No."+"  "+"Student Name");
        label3.setFont(new Font("Comic Sans",Font.PLAIN,17));
        viewStudentPanel.add(label3, BorderLayout.CENTER);

        JLabel label2 = new JLabel("<html>" + view_Students() + "</html>");//wrapping the text in html format to use <br>
        label2.setFont(new Font("Comic Sans",Font.PLAIN,20));
        viewStudentPanel.add(label2, BorderLayout.CENTER);

        JButton back = new JButton("Go Back");
        viewStudentPanel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                welcome_screen.setVisible(true);
                viewStudents.dispose();//destroys the frame
            }
        });
        back.setBackground(new Color(153,153,255));
        viewStudents.add(scrollPane, BorderLayout.CENTER);

    }

    public void add_Book() throws IOException {
        String bookname = bookName.getText();
        String bookid = bookId.getText();

        appendToFile("C:\\Users\\Admin\\OneDrive\\Desktop\\coding\\Java\\OOPs\\src\\Library\\Books.txt",bookid+"  "+bookname);
    }

    public boolean remove_Book(){

        String bookname = bookName3.getText();
        String bookid = bookId3.getText();
        try{
            Path path = Paths.get("C:\\Users\\Admin\\OneDrive\\Desktop\\coding\\Java\\OOPs\\src\\Library\\Books.txt");
            List<String> lines = Files.readAllLines(path);//reads all the lines of file and turns it into list
            boolean check = lines.contains(bookid+"  "+bookname);//checks if the line exist in file
            if (check) {
                List<String> updatedLines = lines.stream().filter(line -> !line.equals(bookid + "  " + bookname)).toList();//removes the crtain line
                Files.write(path, updatedLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);//updates the file with removed line
                return true;
            }
            else{
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String view_Books(){
        try{
            Path path = Paths.get("C:\\Users\\Admin\\OneDrive\\Desktop\\coding\\Java\\OOPs\\src\\Library\\Books.txt");
            List<String> books = Files.readAllLines(path);
            String availabeBooks = String.join("<br>",books);//turing list into string with <br> to add lines after each element
            return availabeBooks;
        }
        catch (IOException e) {
            System.out.println("error in createViewBookScreen");
            return "error";
        }
    }

    public boolean issue_Book(){
        String bookname = bookName2.getText();
        String bookid = bookId2.getText();
        String studentname = studentName.getText();
        String regno = regNo.getText();

        try{
            Path path = Paths.get("C:\\Users\\Admin\\OneDrive\\Desktop\\coding\\Java\\OOPs\\src\\Library\\Books.txt");
            List<String> lines = Files.readAllLines(path);
            boolean checkbook = lines.contains(bookid+"  "+bookname);

            Path path2 = Paths.get("C:\\Users\\Admin\\OneDrive\\Desktop\\coding\\Java\\OOPs\\src\\Library\\Students.txt");

            if(checkbook){
                List<String> updatedLines = lines.stream().map(line -> line.equals(bookid + "  " + bookname) ? (bookid+"  "+bookname+"  --issued") : line).toList();//replaces the crtain line
                Files.write(path, updatedLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);//updates the file with updated line
                    appendToFile("C:\\Users\\Admin\\OneDrive\\Desktop\\coding\\Java\\OOPs\\src\\Library\\Students.txt", regno + "  " + studentname+"  --> Book Issued --> "+bookid + "  " + bookname);

                return true;
            }
            else{
                return false;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean return_Book(){
        String bookname = bookName2.getText();
        String bookid = bookId2.getText();
        String studentname = studentName.getText();
        String regno = regNo.getText();

        try{
            Path path = Paths.get("C:\\Users\\Admin\\OneDrive\\Desktop\\coding\\Java\\OOPs\\src\\Library\\Books.txt");
            List<String> lines = Files.readAllLines(path);
            boolean check = lines.contains(bookid+"  "+bookname+"  --issued");

            Path path2 = Paths.get("C:\\Users\\Admin\\OneDrive\\Desktop\\coding\\Java\\OOPs\\src\\Library\\Students.txt");
            List<String> lines2 = Files.readAllLines(path2);
            boolean checkstudents = lines2.contains(regno + "  " + studentname+"  --> Book Issued --> "+bookid + "  " + bookname);

            if(check && checkstudents){
                List<String> updatedLines = lines.stream().map(line -> line.equals(bookid+"  "+bookname+"  --issued") ? (bookid+"  "+bookname) : line).toList();//replaces the certain line
                Files.write(path, updatedLines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);//updates the file with updated line
                List<String> updatedLines2 = lines2.stream().filter(line -> !line.equals(regno + "  " + studentname+"  --> Book Issued --> "+bookid + "  " + bookname)).toList();//removes the crtain line
                Files.write(path2, updatedLines2, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
                return true;
            }
            else{
                return false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String view_Students(){
        try{
            Path path = Paths.get("C:\\Users\\Admin\\OneDrive\\Desktop\\coding\\Java\\OOPs\\src\\Library\\Students.txt");
            List<String> students = Files.readAllLines(path);
            String availabeStudents = String.join("<br>",students);//turing list into string with <br> to add lines after each element
            return availabeStudents;
        }
        catch (IOException e) {
            System.out.println("error in createViewBookScreen");
            return "error";
        }
    }

    public void appendToFile(String filePath, String content) {
        //adds the new books in file without deleting the old data
        FileWriter fileWriter = null;
        try {
            // true means append mode
            fileWriter = new FileWriter(filePath, true);
            fileWriter.write(content);
            fileWriter.write(System.lineSeparator()); // To add a new line after the appended content
        } catch (IOException e) {
            System.out.println(e);;
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Library_ManagementApp l1 = new Library_ManagementApp();

            }
        });
    }

}
