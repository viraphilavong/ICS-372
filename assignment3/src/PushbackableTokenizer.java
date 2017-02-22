
public interface PushbackableTokenizer {
	public String nextToken();
	public boolean hasMoreTokens();
	public void pushback();
}
