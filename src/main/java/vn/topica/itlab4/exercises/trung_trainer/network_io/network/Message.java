package vn.topica.itlab4.exercises.trung_trainer.network_io.network;

import java.util.List;

public class Message {
    private int messageLength;
    private Common.CmdCode cmdCode;
    private short version = 0;
    private List<Payload> payloads;

    public Message() {
    }

    public Message(Common.CmdCode cmdCode, List<Payload> payloads) {
        this.cmdCode = cmdCode;
        this.payloads = payloads;
        this.messageLength = getByteSize();
    }

    /**
     * Set message length
     * Length = (Header)CmdCode + (Payloads)Tag + Value
     */
    public void setMessageLength() {
        int msglength = this.cmdCode.name().length();
        for (Payload payload : payloads) {
            String tag = payload.getTag().name();
            msglength += tag.length() + payload.getLength();
        }
        this.messageLength = msglength;
    }

    protected int getByteSize() {
        int byteSize = 0;
        byteSize += 3; // 8 bytes for header
        for (Payload pl : payloads) {
            byteSize += 2; // 2 bytes for tag and 2 bytes for length
            byteSize += pl.getValue().getBytes().length;
        }
        return byteSize;
    }

    protected String toMessage() {
        String str = this.cmdCode.name();
        for (Payload pl : payloads) {
            str = str.concat(String.format(" %s %s"
                    , pl.getTag().name()
                    , pl.getValue()));
        }
        return str;
    }

    public List<Payload> getPayloads() {
        return payloads;
    }

    public void setPayloads(List<Payload> payloads) {
        this.payloads = payloads;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public short getVersion() {
        return version;
    }

    public void setVersion(short version) {
        this.version = version;
    }

    public Common.CmdCode getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(Common.CmdCode cmdCode) {
        this.cmdCode = cmdCode;
    }

    public void printPayload() {
        for (Payload pl : payloads) {
            System.out.println(pl.getTag() + " -" + pl.getLength() + " -" + pl.getValue());
        }
    }
}

class Payload {
    private Common.Tag tag;
    private short length;
    private String value;

    public Payload(Common.Tag tag, String value) {
        this.tag = tag;
        this.length = (short) value.length();
        this.value = value;
    }

    protected int getByteSize() {
        return 4 + getLength() * 2;
    }

    public Common.Tag getTag() {
        return tag;
    }

    public void setTag(Common.Tag tag) {
        this.tag = tag;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}


