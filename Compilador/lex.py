import sys
from token import Token
from tokentype import *

class Lexer:
    #CONSTRUCTOR THIS

    def __init__(self, input):
        self.source=input
        self.curChar=''
        self.curPos=-1
        self.nextChar()

#PEOCESA EL CARACTER ACTUAL
    def nextChar(self):
        self.curPos+=1;
        if self.curPos>= len(self.source):
             self.curChar='\0'    #EOF
        else:
             self.curChar=self.source[self.curPos]

    #ANTICIPA EL CARACTER QUE SIGUE
    def pack(self):
        if self.curPos + 1>=len(self.source):
             return '\0'
        return self.sorce[self.curPos + 1]
    #MUESTRA EL ERROR POR SI HAY TOKEN INVALIDO
    def abort(self, message):
        sysy.exit("Error de léxico"+message)
    #SALTEARSE LOS ESPACIOS EN BLANCO
    def skipWhitespace(self):
        while self.curChar==' ' or self.curChar=='\t' or self.curChar == '\r':
            self.curChar
    #SKIP COMMENTS
    def skipComment(self):
        if self.curChar=='#':
            while self.curChar != "\n":
                self.nextChar()
    #obtiene el token siguiente
    def getToken(self):
        self.skipWhitespace()
        self.skipComment()
        token=None
        #checar primero si el primer caracter + =
        if self.curChar=='+':
            token=Token(self.curChar, TokenType.PLUS)
        elif self.curChar=='-':
            token=Token(self.curChar, TokenType.MINUS)
        elif self.curChar=='*':
            token=Token(self.curChar, TokenType.ASTERISK)
        elif self.curChar=='/':
            token=Token(self.curChar, TokenType.SLASH)
        elif self.curChar=='=':
        #VERIFICAR SI ESTAN ASIGNANDO O COMPARANDO
            if self.peek()=='=':
                lastChar=self.curChar
                self.nextChar()
                token=Token(lastChar+self.curChar, TokenType.EQEQ)
            else:
                token=Token(self.curChar, TokenType.EQ)
            #MAYOR QUE
        elif self.curChar=='>':
           if self.peek()=='=':
                lastChar=self.curChar
                self.nextChar()
                token=Token(lastChar+self.curChar, TokenType.GTEQ)
           else:
                token=Token(self.curChar, TokenType.GT)
#MENOR QUE
        elif self.curChar=='<':
           if self.peek()=='=':
                lastChar=self.curChar
                self.nextChar()
                token=Token(lastChar+self.curChar, TokenType.LTEQ)
           else:
                token=Token(self.curChar, TokenType.LT)
        #DIFERENTE IGUAL
        elif self.curChar=='!':
           if self.peek()=='=':
                lastChar=self.curChar
                self.nextChar()
                token=Token(lastChar+self.curChar, TokenType.NOTEQ)
           else:
                self.abort("se esperaba != y escribiste un !"+self.peek() )

        elif self.curChar=='\"':
            self.nextChar()
            starPos=self.curPos
            while self.curChar !='\"':
                if self.curChar=='\r' or self.curChar=='\n' or self.curChar=='\t' or self.curChar=='\\' or self.curChar=='%':
                    self.abort("caracter no válido en el string")
                self.nextChar()

            tokenText=self.source[starPos: self.curPos]
            token=Token(tokenText, TokenType.STRING)
            #CAPTURAR NUMEROS
        elif self.curChar.isdigit():
            starPos=self.curPos
            while self.peek().isdigit():
                self.curChar
            if self.peek()=='.':
                self.nextChar()
                if not self.peek().isdigit():
                    self.abort("caracter no válido en el número")

                while self.peek().isdigit():
                    self.nextChar()

            tokenText=self.source[starPos: self.curPos+1]
            token=Token(tokenText, TokenType.NUMBER)

        elif self.curChar.islpha():
            starPos=self.curPos
            while self.peek().isalnum():
                self.nestChar()
            tokenText=self.source[starPos : self.curPos +1 ]
            keyword = Token.checkIfKeyword(tokenText)
            if keyword==None:
                #identificador
                token=Token(tokenText, TokenType.IDENT)
            else:
                 token=Token(tokenText, keyword)
        elif self.curChar=='\n':
            token =Token(self.curChar, TokenType.NEWLINE)
        elif self.curChar=='\0':
            token =Token(self.curChar, TokenType.EOF)
        else:
            self.abort("Token desconocido "+self.curChar)

        self.nextChar()
        return token;

                    

        
