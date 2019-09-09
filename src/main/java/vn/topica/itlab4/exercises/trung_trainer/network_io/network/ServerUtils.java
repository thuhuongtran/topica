package vn.topica.itlab4.exercises.trung_trainer.network_io.network;

import java.util.List;
import java.util.regex.Pattern;

public class ServerUtils {
    private static ServerUtils instance = new ServerUtils();
    private MessageUtils messageUtils = MessageUtils.getInstance();

    private ServerUtils() {

    }

    public static ServerUtils getInstance() {
        return instance;
    }

    public Message checkPhoneNumber(String phoneNumber) {
        if (!phoneNumber.startsWith("098")
                || !Pattern.matches("\\d{10}", phoneNumber)
                || phoneNumber.charAt(3) == '0'
                || phoneNumber.charAt(3) == '1') {
            return messageUtils.createErrorResponse();
        }
        return null;
    }

    protected Message checkAuthen(Message msg, String phoneNumber) {
        String key = messageUtils.getValueByTag(msg, Common.Tag.Key);
        if (key.equals("topica")) {
            return messageUtils.createAuthenResponse(phoneNumber, Common.ResultCode.OK);
        } else {
            return messageUtils.createAuthenResponse(phoneNumber, Common.ResultCode.NOK);
        }
    }

    protected Message checkInsert(String phoneNumber, Common.Status status, List<User> users) {
        if (status.equals(Common.Status.READY)) {
            Message msg = messageUtils.createInsertResponse(phoneNumber,
                    Common.ResultCode.OK);
            users.add(new User(messageUtils.getValueByTag(msg, Common.Tag.PhoneNumber),
                    messageUtils.getValueByTag(msg, Common.Tag.Name)));
            return msg;
        } else {
            return messageUtils.createInsertResponse(phoneNumber,
                    Common.ResultCode.NOK);
        }
    }

    protected Message checkCommit(String phone) {
        return messageUtils.createCommitResponse(phone, Common.ResultCode.OK);
    }

    protected Message checkSelect(Common.Status status, String phone, Message msg) {
        if (status.equals(Common.Status.SELECT)) {
            return messageUtils.createSelectResponse(phone,
                    messageUtils.getValueByTag(msg, Common.Tag.Name));
        } else {
            return messageUtils.createSelectResponse(phone);
        }
    }
}
