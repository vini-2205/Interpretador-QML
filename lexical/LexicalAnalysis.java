package lexical;

import java.io.FileInputStream;
import java.io.PushbackInputStream;

public class LexicalAnalysis implements AutoCloseable {

    private int line;
    private SymbolTable st;
    private PushbackInputStream input;

    public LexicalAnalysis(String filename) {
        try {
            input = new PushbackInputStream(new FileInputStream(filename), 2);
        } catch (Exception e) {
            throw new LexicalException("Unable to open file");
        }

        st = new SymbolTable();
        line = 1;
    }

    public void close() {
        try {
            input.close();
        } catch (Exception e) {
            throw new LexicalException("Unable to close file");
        }
    }

    public int getLine() {
        return this.line;
    }

    public Lexeme nextToken() {
        Lexeme lex = new Lexeme("", TokenType.END_OF_FILE);

        int state = 1;
        while (state != 5 && state != 6) {
            int c = getc();

            switch (state) {
                case 1:
                    if (c == ' ' || c == '\t' || c == '\r') {
                        state = 1;
                    } else if (c == '\n') {
                        this.line++;
                    }else if(c == ',' || c == ':' || c == '{' ||
                                c == '}' || c == '[' || c == ']'){
                        lex.token += (char) c;
                        state = 5;
                    }else if( Character.isLetter(c)){
                        lex.token += (char) c;
                        state = 2;
                    }else if( Character.isDigit(c)){
                        lex.token += (char) c;
                        state = 3;
                    }else if(c == '"'){
                        state = 4;
                    }else if (c == -1) {
                        lex.type = TokenType.END_OF_FILE;
                        state = 6;
                    } else {
                        lex.token += (char) c;
                        lex.type = TokenType.INVALID_TOKEN;
                        state = 6;
                    }
                    break;
                case 2:
                    if( c == '.' || Character.isLetter(c) || Character.isDigit(c)){
                        lex.token += (char) c;
                        state  = 2;
                    }
                    else{
                        state = 5;
                        ungetc(c);
                    }
                    break;
                case 3:
                    if( Character.isDigit(c)){
                        lex.token += (char) c;
                        state  = 3;
                    }
                    else{
                        state = 6;
                        lex.type = TokenType.NUMBER;
                        ungetc(c);
                    }
                    break;
                case 4:
                    if( c == '"'){
                        lex.type = TokenType.TEXT;
                        state  = 6;
                    }
                    else{
                        lex.token += (char) c;
                        state = 4;
                    }
                    break;
                default:
                    throw new LexicalException("Unreachable");
            }
        }

        if (state == 5)
            lex.type = st.find(lex.token);

        return lex;
    }

    private int getc() {
        try {
            return input.read();
        } catch (Exception e) {
            throw new LexicalException("Unable to read file");
        }
    }

    private void ungetc(int c) {
        if (c != -1) {
            try {
                input.unread(c);
            } catch (Exception e) {
                throw new LexicalException("Unable to ungetc");
            }
        }
    }
}
