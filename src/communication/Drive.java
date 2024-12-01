package communication;

import java.util.Arrays;

public class Drive{

	byte Direction;
	byte[] Steps;



	public Drive(byte direction, byte[] steps) {
		super();
		Direction = direction;
		Steps = steps;
	}

	public Drive() {
		super();
		Direction = (byte) 0x01;
		Steps = new byte[] {(byte) 0x01, (byte) 0xFF};
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Direction;
		result = prime * result + Arrays.hashCode(Steps);
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
		Drive other = (Drive) obj;
		if (Direction != other.Direction)
			return false;
		if (!Arrays.equals(Steps, other.Steps))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "" + Direction + Arrays.toString(Steps);
	}


}