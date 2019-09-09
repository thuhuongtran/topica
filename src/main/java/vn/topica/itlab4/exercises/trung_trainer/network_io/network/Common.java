package vn.topica.itlab4.exercises.trung_trainer.network_io.network;

public class Common {
    protected final static int PORT_NUMBER = 9669;
    protected final static String HOST_NAME = "127.0.0.1";

    public enum CmdCode {
        AUTHEN((short) 0),
        INSERT((short) 1),
        COMMIT((short) 2),
        SELECT((short) 3),
        ERROR((short) 4);

        private short value;

        CmdCode(short value) {
            this.value = (short) value;
        }

        public short getValue() {
            return value;
        }

        public static CmdCode getKey(short value) {
            CmdCode cmdCode;
            switch (value) {
                case 0:
                    cmdCode = AUTHEN;
                    break;
                case 1:
                    cmdCode = INSERT;
                    break;
                case 2:
                    cmdCode = COMMIT;
                    break;
                case 3:
                    cmdCode = SELECT;
                    break;
                case 4:
                    cmdCode = ERROR;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + value);
            }
            return cmdCode;
        }
    }

    protected enum Tag {
        Key(0),
        PhoneNumber(1),
        Name(2),
        ResultCode(3);

        private short value;

        Tag(int value) {
            this.value = (short) value;
        }

        public short getValue() {
            return value;
        }

        public static Tag getKey(short value) {
            Tag tag;
            switch (value) {
                case 0:
                    tag = Key;
                    break;
                case 1:
                    tag = PhoneNumber;
                    break;
                case 2:
                    tag = Name;
                    break;
                case 3:
                    tag = ResultCode;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + value);
            }
            return tag;
        }
    }

    protected enum ResultCode {
        NA,
        OK,
        NOK
    }

    protected enum Status {
        INIT,
        READY,
        SELECT
    }
}
