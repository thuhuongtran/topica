package vn.topica.itlab4.exercises.trung_trainer.network_io.network;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MessageUtils {
    private static MessageUtils instance = new MessageUtils();

    private MessageUtils() {
    }

    public static MessageUtils getInstance() {
        return instance;
    }

    private Random ran = new Random();

    protected String getValueByTag(Message msg, Common.Tag tag) {
        for (Payload pl : msg.getPayloads()) {
            if (pl.getTag().equals(tag)) {
                return pl.getValue();
            }
        }
        return "";
    }

    public byte[] getBytes(Message msg) {
        int byteSize = msg.getByteSize();
        ByteBuffer message = ByteBuffer.allocate(byteSize)
                .put((byte) msg.getMessageLength())
                .put((byte) msg.getCmdCode().getValue())
                .put((byte) msg.getVersion());
        for (Payload pl : msg.getPayloads()) {
            message.put((byte) pl.getTag().getValue())
                    .put((byte) pl.getLength())
                    .put(pl.getValue().getBytes());
        }
        return message.array();
    }

    public Message readBytes(byte[] receivedBytes) {
        int msgLength = receivedBytes.length;
        int i = 0;
        int temp = msgLength;
        Common.CmdCode cmdCode = Common.CmdCode.getKey((short) receivedBytes[i++]);
        short version = (short) receivedBytes[i++];
        temp -= 3;
        List<Payload> payloads = new ArrayList<>();
        while (temp > 0) {
            Common.Tag tag = Common.Tag.getKey((short) receivedBytes[i++]);
            short length = (short) receivedBytes[i++];
            String value = new String(receivedBytes, i, length);
            i += length;
            temp = temp - 2 - length;
            Payload pl = new Payload(tag, value);
            payloads.add(pl);
        }
        Message msg = new Message(cmdCode, payloads);
        return msg;
    }

    public Message createAuthenRequest() {
        List<Payload> payloads = new ArrayList<>();
        String phone = String.format("098%d", ran.nextInt(9999999));
        payloads.add(new Payload(Common.Tag.PhoneNumber, phone));
        int key = ran.nextInt(100);
        int tmp = ran.nextInt(5);
        if (tmp == 1 || tmp == 3 || tmp == 4 || tmp == 5) {
            payloads.add(new Payload(Common.Tag.Key, "topica"));
        } else
            payloads.add(new Payload(Common.Tag.Key,
                    String.format("topica%d", key)));
        Message msg = new Message(Common.CmdCode.AUTHEN, payloads);
        return msg;
    }

    public Message createInsertRequest(String phone) {
        List<Payload> payloads = new ArrayList<>();
        payloads.add(new Payload(Common.Tag.PhoneNumber, phone));
        int key = ran.nextInt(100);
        payloads.add(new Payload(Common.Tag.Name,
                String.format("name%d", key)));
        Message msg = new Message(Common.CmdCode.INSERT, payloads);
        return msg;
    }

    protected Message createCommitRequest(String phone) {
        List<Payload> payloads = new ArrayList<>();
        payloads.add(new Payload(Common.Tag.PhoneNumber, phone));
        Message msg = new Message(Common.CmdCode.COMMIT, payloads);
        return msg;
    }

    protected Message createSelectRequest(String phone) {
        List<Payload> payloads = new ArrayList<>();
        payloads.add(new Payload(Common.Tag.PhoneNumber, phone));
        Message msg = new Message(Common.CmdCode.SELECT, payloads);
        return msg;
    }

    protected Message createErrorResponse() {
        List<Payload> payloads = new ArrayList<>();
        payloads.add(new Payload(Common.Tag.ResultCode, Common.ResultCode.NA.name()));
        Message msg = new Message(Common.CmdCode.ERROR, payloads);
        return msg;
    }

    protected Message createAuthenResponse(String phone, Common.ResultCode resultCode) {
        List<Payload> payloads = new ArrayList<>();
        payloads.add(new Payload(Common.Tag.PhoneNumber, phone));
        payloads.add(new Payload(Common.Tag.ResultCode, resultCode.name()));
        Message msg = new Message(Common.CmdCode.AUTHEN, payloads);
        return msg;
    }

    protected Message createInsertResponse(String phone, Common.ResultCode resultCode) {
        List<Payload> payloads = new ArrayList<>();
        payloads.add(new Payload(Common.Tag.PhoneNumber, phone));
        payloads.add(new Payload(Common.Tag.ResultCode, resultCode.name()));
        Message msg = new Message(Common.CmdCode.INSERT, payloads);
        return msg;
    }

    protected Message createCommitResponse(String phone, Common.ResultCode resultCode) {
        List<Payload> payloads = new ArrayList<>();
        payloads.add(new Payload(Common.Tag.PhoneNumber, phone));
        payloads.add(new Payload(Common.Tag.ResultCode, resultCode.name()));
        Message msg = new Message(Common.CmdCode.COMMIT, payloads);
        return msg;
    }

    protected Message createSelectResponse(String phone) {
        List<Payload> payloads = new ArrayList<>();
        payloads.add(new Payload(Common.Tag.PhoneNumber, phone));
        payloads.add(new Payload(Common.Tag.ResultCode, Common.ResultCode.NOK.name()));
        Message msg = new Message(Common.CmdCode.SELECT, payloads);
        return msg;
    }

    protected Message createSelectResponse(String phone, String name) {
        List<Payload> payloads = new ArrayList<>();
        payloads.add(new Payload(Common.Tag.PhoneNumber, phone));
        payloads.add(new Payload(Common.Tag.ResultCode, Common.ResultCode.OK.name()));
        payloads.add(new Payload(Common.Tag.Name, name));
        Message msg = new Message(Common.CmdCode.SELECT, payloads);
        return msg;
    }
}

