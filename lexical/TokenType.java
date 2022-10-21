package lexical;

public enum TokenType {
    // SPECIALS
    UNEXPECTED_EOF,
    INVALID_TOKEN,
    END_OF_FILE,

    // SYMBOLS
    COLON, //:
    OPEN_BRA, // [
    CLOSE_BRA, // ]
    OPEN_CUR, // {
    CLOSE_CUR, // }
    COMMA, // ,
    POINT, // .

    // OTHERS
    NAME,          // identifier
    NUMBER,        // integer
    TEXT,           // string
    LIST,          //arranjo
    OBJECT         //objeto

};
