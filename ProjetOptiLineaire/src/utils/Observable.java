package utils;

public interface Observable {
	public void notifyObserver(String msg);
	public void addObserver(Observer observer);
	public void removeObserver(Observer observer);
}
