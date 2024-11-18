import java.io.*;

public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {

            String[] command = new String[]{"java", "-cp", "/home/aseixo/IdeaProjects/pspud2probapresencial15112024/out/production/pspud2probapresencial15112024/", "Server"};
            ProcessBuilder builder = new ProcessBuilder(command);
            Process server = builder.start();

            InputStreamReader streamReader = new InputStreamReader(server.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            OutputStreamWriter streamWriter = new OutputStreamWriter(server.getOutputStream());
            BufferedWriter writer = new BufferedWriter(streamWriter);

            String name = "heledna";
            String pwd = "abc123";
            writer.write(name.concat("\r\n"));
            writer.flush();
            writer.write(pwd.concat("\r\n"));
            writer.flush();
            writer.close();

            String serverRes = "NO";
            serverRes = reader.readLine();
            System.out.println(serverRes);
            reader.close();
            int res = server.waitFor();
    }
}
