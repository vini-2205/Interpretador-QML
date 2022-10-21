import lexical.Lexeme;
import lexical.LexicalAnalysis;
import lexical.TokenType;
import syntatic.SyntaticAnalysis;

public class mdi {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java mdi [miniDart file]");
            return;
        }

        try (LexicalAnalysis l = new LexicalAnalysis(args[0])) {
            
            // O código a seguir é dado para testar o interpretador.
            // TODO: descomentar depois que o analisador léxico estiver OK.
            SyntaticAnalysis s = new SyntaticAnalysis(l);
            s.start();
            System.out.println("Sim");

            // O código a seguir é usado apenas para testar o analisador léxico.
            // TODO: depois de pronto, comentar o código abaixo.
        //     Lexeme lex;
        //     do {
        //         lex = l.nextToken();
        //         System.out.printf("%02d: (\"%s\", %s)\n", l.getLine(),
        //             lex.token, lex.type);
        //     } while (lex.type != TokenType.END_OF_FILE &&
        //              lex.type != TokenType.INVALID_TOKEN &&
        //              lex.type != TokenType.UNEXPECTED_EOF);
        // } catch (Exception e) {
        //     System.err.println("Internal error: " + e.getMessage());
        }
    }

}
