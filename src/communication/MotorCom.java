package communication;

import java.util.Arrays;

public class MotorCom {

	private	byte Msg_ID;
	private	byte Cmd;
	private	byte msgType;
	private byte[] crc;
	private	byte Fin;
	private	Object data;



public MotorCom(byte msg_ID, byte cmd, byte type, byte[] crc, byte fin, Object data) {
		super();
		Msg_ID = msg_ID;
		Cmd = cmd;
		msgType = type;
		this.crc = crc;
		Fin = fin;
		this.data = data;
	}







@Override
public String toString() {
	return "" + Msg_ID + Cmd + msgType  + crc[0] + crc[1] + Fin + data;
}







@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Cmd;
	result = prime * result + Fin;
	result = prime * result + Msg_ID;
	result = prime * result + Arrays.hashCode(crc);
	result = prime * result + ((data == null) ? 0 : data.hashCode());
	result = prime * result + msgType;
	return result;
}



@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	MotorCom other = (MotorCom) obj;
	if (Cmd != other.Cmd)
		return false;
	if (Fin != other.Fin)
		return false;
	if (Msg_ID != other.Msg_ID)
		return false;
	if (!Arrays.equals(crc, other.crc))
		return false;
	if (data == null) {
		if (other.data != null)
			return false;
	} else if (!data.equals(other.data))
		return false;
	if (msgType != other.msgType)
		return false;
	return true;
}



}
