package Index;

import java.util.HashMap;

public class Index {
	private double loadFactor;
	private Node[] index;
	private int counter;
	private int capacity;
	private int hashFunction;
	private int handling;

	private int collusions;

	public Index() {
		collusions = 0;
		handling = 1;
		hashFunction = 1;
		capacity = 127;
		counter = 0;
		loadFactor = 0.8;
		index = new Node[capacity];
	}

	public void put(String key, String value) {
		int position = getPosition(key);
		if (getLoadFactor() > loadFactor)
			resize();
		if (index[position] == null) {
			index[position] = new Node(key, value);
			counter++;
		} else {
			index[position].put(value);
		}
	}

	public void remove(String k) {
		int position = getPosition(k);
		if (index[position] == null)
			return;
		else {
			index[position] = null;
		}
	}

	public HashMap<String, Integer> get(String k) {
		return index[getPosition(k)].getValue();
	}

	public void resize() {
		int oldC = capacity;
		increaseCapacity();
		Node[] temp = new Node[capacity];
		for (int i = 0; i < oldC; i++) {
			temp[getHash(index[i].getKey())] = index[i];
		}
		index = temp;
	}

	private double getLoadFactor() {
		return counter / capacity;
	}

	public void increaseCapacity() {
		for (capacity *= 2; !isPrime(++capacity);)
			;
	}

	private boolean isPrime(int n) {
		for (int i = 2; i < n / 2; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public void useSSF() {
		hashFunction = 1;
	}

	public void usePAF() {
		hashFunction = 2;
	}

	public void useLP() {
		handling = 1;
	}

	public void useDH() {
		handling = 2;
	}

	public void setLoadFactor(double lf) {
		loadFactor = lf;
	}

	private int getHash(String k) {
		switch (hashFunction) {
		case 1:
			return ssf(k);
		case 2:
			return paf(k);
		}
		return -1;
	}

	private int getPosition(String k) {
		switch (handling) {
		case 1:
			return handleLP(k);
		case 2:
			return handleDH(k);
		}
		return -1;
	}

	public int getCollusionCount() {
		return collusions;
	}

	private int handleLP(String k) {
		int position=getHash(k);
		while(index[position] != null && !index[position].getKey().equals(k)) {
			position++;
		}
		return position;
	}

	private int handleDH(String k) {
		return 0;
	}

	private int ssf(String k) {
		int sum =0;
		for(int i=0;i<k.length();i++) {
			sum+=k.toCharArray()[i];
		}
		sum%=capacity;
		return sum;
	}

	private int paf(String k) {
		return 0;
	}
}
