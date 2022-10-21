package syntatic;

import lexical.Lexeme;
import lexical.LexicalAnalysis;
import lexical.TokenType;

public class SyntaticAnalysis {

    private LexicalAnalysis lex;
    private Lexeme current;

    public SyntaticAnalysis(LexicalAnalysis lex) {
        this.lex = lex;
        this.current = lex.nextToken();
    }

    public void start() {
       procCode();
    }

    private void advance() {
        // System.out.println("Advanced (\"" + current.token + "\", " +
        //     current.type + ")");
        current = lex.nextToken();
    }

    private void eat(TokenType type) {
        // System.out.println("Expected (..., " + type + "), found (\"" + 
        //     current.token + "\", " + current.type + ")");
        if (type == current.type) {
            current = lex.nextToken();
        } else {
            showError();
        }
    }

    private void showError() {
        System.out.printf("Não");
        System.exit(1);
    }

    private void procCode(){
        procObj();
    }

    private void procObj(){
        eat(TokenType.NAME);
        procBody();
    }

    private void procBody(){
        eat(TokenType.OPEN_CUR);
        while(current.type == TokenType.NAME){
            advance();

            if(current.type == TokenType.COLON){
                advance();
                if(current.type == TokenType.NUMBER ||
                current.type == TokenType.TEXT ||
                current.type == TokenType.OPEN_BRA){
                    procExp();
                }
                else eat(TokenType.NAME);
            }
            else if(current.type == TokenType.OPEN_CUR){
                procBody();
            }
        }
        eat(TokenType.CLOSE_CUR);
    }

    private void procExp(){
        if(current.type == TokenType.OPEN_BRA){
            procList();
        }
        else advance();
    }

    private void procList(){
        eat(TokenType.OPEN_BRA);

        if(current.type == TokenType.CLOSE_BRA){
            advance();
        }
        else{
            while(current.type == TokenType.NUMBER ||
            current.type == TokenType.TEXT ||
            current.type == TokenType.OPEN_BRA ||
            current.type == TokenType.NAME){
                if(current.type == TokenType.NUMBER ||
                current.type == TokenType.TEXT ||
                current.type == TokenType.OPEN_BRA){
                    procExp();
                }
                else{
                    eat(TokenType.NAME);
                    if(current.type == TokenType.OPEN_CUR)
                        procBody();
                }
                if(current.type == TokenType.COMMA){
                    advance();
                }
                else if(current.type == TokenType.CLOSE_BRA){
                    advance();
                    break;
                }
                else showError();
            }
        }
    }

}
