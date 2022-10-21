package lexical;

import java.util.HashMap;
import java.util.Map;

public class SymbolTable {

    private Map<String, TokenType> st;

    public SymbolTable() {
        st = new HashMap<String, TokenType>();

        // SYMBOLS
        st.put(":", TokenType.COLON);
        st.put("[", TokenType.OPEN_BRA);
        st.put("]", TokenType.CLOSE_BRA);
        st.put("{", TokenType.OPEN_CUR);
        st.put("}", TokenType.CLOSE_CUR);
        st.put(",", TokenType.COMMA);
        st.put(".", TokenType.POINT);

        // OPERATORS

        // KEYWORDS
    }

    public boolean contains(String token) {
        return st.containsKey(token);
    }

    public TokenType find(String token) {
        return this.contains(token) ? st.get(token) : TokenType.NAME;
    }
}
