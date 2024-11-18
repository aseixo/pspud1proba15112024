import java.io.*;
import java.util.Date;

public class Server {

    private  static final int MAX_USERS=3;
    private static User[] users = new User[MAX_USERS];
    private static final String LOG_FILE_NAME= "logpass.dat";

    public static void main(String[] args) throws IOException {

        populateUsers();
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(streamReader);

        OutputStreamWriter streamWriter = new OutputStreamWriter(System.out);
        BufferedWriter writer = new BufferedWriter(streamWriter);

        User user = new User();
        String name = reader.readLine();
        user.setName(name);
        String pwd = reader.readLine();
        user.setPwd(pwd);
        boolean allowed = getUser(user);
        logUser(user, allowed);
        writer.write(String.valueOf(allowed));
        writer.flush();
        writer.close();

    }

    private static void populateUsers(){
        User user0 = new User("paulo", "8901");
        users[0] = user0;
        User user1 = new User("helena", "abc123");
        users[1] = user1;
        User user2 = new User("camilo", "9876");
        users[2] = user2;
    }

    private static boolean getUser(User user){

        boolean allowed = false;
        for (User u: users){
            if (u.getName().equals(user.getName()) && u.getPwd().equals(user.getPwd()))
                allowed = true;
        }
        return allowed;
    }

    private static void browseUsers(){
        for (User u: users)
            System.out.println(u.getName().concat(" "). concat(u.getPwd()));
    }

    private static void logUser(User user, boolean allowed) throws IOException {
        FileWriter fileWriter = new FileWriter(LOG_FILE_NAME, true);
        String auth = "DENEGADO";
        if (allowed==true){
            auth = "PERMITIDO";
        }
        String res = user.getName().concat(" ").concat(new Date().toString()).concat(" ".concat(auth).concat("\r\n"));
        fileWriter.write(res);
        fileWriter.flush();
        fileWriter.close();

    }
}
