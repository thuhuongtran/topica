package vn.topica.itlab4.exercises.trung_trainer.network_io.network;

import java.io.*;
import java.net.Socket;

public class MessageClient {
    private static MessageClient instance = new MessageClient();
    private MessageUtils messageUtils = MessageUtils.getInstance();

    private MessageClient() {
    }

    public static MessageClient getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        MessageClient.getInstance().listen();
    }

    protected void listen() {
        int count = 0;
        while (true) {
            System.out.println("-------Response-------------");
            System.out.println(String.format("Client. ID = %d", ++count));
            try (
                    Socket client = new Socket(Common.HOST_NAME, Common.PORT_NUMBER);
                    DataOutputStream out = new DataOutputStream(client.getOutputStream());
                    DataInputStream in = new DataInputStream(client.getInputStream());
            ) {

                Message receiveMsg = handle(in, out, Common.CmdCode.AUTHEN, null);
                String phone = messageUtils.getValueByTag(receiveMsg, Common.Tag.PhoneNumber);
                // insert if authen successfully
                if (messageUtils.getValueByTag(receiveMsg, Common.Tag.ResultCode)
                        .equals(Common.ResultCode.OK.name())) {
                    receiveMsg = handle(in, out, Common.CmdCode.INSERT, phone);
                }
                // commit if insert successfully
                if (messageUtils.getValueByTag(receiveMsg, Common.Tag.ResultCode)
                        .equals(Common.ResultCode.OK.name())) {
                    receiveMsg = handle(in, out, Common.CmdCode.COMMIT, phone);
                }
                // select if commit successfully
                if (messageUtils.getValueByTag(receiveMsg, Common.Tag.ResultCode)
                        .equals(Common.ResultCode.OK.name())) {
                    receiveMsg = handle(in, out, Common.CmdCode.SELECT, phone);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                break;
            }
        }
    }

    private byte[] getBytes(DataInputStream in) throws IOException {
        int msgLength = in.readUnsignedByte();
        byte[] inBytes = new byte[msgLength - 1];
        in.read(inBytes);
        return inBytes;
    }

    private Message handle(DataInputStream in, DataOutputStream out, Common.CmdCode cmdCode, String phone) throws IOException {
        byte[] sendBytes = new byte[0];
        if (cmdCode.equals(Common.CmdCode.AUTHEN)) {
            sendBytes = messageUtils.getBytes(messageUtils.createAuthenRequest());
        } else if (cmdCode.equals(Common.CmdCode.INSERT)) {
            sendBytes = messageUtils.getBytes(messageUtils.createInsertRequest(phone));
        } else if (cmdCode.equals(Common.CmdCode.COMMIT)) {
            sendBytes = messageUtils.getBytes(messageUtils.createCommitRequest(phone));
        } else if (cmdCode.equals(Common.CmdCode.SELECT)) {
            sendBytes = messageUtils.getBytes(messageUtils.createSelectRequest(phone));
        }
        out.write(sendBytes);
        byte[] inBytes = getBytes(in);
        Message receiveMsg = new Message();
        if (inBytes.length > 0) {
            receiveMsg = messageUtils.readBytes(inBytes);
            System.out.println(receiveMsg.toMessage());
        }
        return receiveMsg;
    }
}
