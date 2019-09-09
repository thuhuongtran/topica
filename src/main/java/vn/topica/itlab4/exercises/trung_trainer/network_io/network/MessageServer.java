package vn.topica.itlab4.exercises.trung_trainer.network_io.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MessageServer extends Thread {
    private static MessageServer instance = new MessageServer();
    private MessageUtils messageUtils = MessageUtils.getInstance();
    private ServerUtils serverUtils = ServerUtils.getInstance();
    private List<User> users = new ArrayList<>();
    private boolean serverOn = true;

    private MessageServer() {
    }

    public static MessageServer getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        MessageServer.getInstance().listen();
    }

    protected void listen() {
        try (ServerSocket serverSocket = new ServerSocket(Common.PORT_NUMBER)) {
            System.out.println("Server started.");
            int count = 0;
            while (serverOn) {
                Socket socket = serverSocket.accept();
                Calendar now = Calendar.getInstance();
                System.out.println("-------Request-------------");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                System.out.println("It's now: " + sdf.format(now.getTime()));
                System.out.println(String.format("Connected client. ID = %d", ++count));
                ServerThread thread = new ServerThread(socket);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ServerThread extends Thread {
        private Socket socket;
        private Common.Status status = Common.Status.INIT;
        private User user;

        public ServerThread(Socket socket) {
            this.socket = socket;
            user = new User();
        }

        private byte[] getBytes(DataInputStream in) throws IOException {
            int msgLength = in.readUnsignedByte();
            byte[] inBytes = new byte[msgLength - 1];
            in.read(inBytes);
            return inBytes;
        }

        private Message handle(DataInputStream in, DataOutputStream out, Common.CmdCode cmdCode) throws IOException {
            byte[] inBytes = getBytes(in);
            Message msg = messageUtils.readBytes(inBytes);
            System.out.println(msg.toMessage());
            String phoneNumber = messageUtils.getValueByTag(msg, Common.Tag.PhoneNumber);
            Message returnMsg = new Message();
            if (cmdCode.equals(Common.CmdCode.INSERT)) {
                returnMsg = serverUtils.checkInsert(phoneNumber, status, users);
            } else if (cmdCode.equals(Common.CmdCode.COMMIT)) {
                returnMsg = serverUtils.checkCommit(phoneNumber);
            } else if (cmdCode.equals(Common.CmdCode.SELECT)) {
                returnMsg = serverUtils.checkSelect(status, phoneNumber, msg);
            }
            out.write(messageUtils.getBytes(returnMsg));
            return returnMsg;
        }

        @Override
        public void run() {
            try (
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ) {
                byte[] inBytes = getBytes(in);
                Message msg = messageUtils.readBytes(inBytes);
                System.out.println(msg.toMessage());
                String phoneNumber = messageUtils.getValueByTag(msg, Common.Tag.PhoneNumber);
                Message returnMsg = serverUtils.checkPhoneNumber(phoneNumber);
                // check authen
                if (returnMsg == null) {
                    returnMsg = serverUtils.checkAuthen(msg, phoneNumber);
                }
                out.write(messageUtils.getBytes(returnMsg));
                // check insert
                if (messageUtils.getValueByTag(returnMsg, Common.Tag.ResultCode)
                        .equals(Common.ResultCode.OK.name())) {
                    status = Common.Status.READY;
                    returnMsg = handle(in, out, Common.CmdCode.INSERT);
                }
                // commit if insert successfully
                if (messageUtils.getValueByTag(returnMsg, Common.Tag.ResultCode)
                        .equals(Common.ResultCode.OK.name())) {
                    returnMsg = handle(in, out, Common.CmdCode.COMMIT);
                    status = Common.Status.SELECT;
                }
                // select if commit successfully
                if (messageUtils.getValueByTag(returnMsg, Common.Tag.ResultCode)
                        .equals(Common.ResultCode.OK.name())) {
                    returnMsg = handle(in, out, Common.CmdCode.SELECT);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
